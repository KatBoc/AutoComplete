import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers


class DictionaryTreeTest extends AnyFlatSpec with Matchers {

  val dictionaryTree = new DictionaryTree()

  // Insert words into the DictionaryTree
  val words = List("car", "carpet", "java", "javascript", "internet")
  words.foreach(dictionaryTree.insert)

  "A DictionaryTree" should "insert and search for words" in {

    // Test autocomplete functionality
    val query1 = "c"
    val suggestions1 = dictionaryTree.autocomplete(query1)
    suggestions1 should contain theSameElementsAs List("car", "carpet")

    val query2 = "car"
    val suggestions2 = dictionaryTree.autocomplete(query2)
    suggestions2 should contain theSameElementsAs List("car", "carpet")

    val query3 = "carp"
    val suggestions3 = dictionaryTree.autocomplete(query3)
    suggestions3 should contain theSameElementsAs List("carpet")

    val query4 = "jav"
    val suggestions4 = dictionaryTree.autocomplete(query4)
    suggestions4 should contain theSameElementsAs List("java", "javascript")

    val query5 = "intern"
    val suggestions5 = dictionaryTree.autocomplete(query5)
    suggestions5 should contain theSameElementsAs List("internet")
  }

  "A DictionaryTree" should "handle empty input for autocomplete" in {

    // Test autocomplete functionality with an empty query
    val query = ""
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions should contain theSameElementsAs words
  }

  "A DictionaryTree" should "handle uppercase prefix" in {

    // Test autocomplete functionality with uppercase prefix
    val query = "JA"
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions should contain theSameElementsAs List("JAva", "JAvascript")
  }

  "A DictionaryTree" should "handle not matching input for any word in dictionary" in {

    // Test autocomplete functionality with not matching input for any word in dictionary
    val query = "foo"
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions shouldBe empty
  }

  "A DictionaryTree" should "do not enter the same word twice" in {

    // test inserting the same word more than once
    dictionaryTree.insert("java")
    dictionaryTree.insert("java")

    val query = "ja"
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions should contain theSameElementsAs List("java", "javascript")
  }

  "A DictionaryTree" should "handle bigger datasets" in {

    val englishWordsUrl = "https://www.mit.edu/~ecprice/wordlist.10000"
    dictionaryTree.insertFromTxtURL(englishWordsUrl)
    val query = "jim"
    val suggestions = dictionaryTree.autocomplete(query)
    suggestions should contain theSameElementsAs List("jim", "jimmy")
  }

}