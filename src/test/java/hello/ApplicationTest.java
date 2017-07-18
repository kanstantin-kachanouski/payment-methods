package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;

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
