package junprogug.junprogug.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@JacksonXmlRootElement(localName = "faktura")
@AllArgsConstructor
@NoArgsConstructor
public class Faktura {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "komputer")
    private List<Computer> komputery;

    public List<Computer> getKomputery() {
        return komputery;
    }
}
