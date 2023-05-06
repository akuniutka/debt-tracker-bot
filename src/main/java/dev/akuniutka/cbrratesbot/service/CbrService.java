package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.dto.DateForFilter;
import dev.akuniutka.cbrratesbot.dto.CbrServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
public class CbrService extends WebServiceTemplate {
    @Value("${cbr.api.url}")
    private String cbrApiUrl;

    public List<ExchangeRate> getExchangeRates() throws DatatypeConfigurationException {
        final DateForFilter dateForFilter = new DateForFilter();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        dateForFilter.setValue(xmlGregorianCalendar);

        log.debug(dateForFilter.toString());
        CbrServiceResponse response = (CbrServiceResponse) marshalSendAndReceive(cbrApiUrl, dateForFilter);

        if (response == null) {
            throw new IllegalStateException("Could not get response from CBR service");
        }

        final List<ExchangeRate> exchangeRates = response.getExchangeRatesContainer().getExchangeRates();
        exchangeRates.forEach(exchangeRate -> exchangeRate.setCurrency(exchangeRate.getCurrency().trim()));
        return exchangeRates;
    }
}
