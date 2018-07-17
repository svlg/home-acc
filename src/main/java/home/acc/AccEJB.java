package home.acc;

import home.acc.Entity.Operation;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class AccEJB implements AccEJBRemote{

    @Inject
    private EntityManager em;

    @Override
    public List<Operation> listOperations() {
        return null;
    }

    public @NotNull Operation addOperation(@NotNull Operation operation) {
        try {
            em.persist(operation);
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        //operation.setName("operationChanged");
        return operation;
    }

}

