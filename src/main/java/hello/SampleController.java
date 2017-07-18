package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author www.epam.com
 */
@RestController
public class SampleController {
    private List<PaymentMethod> sampleMethods;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("sample-resp.json");
        PaymentMethod[] arr = null;
        try {
            arr = mapper.readValue(is, PaymentMethod[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        sampleMethods = Arrays.asList(arr);
    }

    @RequestMapping("/sample")
    public ResponseEntity<List<PaymentMethod>> listPaymentMethods() {
        return new ResponseEntity<List<PaymentMethod>>(sampleMethods, HttpStatus.OK);
    }

}
