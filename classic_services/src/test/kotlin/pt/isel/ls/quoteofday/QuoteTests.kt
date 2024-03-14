package pt.isel.ls.quoteofday

import kotlin.test.Test
import kotlin.test.assertFailsWith

class QuoteTests {

    @Test
    fun `create Quote with non blank text and author succeeds`() {
        Quote("Hello, World!", "Anonymous")
    }

    @Test
    fun `create Quote with blank text fails`() {
        assertFailsWith<IllegalArgumentException> {
            Quote("", "Anonymous")
        }
    }

    @Test
    fun `create Quote with blank author fails`() {
        assertFailsWith<IllegalArgumentException> {
            Quote("Hello, World!", "")
        }
    }
}