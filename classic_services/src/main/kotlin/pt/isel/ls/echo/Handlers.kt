package pt.isel.ls.echo

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status

/**
 * Handles the echo request
 */
fun echoHandler(request: Request): Response {
    val body = request.bodyString()
    return if (body.isBlank()) {
        Response(Status.BAD_REQUEST)
    } else {
        Response(Status.OK).body(body).header("Content-Type", "text/plain")
    }
}
