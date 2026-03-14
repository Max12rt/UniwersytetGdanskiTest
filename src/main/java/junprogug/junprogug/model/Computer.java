package junprogug.junprogug.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "komputery")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JacksonXmlProperty(localName = "nazwa")
    private String name;

    @JacksonXmlProperty(localName = "data_ksiegowania")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate accountingDate;

    @JacksonXmlProperty(localName = "koszt_USD")
    private BigDecimal costUSD;

    @JacksonXmlProperty(localName = "koszt_PLN")
    private BigDecimal costPLN;
}
