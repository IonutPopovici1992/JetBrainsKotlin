import org.junit.Assert.assertEquals
import org.junit.Test

@Test
fun testSimpleExpression() {
    assertEquals(7, parser.parse("3+4"))
}

@Test
fun testComplexExpression() {
    assertEquals(12, parser.parse("(3+4)*2"))
}

@Test
fun testNegativeNumber() {
    assertEquals(-5, parser.parse("-5"))
}

@Test(expected = Exception::class)
fun testInvalidExpression() {
    parser.parse("3+")
}

@Test(expected = Exception::class)
fun testMissingClosingParenthesis() {
    parser.parse("(3+4")
}
