## Information Retrieval Tasks Repository

This repository contains implementations of various information retrieval tasks using Apache Lucene and Java. Each task is organized into separate packages and includes both indexing and searching functionalities.
Note that the code in this repo is written in Java 17 and uses Apache Lucene 9.10.0.

### Task 1-1: Simple Text Indexing and Searching

**Package:** `taskone.console`

**Indexer:**
- File: `Indexer.java`
- Description: Indexes a single document with a predefined field ("Name") using Lucene.
- Functionality:
    - Creates an index in the specified directory.
    - Adds a document with a sample text to the index.

**Searcher:**
- File: `Searcher.java`
- Description: Searches for user-input queries within the index.
- Functionality:
    - Accepts a search query from the user.
    - Utilizes Lucene to search for matching documents.
    - Displays search results, including scores and document names.

### Task 1-2: Indexing and Searching a Dataset

**Package:** `taskone.dataset`

**Indexer:**
- File: `Indexer.java`
- Description: Indexes multiple documents from a dataset using Lucene.
- Functionality:
    - Reads documents from a specified directory.
    - Indexes document contents, filename, and path.

**Searcher:**
- File: `Searcher.java`
- Description: Searches for user-input queries within the dataset index.
- Functionality:
    - Accepts a search query from the user.
    - Utilizes Lucene to search for matching documents.
    - Displays search results, including scores and filenames.

### Task 2: Text Analysis with Different Analyzers

**Package:** `tasktwo`

**Analyzers:**
- File: `Analyzers.java`
- Description: Analyzes input text using different Lucene analyzers.
- Functionality:
    - Accepts user input or uses a default text for analysis.
    - Utilizes Standard, Whitespace, Simple, and Stop analyzers.
    - Displays tokenized results for each analyzer.

### Task 3: Inverted Index and Boolean Query Operations

**Package:** `taskthree`

**InvertedIndex:**
- File: `InvertedIndex.java`
- Description: Creates an inverted index and performs Boolean queries.
- Functionality:
    - Creates an inverted index from a set of predefined documents.
    - Supports AND, OR, XOR, and AND NOT operations for user-input queries.
    - Displays search results with highlighted query terms in the documents.

