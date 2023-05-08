package dev.akuniutka.cbrratesbot.config;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.dto.DateForFilter;
import dev.akuniutka.cbrratesbot.dto.CbrServiceResponse;
import dev.akuniutka.cbrratesbot.dto.ExchangeRatesContainer;
import dev.akuniutka.cbrratesbot.service.CbrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class BotConfig {
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

//    @Bean
//    public CharacterEncodingFilter characterEncodingFilter() {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding(StandardCharsets.UTF_8.name());
//        filter.setForceEncoding(true);
//        return filter;
//    }
}
