package hello.type_converter.formatter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        // when, then
        Number result = formatter.parse("1,000", Locale.KOREA);
        // given
        assertThat(result).isEqualTo(1000L);
    }

    @Test
    void print() {
        // when, then
        String result = formatter.print(1000, Locale.KOREA);
        // given
        assertThat(result).isEqualTo("1,000");
    }
}