package fortune.haengunseserver.domain.calendar.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "item")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LunToSolResponse {

    @JacksonXmlProperty(localName = "solYear")
    private int solYear;

    @JacksonXmlProperty(localName = "solMonth")
    private int solMonth;

    @JacksonXmlProperty(localName = "solDay")
    private int solDay;

    public LocalDate toLocalDate() {
        return LocalDate.of(solYear, solMonth, solDay);
    }
}
