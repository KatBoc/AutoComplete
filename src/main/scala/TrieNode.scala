/**
 * Represents a single node in the Trie data structure.
 *
 * Each TrieNode contains a map of its children nodes, where a char is the key.
 * The value is corresponding child TrieNode.
 * Each TrieNode has a flag 'isEndOfWord', that indicates whether the node represents the end of a word in a Trie.
 *
 * @param children A map of the children TrieNodes with characters as keys.
 * @param isEndOfWord A boolean flag indicating if the TrieNode represents the end of a word.
 */

case class TrieNode(
                     children: Map[Char, TrieNode] = Map.empty[Char, TrieNode],
                     isEndOfWord: Boolean = false
                   )
