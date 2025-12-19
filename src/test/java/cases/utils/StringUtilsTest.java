package cases.utils;

import base.BaseTestCase;
import org.example.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class StringUtilsTest extends BaseTestCase {
    @Test
    public void testIsNullOrEmpty() {
        // Chia lam bao nhieu cases?
        // Case 1: input == null
        assertTrue(StringUtils.isNullOrEmpty(null)); // case 1 => expected: true
        // Case 2: input == ""
        assertTrue(StringUtils.isNullOrEmpty("")); // case 2 => expected: true
        // Case 3: input != "" = "kjhfjfshgjdf"
        assertFalse(StringUtils.isNullOrEmpty("hgjhgjshdvjsvcbvcxbj")); // case 3 => expected: false
        assertFalse(StringUtils.isNullOrEmpty("            "));
        // Da hoan thanh muc tieu cua method => Passed
    }

    @Test
    public void testIsBlank() {
        // case 1: input null => true
        assertTrue(StringUtils.isBlank(null));
        // case 2: input rong => true
        assertTrue(StringUtils.isBlank(""));
        // case 3: input blank
        assertTrue(StringUtils.isBlank("            "));
        // case 4: input not null not blank not empty
        assertFalse(StringUtils.isBlank("hgjhgjshdvjsvcbvcxbj"));
    }

    @Test
    public void testCapitalize() {
        assertNull(StringUtils.capitalize(null));
        assertEquals("", StringUtils.capitalize(""));
        assertEquals("Abcd", StringUtils.capitalize("abcd"));
        assertEquals("Uppercase", StringUtils.capitalize("UPPERCASE"));
        assertEquals("Love You To The Moon", StringUtils.capitalize("love you to the moon"));
        assertEquals("@#$%", StringUtils.capitalize("@#$%"));
        assertEquals("   ", StringUtils.capitalize("   "));
        assertEquals("123", StringUtils.capitalize("123"));
        assertEquals(" Abc", StringUtils.capitalize(" abc"));
        assertEquals("Ă", StringUtils.capitalize("ă"));
        assertEquals("A1b2c3", StringUtils.capitalize("a1b2c3"));
        assertEquals("1a2b3c", StringUtils.capitalize("1a2b3c"));
    }

    @ParameterizedTest
    @CsvSource(
            value = {
                    "null, null",
                    "'', ''",
                    "abcd, Abcd",
                    "UPPERCASE, Uppercase",
                    "'love you to the moon', 'Love You To The Moon'",
                    "'@#$%', '@#$%'",
                    "'   ', '   '",
                    "123, 123",
                    "' abc', ' Abc'",
                    "ă, Ă",
                    "a1b2c3, A1b2c3",
                    "1a2b3c, 1a2b3c",
                    "😀, 😀"
            }, nullValues = "null")
    void testCapitalize(String input, String expected) {
        assertEquals(expected, StringUtils.capitalize(input));
    }

    @Test
    public void testReverse() {
        assertNull(StringUtils.reverse(null));
        assertEquals("", StringUtils.reverse(""));
        assertEquals("dcba", StringUtils.reverse("abcd"));
        assertEquals("ESACREPPU", StringUtils.reverse("UPPERCASE"));
        assertEquals("noom eht ot uoy evol", StringUtils.reverse("love you to the moon"));
        assertEquals("%$#@", StringUtils.reverse("@#$%"));
        assertEquals("   ", StringUtils.reverse("   "));
        assertEquals("321", StringUtils.reverse("123"));
        assertEquals("cba ", StringUtils.reverse(" abc"));
        assertEquals(" cba", StringUtils.reverse("abc "));
        assertEquals("ă", StringUtils.reverse("ă"));
        assertEquals("3c2b1a", StringUtils.reverse("a1b2c3"));
        assertEquals("c3b2a1", StringUtils.reverse("1a2b3c"));
        assertEquals("😀", StringUtils.reverse("😀"));
        assertEquals("cba\n", StringUtils.reverse("\nabc"));
        assertEquals("racecar", StringUtils.reverse("racecar"));

        // Additional edge cases
        assertEquals("A", StringUtils.reverse("A"));
        assertEquals(" ", StringUtils.reverse(" "));
        assertEquals("\n\t\r", StringUtils.reverse("\r\t\n"));
        assertEquals("ba", StringUtils.reverse("ab"));
        assertEquals("cba", StringUtils.reverse("abc"));
        assertEquals("a b a", StringUtils.reverse("a b a"));
        assertEquals("a@a", StringUtils.reverse("a@a"));
        assertEquals(")(*&^%", StringUtils.reverse("%^&*()"));
    }
    @Test
    public void testContainsIgnoreCase() {
        // Null cases
        assertFalse(StringUtils.containsIgnoreCase(null, "abc"));
        assertFalse(StringUtils.containsIgnoreCase("abc", null));
        assertFalse(StringUtils.containsIgnoreCase(null, null));

        // Empty cases
        assertTrue(StringUtils.containsIgnoreCase("", ""));
        assertTrue(StringUtils.containsIgnoreCase("abc", ""));
        assertTrue(StringUtils.containsIgnoreCase("", ""));

        // Exact matches
        assertTrue(StringUtils.containsIgnoreCase("hello world", "hello"));
        assertTrue(StringUtils.containsIgnoreCase("hello world", "world"));

        // Case insensitive matches
        assertTrue(StringUtils.containsIgnoreCase("HELLO WORLD", "hello"));
        assertTrue(StringUtils.containsIgnoreCase("hello WORLD", "Hello"));
        assertTrue(StringUtils.containsIgnoreCase("Hello World", "WORLD"));

        // Partial matches
        assertTrue(StringUtils.containsIgnoreCase("hello world", "lo wo"));
        assertTrue(StringUtils.containsIgnoreCase("HELLO WORLD", "lo wo"));

        // Not found cases
        assertFalse(StringUtils.containsIgnoreCase("hello world", "test"));
        assertFalse(StringUtils.containsIgnoreCase("hello world", "HELLO WORLD EXTRA"));

        // Special characters
        assertTrue(StringUtils.containsIgnoreCase("hello@world#123", "@WORLD"));
        assertTrue(StringUtils.containsIgnoreCase("test123", "TEST"));

        // Unicode characters
        assertTrue(StringUtils.containsIgnoreCase("hello ăâđêôơư", "ĂÂĐ"));
        assertTrue(StringUtils.containsIgnoreCase("TEST ĂÂĐ", "test ăâđ"));

        // Edge cases
        assertTrue(StringUtils.containsIgnoreCase("   ", " "));
        assertTrue(StringUtils.containsIgnoreCase("abc", "b"));
        assertTrue(StringUtils.containsIgnoreCase("abc", "B"));
        assertTrue(StringUtils.containsIgnoreCase("multiple words test", "WORDS TEST"));
    }
}
