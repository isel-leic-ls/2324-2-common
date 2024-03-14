package pt.isel.ls.quoteofday

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

private val theTestQuotes = arrayOf(
    Quote("Hello, World!", "Anonymous"),
    Quote("Hello Again, World!", "Another Anonymous")
)

class ServiceTests {

    @Test
    fun `service calls return a quote`() {
        // Arrange
        val expectedQuote = theTestQuotes[0]
        val fakeDataAccess = { expectedQuote }
        val service = buildService(dataAccess = fakeDataAccess, dateProvider = { LocalDate(2024, 10, 23) })
        // Act
        val actualResult = service()
        // Assert
        assertEquals(expectedQuote, actualResult)
    }

    @Test
    fun `service calls return the same quote for the same day`() {
        // Arrange
        val quotes = theTestQuotes.toMutableList()
        val expectedQuote = quotes.last()
        val fakeDataAccess = { quotes.removeLast() }
        val service = buildService(dataAccess = fakeDataAccess, dateProvider = { LocalDate(2021, 1, 1) })
        // Act
        val result1 = service()
        val result2 = service()
        // Assert
        assertEquals(expectedQuote, result1)
        assertEquals(expectedQuote, result2)
    }

    @Test
    fun `service calls return different quotes for different days`() {
        // Arrange
        val quotes = theTestQuotes.toMutableList()
        val fakeDataAccess = { quotes.removeLast() }
        val dates = mutableListOf(LocalDate(2021, 1, 1), LocalDate(2021, 1, 2))
        val fakeDateProvider = { dates.removeLast() }
        val service = buildService(dataAccess = fakeDataAccess, dateProvider = fakeDateProvider)
        // Act
        val result1 = service()
        val result2 = service()
        // Assert
        assertEquals(expected = theTestQuotes.last(), actual = result1)
        assertEquals(expected = theTestQuotes.first(), actual = result2)
    }
}