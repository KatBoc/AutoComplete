object Measurement {
  /**
   * Measure memory usage and time of basic functionality
   */
 private def measure[T](codeBlock: => T): (T, Long, Long) = {
   val runtime = Runtime.getRuntime
   val startTime = System.nanoTime()
   val initialMemory = runtime.totalMemory() - runtime.freeMemory()
   val result = codeBlock
   val endTime = System.nanoTime()
   val finalMemory = runtime.totalMemory() - runtime.freeMemory()

   val memoryUsage = finalMemory - initialMemory
   val executionTime = (endTime - startTime) / 1000000
   (result, memoryUsage, executionTime)
 }

  def measureTM(): Unit = {
    val dictionaryTreeTest = new DictionaryTree()
    val englishWordsUrl = "https://www.mit.edu/~ecprice/wordlist.10000"

    val (x, trieMemoryUsageForCreate, timeForCreate) = measure {
      dictionaryTreeTest.insertFromTxtURL(englishWordsUrl)
      dictionaryTreeTest
    }

    println(s"Trie memory usage for create: $trieMemoryUsageForCreate")
    println(s"Trie creation time: $timeForCreate ms")

    val (y, trieMemoryUsageForAutocomplete, timeForAutocomplete) = measure {
      val query = "th"
      val suggestions = dictionaryTreeTest.autocomplete(query)
      suggestions
    }

    println(s"Trie memory usage for autocomplete: $trieMemoryUsageForAutocomplete")
    println(s"Autocomplete execution time: $timeForAutocomplete ms")
  }

  def main(args: Array[String]): Unit = {
    measureTM()
  }
}
