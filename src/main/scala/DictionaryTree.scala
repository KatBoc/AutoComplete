import scala.io.Source

/**
 * A data structure for storing and searching words, implemented as a Trie.
 * This class provides a dictionary-like functionality that allows efficient searching of words based on their prefixes.
 * It uses a Trie data structure to store words in a prefix tree, where each node represents a prefix and each edge
 * represents a character in the word. The Trie structure allows fast searching for words that start with a given prefix.
 *
 * @constructor Creates an empty DictionaryTree with a root TrieNode.
 */
class DictionaryTree {

  private var root = TrieNode()

  /**
   * Returns the root TrieNode of the DictionaryTree.
   *
   * @return The root TrieNode
   */
  def getRoot: TrieNode = root

  /**
   * Inserts a word into the DictionaryTree.
   *
   * This method calls a private recursive function 'insertHelper' with a given word and the root node.
   * It converts the word to lowercase and calls the private helper method insertHelper with the word as a List
   * of characters and the root TrieNode.
   *
   * @param word The word to be inserted into the DictionaryTree
   */
  def insert(word: String): Unit = {
    root = insertHelper(word.toLowerCase().toList, root)
  }


  /**
   * Inserts a word represented as a List of characters into the Trie rooted at the given node.
   *
   * This is a private recursive helper method that inserts a word represented as a List of characters into the Trie.
   * It recursively inserts each character of the word into the Trie by following the edges of the prefix tree.
   * If the current character does not have a corresponding child node in the Trie, it creates a new child node
   * and recursively inserts the rest of the word into that child node. If the current character already has a child node
   * in the Trie, it recursively inserts the rest of the word into that child node. If the word is completely inserted into
   * the Trie, it sets the isEndOfWord flag of the last node in the path to true.
   *
   * @param word The word represented as a List of characters to be inserted into the Trie.
   * @param node The TrieNode representing the current prefix in the Trie.
   * @return The TrieNode representing the updated prefix in the Trie after inserting the word.
   */
  private def insertHelper(word: List[Char], node: TrieNode): TrieNode = {
    word match {
      case Nil => TrieNode(node.children, isEndOfWord = true)
      case ch :: rest =>
        val updatedChildNode = node.children.get(ch) match {
          case Some(childNode) => insertHelper(rest, childNode)
          case None => insertHelper(rest, TrieNode())
        }
        TrieNode(node.children + (ch -> updatedChildNode), node.isEndOfWord)
    }
  }

/**
 * Inserts words from a text file located at `filePath` into a data structure.
 *
 * @param filePath the path to the text file to be read and inserted into the data structure
 */
  def insertFromTxtFile(filePath: String): Unit = {
    val fileSource = Source.fromFile(filePath, "UTF-8")
    val words = fileSource.mkString.split("\\W+").toList
    fileSource.close()
    words.foreach(insert)
  }

/**
 * Inserts words from a text file located at `urlPath` into a data structure.
 *
 * @param urlPath the URL path to the text file to be read and inserted into the data structure
 */
  def insertFromTxtURL(urlPath: String): Unit = {
    val urlSource = Source.fromURL(urlPath)
    val words = urlSource.getLines.toList
    urlSource.close()
    words.foreach(insert)
  }

  /**
   * Inserts words from a list of strings into a data structure.
   *
   * @param words the list of words to be inserted into the data structure
   */
  def insertFromList(words: List[String]): Unit = {
    words.foreach(insert)
  }

  /**
   * Delete a word from the DictionaryTree.
   *
   * This method calls a private recursive function 'deleteHelper' with a given word and the root node.
   * It converts the word to lowercase and calls the private helper method deleteHelper with the word as a List
   * of characters and the root TrieNode.
   *
   * @param word The word to be inserted into the DictionaryTree
   */
  def delete(word: String): Unit = {
    deleteHelper(word.toLowerCase().toList, root)
  }

  private def deleteHelper(word: List[Char], node: TrieNode): Option[TrieNode] = {
    // TODO: Implement deleteHelper method
    None
  }

  /**
   * Returns a list of words that start with the given prefix.
   *
   * This method searches for the TrieNode that corresponds to the prefix using the private recursive method 'findNodeByPrefix'.
   * If the TrieNode exists, the method calls the private recursive method 'completeWordsFromNode' with the prefix and TrieNode.
   *
   * @param prefix The prefix used to search for words.
   * @return A list of words that start with the given prefix.
   */
  def autocomplete(prefix: String): List[String] = {
    val prefixNode = findNodeByPrefix(prefix.toLowerCase().toList, root)
    prefixNode.map(completeWordsFromNode(prefix, _)).getOrElse(List.empty[String])
  }

  /**
   * Returns the TrieNode that corresponds to the given prefix.
   *
   * This is a private recursive helper method that searches for the TrieNode that corresponds to the prefix
   * in the DictionaryTree. It uses pattern matching on the prefix parameter to determine the base case and the recursive case:
   * Base case (case Nil => ...): When the prefix list is empty, it means that the corresponding TrieNode has been found.
   * Recursive case (case ch :: rest => ...): When the prefix list is not empty, it looks
   * for the child node that corresponds to the current character and continues the search recursively with the
   * rest of the prefix and the child node.
   *
   * @param prefix The prefix used to search for the TrieNode.
   * @param node   The current TrieNode being processed.
   * @return An Option containing the TrieNode that corresponds to the prefix (if found) or None.
   */
  private def findNodeByPrefix(prefix: List[Char], node: TrieNode): Option[TrieNode] = {
    prefix match {
      case Nil => Some(node)
      case ch :: rest =>
        node.children.get(ch).flatMap(childNode => findNodeByPrefix(rest, childNode))
    }
  }

  /**
   * Returns a list of complete words that can be formed from the given TrieNode.
   *
   * This is a private recursive helper method that searches for complete words that can be formed from the
   * TrieNode that corresponds to the given prefix.
   * It uses the TrieNode's children to find all possible complete words and returns them as a list.
   *
   * @param prefix The prefix used to search for words.
   * @param node   The current TrieNode being processed.
   * @return A list of complete words that can be formed from the TrieNode.
   */
  private def completeWordsFromNode(prefix: String, node: TrieNode): List[String] = {
    val wordsFromChildren = node.children.flatMap {
      case (ch, childNode) => completeWordsFromNode(prefix + ch, childNode)
    }.toList

    if (node.isEndOfWord) prefix :: wordsFromChildren else wordsFromChildren
  }

  /**
   * Displays the Trie structure of the DictionaryTree starting from the given node.
   *
   * This method is for debugging purposes and prints the Trie structure of the DictionaryTree starting
   * from the given TrieNode.
   * It prints each node and its children in a tree-like format.
   *
   * @param node   The TrieNode to start the display from.
   * @param prefix The prefix to display before each node.
   * @param level  The level of the current TrieNode in the tree structure.
   */
  def displayTrie(node: TrieNode, prefix: String = "", level: Int = 0): Unit = {
    val indent = "  " * level
    if (node.isEndOfWord) {
      println(s"$indent")
    }

    for ((ch, child) <- node.children) {
      println(s"$indent -> $ch")
      displayTrie(child, prefix + ch, level + 1)
    }
  }

}