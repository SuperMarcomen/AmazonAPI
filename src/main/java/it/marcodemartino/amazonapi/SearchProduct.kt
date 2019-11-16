package it.marcodemartino.amazonapi

import it.marcodemartino.amazonapi.objects.Product
import org.jsoup.Jsoup

fun main() {
    SearchProduct("Echo dot")
}

class SearchProduct(private val query: String) {

    init {
        val url = "https://www.amazon.de/s?k=$query"
        val doc = Jsoup.connect(url).get()
        val productElements = doc.getElementsByClass("s-result-list s-search-results sg-row")
        val productList = mutableListOf<Product>()

        for (element in productElements.select("div[^data-index]"))
            productList.add(Product.fromElement(element))

    }

}