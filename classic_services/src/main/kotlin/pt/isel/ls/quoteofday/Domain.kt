package pt.isel.ls.quoteofday

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Represents the contract of the service that provides the quote of the day. This abstraction serves as a way to
 * enable testing of the handlers that use the service, by allowing the injection of a fake service that returns
 * predictable results.
 */
typealias QuoteOfDayService = () -> Quote

/**
 * Represents a quote in our domain model
 * @property text the quote text. Must not be blank.
 * @property author the quote author. Must not be blank.
 */
data class Quote(
    val text: String,
    val author: String,
) {
    init {
        require(text.isNotBlank()) { "The quote text must not be blank" }
        require(author.isNotBlank()) { "The quote author must not be blank" }
    }
}

/**
 * Actual implementation of the service that provides the quote of the day. We use the memoization technique to ensure
 * that the same quote is returned for the same day. Note that, for simplicity, the memoization is done in memory and
 * does not persist across server restarts.
 */
fun service(dataAccess: RandomQuote, dateProvider: () -> LocalDate): QuoteOfDayService {
    var quoteOfDay: Pair<Int, Quote>? = null
    return {
        val lastQuoteOfDay = quoteOfDay
        val today = dateProvider().dayOfYear
        if (lastQuoteOfDay == null || lastQuoteOfDay.first != today) {
            val newQuote = dataAccess()
            quoteOfDay = Pair(first = today, second = newQuote)
            newQuote
        }
        else {
            lastQuoteOfDay.second
        }
    }
}

/**
 * Builds the service for the quote of the day by injecting the required dependencies. This function is useful for
 * testing purposes, as it allows the injection of a fake data access that returns predictable results, and a fake
 * date provider that returns a known date. By default, the service is built with the real data access that returns a
 * random quote, and the real date provider that returns the current local date.
 */
fun buildService(
    dataAccess: RandomQuote = ::getRandomQuoteFromDB,
    dateProvider: () -> LocalDate = ::currentLocalDate
): QuoteOfDayService = service(dataAccess, dateProvider)

/**
 * Utility function that returns the current local date.
 */
fun currentLocalDate(): LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
