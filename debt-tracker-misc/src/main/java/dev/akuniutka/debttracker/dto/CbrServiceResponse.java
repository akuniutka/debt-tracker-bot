package dev.akuniutka.debttracker.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetCursOnDateXMLResponse", namespace = "http://web.cbr.ru/")
public class CbrServiceResponse {
    @XmlElement(name = "GetCursOnDateXMLResult", namespace = "http://web.cbr.ru/")
    private ExchangeRatesContainer exchangeRatesContainer;
}
