package com.poula.anywaretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({
		"classpath*:applicationContext.xml"
})
public class AnywareTestApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AnywareTestApplication.class, args);
	}

}
