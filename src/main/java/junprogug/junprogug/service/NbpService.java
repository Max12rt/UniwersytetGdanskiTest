package junprogug.junprogug.service;

import junprogug.junprogug.DTO.NbpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbpService {
    private final RestTemplate restTemplate;

    public BigDecimal getUsdRate(LocalDate date) {
        LocalDate checkDate = date;
        for (int i = 0; i < 7; i++) {
            try {
                String url = "https://api.nbp.pl/api/exchangerates/rates/a/usd/" + checkDate + "?format=json";
                NbpResponse response = restTemplate.getForObject(url, NbpResponse.class);

                if (response != null && !response.rates().isEmpty()) {
                    return response.rates().get(0).mid();
                }
            } catch (HttpClientErrorException.NotFound e) {
                log.warn("Rate for {} not found, checking previous day...", checkDate);
                checkDate = checkDate.minusDays(1);
            }
        }
        throw new RuntimeException("Unable to retrieve exchange rate from NBP");
    }
}
