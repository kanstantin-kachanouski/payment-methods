package hello;

import lombok.Data;

@Data
public class PaymentMethod {
    private int planId;
    private String payorCode;
    private String planName;
}
