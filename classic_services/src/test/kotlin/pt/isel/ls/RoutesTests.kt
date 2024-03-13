package pt.isel.ls

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.routing.RouterMatch.MatchingHandler
import kotlin.test.Test
import kotlin.test.assertIs

class RoutesTests {

    @Test
    fun `buildRoutes returns router with Echo route`() {
        // Arrange
        val request = Request(Method.PUT, ECHO_ROUTE)
        // Act
        val matchResult = buildRoutes().match(request)
        // Assert
        assertIs<MatchingHandler>(matchResult, "No matching handler found for $request")
    }

    @Test
    fun `buildRoutes returns router with Quote Of The Day route`() {
        // Arrange
        val request = Request(Method.GET, QUOTE_ROUTE)
        // Act
        val matchResult = buildRoutes().match(request)
        // Assert
        assertIs<MatchingHandler>(matchResult, "No matching handler found for $request")
    }
}
