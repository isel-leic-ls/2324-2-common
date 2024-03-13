package pt.isel.ls

import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.slf4j.LoggerFactory

/**
 * Script for the classic services host
 *
 * 1. Create a server that listens on port 8080 using http4k (Read through the main function)
 * 2. Test the function buildRoutes, which returns the set of supported routes (Read through the RoutesTests class)
 * 3. Make the tests of RoutesTests pass by adding the required routes to the buildRoutes function. The handlers should
 * return a 404 status code. BTW, take the opportunity to talk about the syntax for registering a route in http4k.
 * 4. Test the routes handlers (Read through the echo.HandlerTests and quoteOfDay.HandlerTests classes)
 *      4.1. Let's refine the QuoteOfDay service contract so that we can test if the response body is actually a quote.
 *      4.2. Should we refine Echo service contract? What would be the consequences on our implementation?
 * 5. Make the tests of the handlers pass by actually implementing the handlers. Their skeleton implementation is
 * already provided in the echo.Handlers.kt ang quoteofday.Handlers.kt files. We will only use JSON encoding for the
 * QuoteOfDay service, for contrast with the Echo service.
 * 6. ...
 */
fun main() {

    val logger = LoggerFactory.getLogger("pt.isel.ls.ClassicServicesHost")
    val port = 8080

    val classicServicesRoutes = buildRoutes()
    val server = classicServicesRoutes.asServer(Jetty(port)).start()

    logger.info("Server started on port $port. Press Enter to stop it ...")
    readln()

    server.stop()

    logger.info("Server stopped")
}