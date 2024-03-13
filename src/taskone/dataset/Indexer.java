package taskone.dataset;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Paths;

public class Indexer {
    public static void main(String[] args) {
        String indexPath = "./indexFolder/TaskOne/dataset";

        try {
            Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));
            StandardAnalyzer analyzer = new StandardAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig (analyzer);
            IndexWriter indexWriter = new IndexWriter(indexDirectory, config);

            String dataFolderPath = "./dataset";

            File[] files = new File(dataFolderPath).listFiles();

            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()) {
                        System.out.println("Indexing " + file.getCanonicalPath());
                        Document document = new Document();

                        document.add(new TextField("contents", new FileReader(file)));

                        document.add(new TextField("filename", file.getName(), Field.Store.YES));

                        document.add(new TextField("path", file.getCanonicalPath(), Field.Store.YES));

                        indexWriter.addDocument(document);
                    }
                }

                indexWriter.commit();
                indexWriter.close();

                System.out.println("Indexing completed successfully.");
            } else {
                System.out.println("No files found in the specified directory.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
