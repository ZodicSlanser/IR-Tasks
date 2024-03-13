package taskone.dataset;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Searcher {

    public static void main(String[] args) {
        String indexPath = "./indexFolder/TaskOne/dataset";
        String fieldName = "contents";

        try (Directory indexDirectory = FSDirectory.open(Paths.get(indexPath))) {
            IndexReader indexReader = DirectoryReader.open(indexDirectory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            Analyzer analyzer = new StandardAnalyzer();

            QueryParser queryParser = new QueryParser(fieldName, analyzer);

            System.out.println("Enter a search query: ");
            Scanner scanner = new Scanner(System.in);
            String queryString = scanner.nextLine();

            Query query = queryParser.parse(queryString);

            TopDocs topDocs = indexSearcher.search(query, 10);

            System.out.println("Search results for query: " + queryString);
            System.out.println("========================================================");

            if (topDocs.totalHits.value == 0) {
                System.out.println("No matching documents found for the query.");
            } else {
                System.out.println("Display Results:");
                System.out.println("========================================================");

                Set<String> fieldsToLoad = new HashSet<>();
                fieldsToLoad.add(fieldName);

                for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                    Document document = indexReader.document(scoreDoc.doc);
                    System.out.println("Score: " + scoreDoc.score + ", Document: " + document.get("filename"));
                }
            }

            indexReader.close();

        } catch (IOException | ParseException e) {
            System.err.println("Error occurred during search: " + e.getMessage());
        }
    }
}
