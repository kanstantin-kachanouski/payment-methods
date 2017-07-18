package hello;

import lombok.Data;

@Data
public class PaymentMethod implements Comparable<PaymentMethod> {
    private int planId;
    private String payorCode;
    private String planName;

    @Override
    public int compareTo(PaymentMethod o) {
        return this.planId - o.planId;
    }
}
