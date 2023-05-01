class WordDictionary {
  private val root = new TrieNode()
  def insert(word: String): Unit = {
    var currentNode = root
    val wordToInsert = word.toLowerCase()
    for (ch <- wordToInsert) {
      if (!currentNode.children.contains(ch))
        {
          currentNode.children(ch) = new TrieNode()
        }
      currentNode = currentNode.children(ch)
    }
    currentNode.isEndOfWord = true
  }

  def delete(word: String): Unit = {
    deleteHelper(root, word, 0)
  }

  private def deleteHelper(node: TrieNode, word: String, index: Int): Boolean = {
    if (index == word.length) {
      if (!node.isEndOfWord) {
        return false
      }
      node.isEndOfWord = false
      return node.children.isEmpty
    }

    val ch = word(index)
    if (!node.children.contains(ch)) {
      return false
    }

    val shouldDeleteCurrentNode = deleteHelper(node.children(ch), word, index + 1)

    if (shouldDeleteCurrentNode) {
      node.children.remove(ch)
      return node.children.isEmpty && !node.isEndOfWord
    }

    false
  }

  private def search(query: String): Option[List[String]] = {
    var currentNode = root
    for (ch <- query) {
      if (!currentNode.children.contains(ch)) {
        return None // querry prefix not present
      }
      currentNode = currentNode.children(ch)
    }

    if (currentNode.isEndOfWord && currentNode.children.isEmpty) {
      Some(List(query))
    } else {
      Some(searchWords(query, currentNode))
    }
  }

  def autoComplete(query: String) = {
    val searchResults = search(query.toLowerCase())
    searchResults match {
      case Some(result) =>
        println(s"Autocomplete result for '$query': ${result.mkString(", ")}")
      case None =>
        println(s"No words found with the prefix '$query'")
    }
  }

  private def searchWords(prefix: String, node: TrieNode): List[String] = {
    var words = List[String]()
    if (node.isEndOfWord) {
      words = prefix :: words
    }
    for ((ch, child) <- node.children) {
      words = words ::: searchWords(prefix + ch, child)
    }
    words
  }
}
