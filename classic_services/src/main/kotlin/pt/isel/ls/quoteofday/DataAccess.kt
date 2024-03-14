package pt.isel.ls.quoteofday

/**
 * Represents the contract of the data access that returns a random quote. This abstraction serves as a way to enable
 * testing of the service that uses the data access, by allowing the injection of a fake data access that returns predictable
 * results.
 */
typealias RandomQuote = () -> Quote

/**
 * The actual implementation of the data access that returns a random quote.
 */
// TODO: Implement the data access that returns a random quote fetched from the database
fun getRandomQuoteFromDB(): Quote =
    when ((0..1).random()) {
        0 -> Quote("Hello, World!", "Anonymous")
        else -> Quote("Hello Again, World!", "Anonymous")
    }