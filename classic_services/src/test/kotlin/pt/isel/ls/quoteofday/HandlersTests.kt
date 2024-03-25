package pt.isel.ls.quoteofday

import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.UriTemplate
import org.http4k.routing.RoutedRequest
import pt.isel.ls.QUOTE_ROUTE
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HandlersTests {

    @Test
    fun `getQuoteOfDayHandler returns response with a quote`() {
        // Arrange
        val request = Request(Method.GET, QUOTE_ROUTE)
        val theQuote = Quote("Hello, World!", "Anonymous")
        val fakeService = { theQuote }
        val getQuoteOfDayHandler = buildGetQuoteOfDayHandler(fakeService)
        // Act
        val response = getQuoteOfDayHandler(request)
        // Assert
        val content = Json.decodeFromString<QuoteOfDay>(response.bodyString())
        assertEquals(expected = theQuote.text, actual = content.quote)
        assertEquals(expected = theQuote.author, actual = content.author)
        assertTrue(content.date.isNotBlank())
        assertEquals(expected = "application/json", actual = response.header("Content-Type"))
    }
}