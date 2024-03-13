package tasktwo;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;


public class Analyzers {

    private static final String SAMPLE_TEXT_DEFAULT = "ZYXZ&F Corportion - zfz@example.com";
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter some text (or leave blank for a default example): ");
        String text = scanner.nextLine();

        if (text.length() < 5) {
            System.out.println("Using default text: " + SAMPLE_TEXT_DEFAULT);
            text = SAMPLE_TEXT_DEFAULT;
        }

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        WhitespaceAnalyzer whitespaceAnalyzer = new WhitespaceAnalyzer();
        SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer();
        StopAnalyzer stopAnalyzer = new StopAnalyzer(EnglishAnalyzer.getDefaultStopSet());

        System.out.println("\n=========================================================");
        System.out.println("Display Results Analyzed Text:");
        System.out.println("=========================================================\n");

        System.out.println("StandardAnalyzer: " + analyzeText(text, standardAnalyzer));
        System.out.println("WhitespaceAnalyzer: " + analyzeText(text, whitespaceAnalyzer));
        System.out.println("SimpleAnalyzer: " + analyzeText(text, simpleAnalyzer));
        System.out.println("StopAnalyzer: " + analyzeText(text, stopAnalyzer));
    }

    public static List<String> analyzeText(String text, Analyzer analyzer) throws IOException {
        List<String> tokens = new ArrayList<>();
        TokenStream tokenStream = analyzer.tokenStream("", new StringReader(text));
        tokenStream.reset();

        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);

        while (tokenStream.incrementToken()) {
            tokens.add(charTermAttribute.toString());
        }

        tokenStream.end();
        tokenStream.close();

        return tokens;
    }
}
