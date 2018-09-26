package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Category;
import home.acc.Entity.Operation;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
//@Named
//@SessionScoped
public class OperationController implements Serializable {

    @Inject
    private AccEJB accEJB;
    private Operation operation = new Operation();
    private String stringSum;

    public OperationController() {
//        if (operation == null) this.operation = new Operation();
//
//        if (operation.getDate() == null)
//            operation.setDate(Calendar.getInstance().getTime());
    }

    public String doCreateOperation() {
        accEJB.addOperation(operation);
       // if (operation == null)
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation created", "id = " + operation.getCategoryName()));
//        else
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation creation failed", ""));
        operation = new Operation();
        return "index.xhtml";
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void onCategoryChosen(SelectEvent event) {
        Category cat = (Category) event.getObject();
        operation.setCategory(cat);
    }

    public void onSumUpdate(SelectEvent event){
        Float calcSum = (Float) event.getObject();
        operation.setSum(100F);
    }

    public String getStringSum() {
        return stringSum;
    }

    public void setStringSum(String stringSum) {
        this.stringSum = stringSum;
    }
}
