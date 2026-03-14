package junprogug.junprogug;

import junprogug.junprogug.model.Computer;
import junprogug.junprogug.repository.ComputerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ComputerRepositoryTest {

    @Autowired
    private ComputerRepository repository;

    @Test
    void shouldFindComputerByFragmentName() {

        repository.save(new Computer("DELL Latitude", LocalDate.now(), new BigDecimal("543"), null));


        List<Computer> results = repository.findByNameContainingIgnoreCase("Lat", null);


        assertFalse(results.isEmpty());
        assertTrue(results.get(0).getName().contains("Latitude"));
    }
}
