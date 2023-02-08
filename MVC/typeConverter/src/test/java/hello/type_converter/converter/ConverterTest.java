package hello.type_converter.converter;

import hello.type_converter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConverterTest {
    @Test
    void stringToInteger() {
        // when
        StringToIntegerConverter converter = new StringToIntegerConverter();
        // then
        Integer result = converter.convert("10");
        // given
        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        // when
        IntegerToStringConverter converter = new IntegerToStringConverter();
        // then
        String result = converter.convert(10);
        // given
        assertThat(result).isEqualTo("10");
    }

    @Test
    void stringToIpPort() {
        // when
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        // then
        String result = converter.convert(source);
        // given
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void ipPortToString() {
        // when
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        // then
        IpPort result = converter.convert(source);
        // given
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
