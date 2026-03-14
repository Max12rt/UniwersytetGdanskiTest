package junprogug.junprogug.service;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import junprogug.junprogug.model.Computer;
import junprogug.junprogug.model.Faktura;
import junprogug.junprogug.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository repository;
    private final NbpService nbpService;

    @Transactional
    public void initData() {
        if (repository.count() > 0) return;

        List<Computer> list = List.of(
                createComputer("ACER Aspire", LocalDate.of(2026, 1, 5), new BigDecimal("345")),
                createComputer("DELL Latitude", LocalDate.of(2026, 1, 11), new BigDecimal("543")),
                createComputer("HP Victus", LocalDate.of(2026, 1, 19), new BigDecimal("346"))
        );

        repository.saveAll(list);
        syncXml();
        log.info("Initial data loaded and XML synchronized.");
    }

    private Computer createComputer(String name, LocalDate date, BigDecimal usd) {
        BigDecimal rate = nbpService.getUsdRate(date);
        BigDecimal pln = usd.multiply(rate).setScale(2, RoundingMode.HALF_UP);
        return new Computer(null, name, date, usd, pln);
    }

    public List<Computer> getAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Transactional
    public void updateAllRates() {
        List<Computer> all = repository.findAll();
        BigDecimal currentRate = nbpService.getUsdRate(LocalDate.now());

        for (Computer c : all) {
            c.setCostPLN(c.getCostUSD().multiply(currentRate).setScale(2, RoundingMode.HALF_UP));
        }
        repository.saveAll(all);
        syncXml();
    }

    public void syncXml() {
        try {
            List<Computer> all = repository.findAll();
            XmlMapper mapper = new XmlMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("faktura.xml"), new Faktura(all));
        } catch (Exception e) {
            log.error("Failed to update XML file: {}", e.getMessage());
        }
    }
}
