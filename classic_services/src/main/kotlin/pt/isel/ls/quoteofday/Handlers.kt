package pt.isel.ls.quoteofday

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status

/**
 * Handles the request for the quote of the day using the given service. Passing the service as a parameter allows the
 * handler to be tested with a fake service that returns a known quote, instead of the real service that returns a quote
 * that changes every day.
 */
fun handleGetQuoteOfDay(service: QuoteOfDayService): Response {
    val quote = service()
    val quoteOfDay = quote.toQuoteOfDay()
    return Response(Status.OK)
        .header("Content-Type", "application/json")
        .body(Json.encodeToString(quoteOfDay))
}

/**
 * Builds the handler for the quote of the day by injecting the dependency to the service. This function is useful for
 * testing purposes, as it allows the injection of a fake service that returns predictable results. By default, the
 * handler is built with the real service that returns a quote that changes every day.
 */
fun buildGetQuoteOfDayHandler(service: QuoteOfDayService = buildService()): HttpHandler = { _ -> handleGetQuoteOfDay(service) }

/**
 * Represents data contained in the QuoteOfDay HTTP service responses. These responses contain a JSON representation of a
 * quote of the day. This is why the class is annotated with @Serializable, which is a Kotlinx serialization annotation
 * that allows the class to be serialized to and deserialized from JSON.
 *
 * Generally speaking, these types of data classes are called DTOs (Data Transfer Objects) and are used to represent the
 * data that is sent and received by the HTTP service. If the class is pertaining to the request, it is usually called
 * Input Model, and if it is pertaining to the response, it is usually called an Output Model.
 */
@Serializable
data class QuoteOfDay(val quote: String, val author: String, val date: String)

/**
 * Extension function that converts a Quote to a QuoteOfDay
 */
fun Quote.toQuoteOfDay(): QuoteOfDay = QuoteOfDay(
    quote = text,
    author = author,
    date = currentLocalDate().toString()
)
