package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Operation;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;

@Named
@RequestScoped
public class OperationController implements Serializable {

    @Inject
    private AccEJB accEJB;
    private Operation operation = new Operation();

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
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Operation created", "id = " + operation.getId()));
//        else
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Operation creation failed", ""));
        operation = null;
        return "index.xhtml";
    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }


}
