package dev.akuniutka.debttracker.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.akuniutka.debttracker.dto.ExchangeRate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@TestConfiguration
public class BotTestsConfig {
    private static final String TEST_DATA_FILE_NAME = "/exchangeRates.json";

    @Bean
    public List<ExchangeRate> exchangeRates() throws IOException {
        URL url = getClass().getResource(TEST_DATA_FILE_NAME);
        if (url == null) {
            fail("cannot read test data file");
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(url, new TypeReference<List<ExchangeRate>>() {});
    }
}
