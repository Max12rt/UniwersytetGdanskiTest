# UniwersytetGdanskiTest
# Dokumentacja Zadania Rekrutacyjnego - Młodszy Programista

Aplikacja konsolowa w języku Java (Spring Boot) do przeliczania kosztów zakupu komputerów z USD na PLN na podstawie kursów NBP.

## 1. Wymagania systemowe
* **Java:** wersja 17 lub 21.
* **Maven:** (dołączony wrapper `mvnw` lub zainstalowany lokalnie).
* **Baza danych:** H2 (in-memory) – nie wymaga instalacji zewnętrznego silnika.

## 2. Funkcjonalność aplikacji
Aplikacja automatycznie realizuje scenariusz opisany w zadaniu:
1. **Inicjalizacja danych:** Przy starcie dodawane są 3 komputery:
   - ACER Aspire (kurs z 05.01.2026)
   - DELL Latitude (kurs z 11.01.2026)
   - HP Victus (kurs z 19.01.2026)
2. **Integracja z NBP:** Aplikacja pobiera kursy walut z API NBP. W przypadku dni wolnych (np. 11 stycznia to niedziela), system automatycznie szuka kursu z najbliższego poprzedniego dnia roboczego.
3. **Zapis danych:** Wyniki są zapisywane w relacyjnej bazie danych oraz eksportowane do pliku `faktura.xml` w formacie zgodnym z wymaganiami.
4. **Interfejs użytkownika:** Menu konsolowe umożliwia:
   - Wyświetlenie wszystkich rekordów z opcją sortowania (nazwa/data).
   - Wyszukiwanie po fragmencie nazwy.
   - Wyszukiwanie po konkretnej dacie księgowania.
   - Aktualizację wszystkich kursów do dzisiejszego kursu NBP.

## 3. Instrukcja uruchomienia

### Opcja A: Przez IntelliJ IDEA (Zalecane)
1. Otwórz IntelliJ IDEA.
2. Wybierz **File > Open** i wskaż folder projektu (zawierający plik `pom.xml`).
3. Poczekaj na załadowanie zależności Maven.
4. Uruchom klasę `Application.java` (znajduje się w `src/main/java/junprogug/junprogug/Application.java`).

### Opcja B: Przez linię komend (Terminal)
1. Wejdź do folderu głównego projektu.
2. Zbuduj projekt:
   ```bash
   mvn clean install