package by.javatr.bicrent.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends Entity {
    private BigDecimal amount;
    private VirtualCard virtualCard;
    private Company company;
    private Boolean ifPaid;
    private Date date;

}
