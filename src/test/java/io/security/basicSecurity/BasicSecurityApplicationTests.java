package io.security.basicSecurity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicSecurityApplicationTests {

	@Test
	void contextLoads() {
		int number = 10;
		Assertions.assertThat(10).isEqualTo(number);
	}

}
