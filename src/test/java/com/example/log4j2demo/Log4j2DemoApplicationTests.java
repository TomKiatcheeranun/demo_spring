package com.example.log4j2demo;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;

//import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Log4j2DemoApplicationTests {

	@Test
	public void contextLoads() {

	}

//	@Test
//	public void testHome() throws Exception {
//		ResponseEntity<String> entity = new TestRestTemplate()
//				.getForEntity("http://localhost:8080", String.class);
//		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(entity.getBody()).isEqualTo("This is sample page Welcome AYCAP from TIDC");
//	}

}
