package it.marcodemartino.amazonapi

import it.marcodemartino.amazonapi.objects.Product
import org.jsoup.Jsoup

fun main() {
    AmazonAPI().searchProducts("Echo dot", 50)
}

class AmazonAPI {

    /* TODO add search in more pages */
    fun searchProducts(query: String, resultLimit: Int): List<Product> {
        val url = "https://www.amazon.de/s?k=$query"
        val doc = Jsoup.connect(url) //I don't know what it does but without this (V) line there is no result in the page
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36")
                .maxBodySize(0)
                .get()

        val productElements = doc.getElementsByClass("s-result-list s-search-results sg-row")
        val productList = mutableListOf<Product>()

        for (element in productElements.select("div[^data-index]"))
            productList.add(Product.fromElement(element))

        if (productList.size > resultLimit)
            productList.subList(0, resultLimit)

        return productList
    }

}