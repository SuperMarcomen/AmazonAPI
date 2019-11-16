package it.marcodemartino.amazonapi.objects

import org.jsoup.nodes.Element


class Product(
        private val productName: String,
        private val cost: Float
) {

    companion object {
        fun fromElement(element: Element): Product {
            val productName = element.getElementsByClass("a-size-medium a-color-base a-text-normal").first().text()
            val cost = element.getElementsByClass("a-price-whole").first().text().replace(',', '.').toFloat()

            return Product(productName, cost)
        }
    }

    init {
        println("Product name: $productName Cost: $cost")
    }

}