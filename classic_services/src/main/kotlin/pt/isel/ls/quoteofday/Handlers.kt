package pt.isel.ls.quoteofday

import kotlinx.serialization.Serializable
import org.http4k.core.Request
import org.http4k.core.Response
import java.time.LocalDate

/**
 * Handles the request for the quote of the day
 */
fun getQuoteOfDayHandler(request: Request): Response {
    TODO()
}

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
fun Quote.toQuoteOfDay(): QuoteOfDay = QuoteOfDay(text, author, LocalDate.now().toString())
