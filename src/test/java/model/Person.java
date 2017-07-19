package model;

import lombok.Data;

@Data
public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String relationshipToSubscriber;
}
