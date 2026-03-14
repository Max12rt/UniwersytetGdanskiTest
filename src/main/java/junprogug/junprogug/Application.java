package junprogug.junprogug;

import junprogug.junprogug.model.Computer;
import junprogug.junprogug.repository.ComputerRepository;
import junprogug.junprogug.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ComputerService service;
    private final ComputerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(@NonNull String... args) {
        service.initData();
        runMenu();
    }

    private void runMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenuOptions();
                String choice = scanner.nextLine();

                if ("0".equals(choice)) break;

                handleUserChoice(choice, scanner);
            }
        }
    }

    private void printMenuOptions() {
        System.out.println("\n--- Computer Inventory System ---");
        System.out.println("1. View All (Sorted)");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Date");
        System.out.println("4. Sync with NBP (Today)");
        System.out.println("0. Exit");
        System.out.print("Selection: ");
    }

    private void handleUserChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1" -> {
                System.out.print("Sort by: 1. Name | 2. Date: ");
                Sort sort = "2".equals(scanner.nextLine()) ? Sort.by("accountingDate") : Sort.by("name");
                displayResults(service.getAll(sort));
            }
            case "2" -> {
                System.out.print("Enter name fragment: ");
                displayResults(repository.findByNameContainingIgnoreCase(scanner.nextLine(), Sort.by("name")));
            }
            case "3" -> {
                System.out.print("Enter date (YYYY-MM-DD): ");
                try {
                    displayResults(repository.findByAccountingDate(LocalDate.parse(scanner.nextLine()), Sort.by("accountingDate")));
                } catch (Exception e) {
                    System.out.println("Error: Invalid date format.");
                }
            }
            case "4" -> {
                service.updateAllRates();
                System.out.println("Success: Database and XML updated.");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private void displayResults(List<Computer> list) {
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.printf("%n%-20s | %-12s | %-10s | %-10s%n", "Name", "Date", "USD", "PLN");
        System.out.println("-".repeat(60));
        list.forEach(c -> System.out.printf("%-20s | %-12s | %10.2f | %10.2f%n",
                c.getName(), c.getAccountingDate(), c.getCostUSD(), c.getCostPLN()));
    }
}
