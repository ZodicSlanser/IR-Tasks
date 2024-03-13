package taskthree;

import java.util.*;
import java.util.stream.Collectors;

public class InvertedIndex {

    public static Map<String, Set<Integer>> createIndex(String[] documents) {
        Map<String, Set<Integer>> invertedIndex = new HashMap<>();

        for (int i = 0; i < documents.length; i++) {
            String[] words = documents[i].toLowerCase().split("\\s+");
            for (String word : words) {
                invertedIndex.computeIfAbsent(word, key -> new HashSet<>()).add(i + 1);
            }
        }

        return invertedIndex;
    }

    public static Map<String, Set<Integer>> performQuery(Map<String, Set<Integer>> invertedIndex, String word1, String word2, String operation) {
        if (!invertedIndex.containsKey(word1) || !invertedIndex.containsKey(word2)) {
            System.out.println("Error: Words not found in the index.");
            return null;
        }

        Set<Integer> word1Indexes = invertedIndex.get(word1);
        Set<Integer> word2Indexes = invertedIndex.get(word2);

        Map<String, Set<Integer>> invertedResult = new HashMap<>();
        switch (operation.toUpperCase()) {
            case "AND":
                invertedResult.put("AND", word1Indexes.stream().filter(word2Indexes::contains).collect(Collectors.toSet()));
                break;
            case "OR":
                Set<Integer> union = new HashSet<>(word1Indexes);
                union.addAll(word2Indexes);
                invertedResult.put("OR", union);
                break;
            case "XOR":
                Set<Integer> xor = new HashSet<>(word1Indexes);
                xor.addAll(word2Indexes);
                Set<Integer> intersection = new HashSet<>(word1Indexes);
                intersection.retainAll(word2Indexes);
                xor.removeAll(intersection);
                invertedResult.put("XOR", xor);
                break;
            case "AND NOT":
                Set<Integer> andNotResult = new HashSet<>(word1Indexes);
                andNotResult.removeAll(word2Indexes);
                invertedResult.put("AND NOT", andNotResult);
                break;
            default:
                System.out.println("Error: Invalid operation. Valid operations are AND, OR, XOR, and AND NOT.");
                return null;
        }

        return invertedResult;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] documents = {
                "breakthrough drug for depression",
                "new depression drug",
                "new approach for treatment of depression",
                "new hopes for depression patients",
                "latest developments in depression research",
                "innovative therapies for managing depression",
                "depression breakthroughs in medical science",
                "promising treatments for depression",
                "cutting-edge solutions for depression patients",
                "revolutionary approaches to depression treatment",
                "recent studies on depression medication",
                "hopeful advancements in depression care"
        };

        Map<String, Set<Integer>> invertedIndex = createIndex(documents);

        System.out.println("\n====================================================================================");
        System.out.println("Complete Inverted Index:");
        System.out.println("====================================================================================\n");
        invertedIndex.forEach((word, indexes) -> System.out.println(word + ": " + indexes));

        System.out.println("\nEnter the first word:");
        String word1 = scanner.nextLine().toLowerCase();

        System.out.println("Enter the second word:");
        String word2 = scanner.nextLine().toLowerCase();

        System.out.println("Enter the operation (AND, OR, XOR, AND NOT):");
        String operation = scanner.nextLine().toUpperCase();

        Map<String, Set<Integer>> invertedResult = performQuery(invertedIndex, word1, word2, operation);
        if (invertedResult != null) {
            System.out.println("\n====================================================================================");
            System.out.println("Display Result:");
            System.out.println("====================================================================================\n");

            for (Map.Entry<String, Set<Integer>> entry : invertedResult.entrySet()) {
                String operationResult = entry.getKey();
                Set<Integer> resultIndexes = entry.getValue();

                System.out.println(word1 + " " + operationResult + " " + word2 + ":");
                for (int index : resultIndexes) {
                    System.out.println("Line " + index + ": " + highlightWords(documents[index - 1], word1, word2));
                }
            }
        }
    }

    private static String highlightWords(String line, String word1, String word2) {
        return line.replaceAll("(?i)\\b" + word1 + "\\b", "***" + word1.toUpperCase() + "***")
                .replaceAll("(?i)\\b" + word2 + "\\b", "***" + word2.toUpperCase() + "***");
    }
}
