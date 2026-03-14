package junprogug.junprogug.repository;

import junprogug.junprogug.model.Computer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findByNameContainingIgnoreCase(String name, Sort sort);

    List<Computer> findByAccountingDate(LocalDate accountingDate, Sort sort);
}
