import scala.io._
/**
 * An object that provides autocomplete functionality using a dictionary tree.
 */
object AutoComplete {
  val dictionaryTree = new DictionaryTree()

  /**
   * Inserts a list of 10,000 English words into the dictionary tree.
   */
  def insert10000WordList(): Unit = {
    val englishWordsUrl = "https://www.mit.edu/~ecprice/wordlist.10000"
    dictionaryTree.insertFromTxtURL(englishWordsUrl)
  }

  /**
   * Inserts a list of test words into the dictionary tree.
   */
  def insertTestWordList(): Unit = {
    val words = List("bar", "car", "carpet", "java", "saver", "javascript", "internet")
    dictionaryTree.insertFromList(words)
  }

  /**
   * Displays the contents of the dictionary tree.
   */
  def displayTree(): Unit = {
    dictionaryTree.displayTrie(dictionaryTree.getRoot)
  }

  /**
   * Returns a list of suggested words for the given query.
   *
   * @param query the query to autocomplete
   * @return a list of suggested words
   */
  def autocomplete(query: String): List[String] = {
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions
  }

  /**
   * The main entry point of the program. Parses command line arguments and performs the necessary actions.
   *
   * @param args the command line arguments
   */
  def main(args: Array[String]): Unit = {
    if (args.length == 1) {
      val query = args(0)
      insertTestWordList()
      println(autocomplete(query).mkString(", "))
    }

    if (args.length > 1) {
      if (args.contains("--insert10000")) {
        insert10000WordList()
        val query = args(1)
        println(autocomplete(query).mkString(", "))
      }
    }
  }
}
