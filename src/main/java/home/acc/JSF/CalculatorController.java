package home.acc.JSF;

import org.primefaces.event.SelectEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@RequestScoped
public class CalculatorController {

    private String stringSum;

    public String getStringSum() {
        return stringSum;
    }

    public void setStringSum(String stringSum) {
        this.stringSum = stringSum;
    }

    public Float calculateSum(SelectEvent event){

        return 200F;
    }
}
