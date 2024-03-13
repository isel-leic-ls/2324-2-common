package pt.isel.ls.quoteofday

import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import pt.isel.ls.QUOTE_ROUTE
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HandlersTests {

    @Test
    fun `getQuoteOfDayHandler returns response with a quote`() {
        // Arrange
        val request = Request(Method.GET, QUOTE_ROUTE)
        // Act
        val response = getQuoteOfDayHandler(request)
        // Assert
        val content = Json.decodeFromString<QuoteOfDay>(response.bodyString())
        assertTrue(content.quote.isNotBlank())
        assertTrue(content.author.isNotBlank())
        assertTrue(content.date.isNotBlank())
        assertEquals(expected = "application/json", actual = response.header("Content-Type"))
    }
}