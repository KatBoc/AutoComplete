class TrieNode {
  val children: scala.collection.mutable.Map[Char, TrieNode] = scala.collection.mutable.Map[Char, TrieNode]()
  var isEndOfWord = false
}