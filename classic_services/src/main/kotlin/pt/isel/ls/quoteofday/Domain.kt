package pt.isel.ls.quoteofday

/**
 * Represents a quote in our domain model
 * @property text the quote text. Must not be blank.
 * @property author the quote author. Must not be blank.
 */
data class Quote(
    val text: String,
    val author: String,
) {
    init {require(text.isNotBlank()) { "The quote text must not be blank" }

        require(author.isNotBlank()) { "The quote author must not be blank" }
    }
}

/**
 * Represents the service that provides the quote of the day
 */
typealias QuoteOfDayService = () -> Quote
