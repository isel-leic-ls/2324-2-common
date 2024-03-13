package pt.isel.ls.echo

import org.http4k.core.Method
import org.http4k.core.Request
import pt.isel.ls.ECHO_ROUTE
import kotlin.test.Test
import kotlin.test.assertEquals

class HandlersTests {

    @Test
    fun `echoHandler returns response with the same body as the request`() {
        // Arrange
        val bodyText = "Hello, World!"
        val request = Request(Method.PUT, ECHO_ROUTE).body(bodyText)
        // Act
        val response = echoHandler(request)
        // Assert
        assertEquals(expected = 200, actual = response.status.code)
        assertEquals(expected = bodyText, actual = response.bodyString())
        assertEquals(expected = "text/plain", actual = response.header("Content-Type"))
    }

    @Test
    fun `echoHandler returns 400 if request has a blank body`() {
        // Arrange
        val blankBodyText = " \t    \r"
        val request = Request(Method.PUT, ECHO_ROUTE).body(blankBodyText)
        // Act
        val response = echoHandler(request)
        // Assert
        assertEquals(expected = 400, actual = response.status.code)
    }
}