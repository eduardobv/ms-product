package com.edu.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsProductApplicationTests {

	@Test
	void contextLoads() {
		assertThat(this).isNotNull();
	}

}
