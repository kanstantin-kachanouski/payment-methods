package hello;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.Person;
import model.SubscribedPerson;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

@Slf4j  // this is to initialize and use log.
public class TestDataManipulationTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPopulateInCode() throws Exception {
        Person person = new Person();
        person.setDateOfBirth("2000-01-01");
        person.setFirstName("Santuccio");
        person.setGender("M");
        person.setLastName("Salieri");
        person.setRelationshipToSubscriber("spouse");

        SubscribedPerson subscribedPerson = new SubscribedPerson();
        subscribedPerson.setLabcorpPayerCode("001");
        subscribedPerson.setServiceDate("2017-07-19");
        subscribedPerson.setSubscriber(person);
        subscribedPerson.setSubscriberId("100");

        assertNotNull(person);
        assertNotNull(subscribedPerson);
        assertNotNull(subscribedPerson.getSubscriber());

        // convert to JSON
        String subscribedPersonJSON = mapper.writeValueAsString(subscribedPerson);
        log.info(String.format("subscribedPersonJSON = '%s'", subscribedPersonJSON));
    }

    @Test
    public void testReadFromFile() throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("SubscribedPerson.json");
        SubscribedPerson subscribedPerson = mapper.readValue(is, SubscribedPerson.class);

        assertNotNull(subscribedPerson);
        assertNotNull(subscribedPerson.getSubscriber());
    }
}
