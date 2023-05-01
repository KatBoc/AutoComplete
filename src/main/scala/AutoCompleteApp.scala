object AutoCompleteApp extends App {
  val wordDictionary = new WordDictionary()
  val words = List("car", "carpet", "java", "javascript", "internet")

  words.foreach(wordDictionary.insert)

  val query = "Ja"
  var searchResults = wordDictionary.autoComplete(query)

  wordDictionary.delete("java")

  searchResults = wordDictionary.autoComplete(query)
}