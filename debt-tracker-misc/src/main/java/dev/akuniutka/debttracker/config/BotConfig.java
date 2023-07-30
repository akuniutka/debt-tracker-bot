package dev.akuniutka.debttracker.config;

import dev.akuniutka.debttracker.dto.ExchangeRate;
import dev.akuniutka.debttracker.dto.DateForFilter;
import dev.akuniutka.debttracker.dto.CbrServiceResponse;
import dev.akuniutka.debttracker.dto.ExchangeRatesContainer;
import dev.akuniutka.debttracker.service.CbrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;

@Configuration
@EntityScan(basePackages = {"dev.akuniutka.debttracker", "dev.akuniutka.cbrratesbot"})
@EnableJpaRepositories(basePackages = {"dev.akuniutka.debttracker", "dev.akuniutka.cbrratesbot"})
@ComponentScan(basePackages = {"dev.akuniutka.debttracker", "dev.akuniutka.cbrratesbot"})
@Slf4j
public class BotConfig {

    // TODO: limit Swagger to custom endpoints only (with Docket bean)

    @Bean
    public CbrService cbrService() throws SOAPException {
        CbrService cbrService = new CbrService();
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory newSoapMessageFactory = new SaajSoapMessageFactory(messageFactory);
        cbrService.setMessageFactory(newSoapMessageFactory);

        jaxb2Marshaller.setClassesToBeBound(
                DateForFilter.class,
                CbrServiceResponse.class,
                ExchangeRatesContainer.class,
                ExchangeRate.class
        );

        cbrService.setMarshaller(jaxb2Marshaller);
        cbrService.setUnmarshaller(jaxb2Marshaller);
        log.info("CbrService created");

        return cbrService;
    }
}
