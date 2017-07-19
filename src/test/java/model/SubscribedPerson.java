package model;

import lombok.Data;

@Data
public class SubscribedPerson {
    private String labcorpPayerCode;
    private String serviceDate;
    private Person subscriber;
    private String subscriberId;
}
