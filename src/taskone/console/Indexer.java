package taskone.console;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class Indexer {
    public static void main(String[] args) {
        String indexPath = "./indexFolder/TaskOne/console";

        try {
            Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));

            StandardAnalyzer analyzer = new StandardAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(indexDirectory, config);

            Document document = new Document();

            document.add(new TextField("Name", "example text to index", Field.Store.YES));

            indexWriter.addDocument(document);
            System.out.println(document.getFields().size());

            indexWriter.commit();
            System.out.println(document.getField("Name"));
            indexWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
