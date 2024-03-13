package pt.isel.ls.quoteofday

/**
 * Fake implementation of the QuoteOfDayService
 */
fun fakeGetQuoteOfDay(): Quote {
    return Quote("This is a fake quote", "Fake Author")
}
