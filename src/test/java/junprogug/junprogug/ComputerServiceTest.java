package junprogug.junprogug;

import junprogug.junprogug.DTO.NbpResponse;
import junprogug.junprogug.service.ComputerService;
import junprogug.junprogug.service.NbpService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
@SpringBootTest
class ComputerServiceTest {

    @Autowired
    private ComputerService computerService;

    @Test
    void shouldCalculatePlnCorrectly() {

        BigDecimal usd = new BigDecimal("543.00");
        BigDecimal rate = new BigDecimal("3.6878");
        BigDecimal expectedPln = new BigDecimal("2002.48");

        BigDecimal result = computerService.calculatePln(usd, rate);

        assertEquals(0, expectedPln.compareTo(result), "Розрахунок PLN має бути точним");
    }
}
