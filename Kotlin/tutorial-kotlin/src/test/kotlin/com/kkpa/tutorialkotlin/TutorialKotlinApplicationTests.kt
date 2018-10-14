package com.kkpa.tutorialkotlin

import com.kkpa.tutorialkotlin.objects.ObjectTests
import com.kkpa.tutorialkotlin.strings.StringTests
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.springframework.boot.test.context.SpringBootTest


@RunWith(Suite::class)
@Suite.SuiteClasses(
			ObjectTests::class,
			StringTests::class
		)
@SpringBootTest
class TutorialKotlinApplicationTests {

	@Test
	fun contextLoads() {
		println("Running Tests...")
	}

}
