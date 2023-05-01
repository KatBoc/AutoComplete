# Dictionary Tree Autocomplete

A Scala application that demonstrates the use of a Trie data structure to implement an efficient autocomplete functionality for words. The application reads a list of words, stores them in a Trie, and provides an autocomplete method to search for words based on their prefixes.

## Features

- Trie data structure for efficient word storage and searching
- Insert word into the DictionaryTree
- (In progress) Delete word from the DictionaryTree
- Autocomplete functionality to search for words with a given prefix
- Display the Trie structure for debugging purposes

## Usage

You can import the code into your Scala project by including the DictionaryTree class and its associated TrieNode case class in your source code.

1. Insert words into the DictionaryTree using the `insert` method from DictionaryTree class.
2. Get a list of autocomplete suggestions for a given prefix using `autocomplete` method from DictionaryTree class.
3. Display the Trie structure of the DictionaryTree for debugging purposes by `displayTrie` from DictionaryTree class.

## Compiling and Running the Program

1. Make sure you have `sbt` (Scala Build Tool) installed on your system. If you don't have it, you can find the installation instructions [here](https://www.scala-sbt.org/download.html).
2. Open a terminal/command prompt and navigate to the project directory.
3. Compile the program by running `sbt compile`.
4. To run the `autocomplete` method, execute the following command:

```bash
sbt "run [query]"
```
Replace `[query]` with the prefix you want to search for. For example:

```bash
sbt "run car"
```

This command will run the `main` method in the `AutoComplete` object, which in turn calls the `autocomplete` method with the provided prefix. The program will then display a list of words that start with the provided prefix.
By default, the tree will be build from list of words: "bar", "car", "carpet", "java", "saver", "javascript", "internet".
To insert the 10,000-word list and search for a prefix:

```bash
sbt "run --insert10000 [your_prefix]"
```
For example:

```bash
sbt "run --insert10000 ji"
```

## Testing

Run tests by executing the following command:

```bash
sbt test
```

## TODO
- [ ] change default dictionary to loaded from file
- [ ] functionality to add own path to words list in main
- [ ] 'delete' function for deleting a tree from dictionary tree
- [ ] short the tree by concatenating suffixes


## Inspirations
- [Auto Complete Feature using Trie](https://www.geeksforgeeks.org/auto-complete-feature-using-trie/)
- [Data structures for fast auto-complete](https://futurice.com/blog/data-structures-for-fast-autocomplete?fbclid=IwAR3faZklHNJ3NGLkzlNCvbwrOdh7d1M6rWgAuDFsNYdDF-bhlBv7NQ37uGI)