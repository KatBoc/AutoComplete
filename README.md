# Dictionary Tree Autocomplete

A Scala application that demonstrates the use of a Trie data structure to implement an efficient autocomplete functionality for words. The application reads a list of words, stores them in a Trie, and provides an autocomplete method to search for words based on their prefixes.

## Features

- Trie data structure for efficient word storage and searching
- Insert word into the DictionaryTree
- (In progress) Delete word from the DictionaryTree
- Autocomplete functionality to search for words with a given prefix
- Display the Trie structure for debugging purposes

## Requirements

- [Scala](https://www.scala-lang.org/download) 
- [SBT](https://www.scala-sbt.org/download.html) (Scala Build Tool). .


## Usage

You can import the code into your Scala project by including the DictionaryTree class and its associated TrieNode case class in your source code.

1. Insert words into the DictionaryTree using the `insert` method from DictionaryTree class.
2. Get a list of autocomplete suggestions for a given prefix using `autocomplete` method from DictionaryTree class.
3. Display the Trie structure of the DictionaryTree for debugging purposes by `displayTrie` from DictionaryTree class.

## Compiling and Running the Program

1. Clone this repository.
2. Open a terminal/command prompt and navigate to the project directory.
3. Compile the program by running 
```bash
sbt compile
```

### Run autocomplete with a single argument (prefix)

To run the program with the test word list and search for words starting with the provided prefix, use the following command:

```bash
sbt "run [prefix]"
```
Replace `[prefix]` with the prefix you want to search for. 

For example:

```bash
sbt "run car"
```
By default, the tree will be build from list of words from "TestWords.txt": "bar", "car", "carpet", "java", "saver", "javascript", "internet". You can change the content of the file.

### Run autocomplete with two arguments (file path and prefix)

To run the program with a custom word list from a text file and search for words starting with the provided prefix, use the following command:

```bash
sbt "run [file_path] [prefix]"
```
Replace [file_path] with the path to your text file and [prefix] with the prefix you want to search for. 

For example:

```bash
sbt "run /path/to/your/wordlist.txt ji"
```

### Run autocomplete with the --insert10000 flag (insert 10,000 words and search for a prefix)

To run the program with ready 10,000-word list and search for words starting with the provided prefix, use the following command:

```bash
sbt "run --insert10000 [prefix]"
```

Replace [prefix] with the prefix you want to search for.
For example:

```bash
sbt "run --insert10000 ji"
```

### Notes

The 10,000-word list is not saved to a database after being inserted, so when you run the program again with a different command, the 10,000-word list will not be in the Trie.


## Testing

Run tests by executing the following command:

```bash
sbt test
```

## TODO
- [x] change default dictionary to loaded from file
- [x] functionality to add own path to words list in main
- [ ] 'delete' function for deleting a word from the dictionary tree
- [ ] short the tree by concatenating suffixes


## Inspirations
- [Auto Complete Feature using Trie](https://www.geeksforgeeks.org/auto-complete-feature-using-trie/)
- [Data structures for fast auto-complete](https://futurice.com/blog/data-structures-for-fast-autocomplete?fbclid=IwAR3faZklHNJ3NGLkzlNCvbwrOdh7d1M6rWgAuDFsNYdDF-bhlBv7NQ37uGI)