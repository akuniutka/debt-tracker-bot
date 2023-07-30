package dev.akuniutka.debttracker.dto;

import dev.akuniutka.debttracker.dto.ExchangeRate;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetCursOnDateXmlResult")
public class ExchangeRatesContainer {
    @XmlElementWrapper(name = "ValuteData", namespace = "")
    @XmlElement(name = "ValuteCursOnDate", namespace = "")
    private List<ExchangeRate> exchangeRates;
}
