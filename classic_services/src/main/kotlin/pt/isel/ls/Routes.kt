package pt.isel.ls

import org.http4k.core.Method
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import pt.isel.ls.echo.echoHandler
import pt.isel.ls.quoteofday.buildGetQuoteOfDayHandler
import pt.isel.ls.quoteofday.buildService
import pt.isel.ls.quoteofday.service

const val ECHO_ROUTE = "/echo"
const val QUOTE_ROUTE = "/quote"

/**
 * Gets the set of supported routes
 */
fun buildRoutes(): RoutingHttpHandler =
    routes(
        ECHO_ROUTE bind Method.PUT to ::echoHandler,
        QUOTE_ROUTE bind Method.GET to buildGetQuoteOfDayHandler()
    )
