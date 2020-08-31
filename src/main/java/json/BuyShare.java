package json;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement
public class BuyShare {

    @DecimalMin("0.00")
    BigDecimal shares;

    @Pattern(regexp = "^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")
    String date;

    String ticker;

    public String getDate() {
        return date;
    }

    public BigDecimal getShares() {
        return shares;
    }

    public String getTicker() {
        return ticker;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setShares(BigDecimal shares) {
        this.shares = shares;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
