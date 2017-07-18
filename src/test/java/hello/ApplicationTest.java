/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {Application.class})
public class ApplicationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetSample() throws Exception {
		PaymentMethod[] arr = restTemplate.getForObject("/sample", PaymentMethod[].class);
		Set<PaymentMethod> paymentMethods = new TreeSet<>(Arrays.asList(arr));

		Set<PaymentMethod> testDataPaymentMethods = readTestData("test-sample-resp.json");

		assertTrue(paymentMethods.equals(testDataPaymentMethods));

	}

	private Set<PaymentMethod> readTestData(String filename) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("test-sample-resp.json");
		PaymentMethod[] testDataArr = mapper.readValue(is, PaymentMethod[].class);
		return new TreeSet<>(Arrays.asList(testDataArr));
	}


}
