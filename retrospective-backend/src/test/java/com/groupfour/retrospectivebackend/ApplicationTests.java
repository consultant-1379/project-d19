package com.groupfour.retrospectivebackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		int checker = 1;
		assertTrue(checker==1);
	}


}
