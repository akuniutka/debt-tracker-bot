package dev.akuniutka.debttracker.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ValuteCursOnDate")
public class ExchangeRate {
    @XmlElement(name = "Vname")
    private String currency;
    @XmlElement(name = "Vnom")
    private int units;
    @XmlElement(name = "Vcurs")
    private double value;
    @XmlElement(name = "Vcode")
    private String currencyNumericCode;
    @XmlElement(name = "VchCode")
    private String currencyAlphabeticCode;
}
