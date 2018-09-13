package home.acc;

import home.acc.Entity.Operation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
@LocalBean
public class AccEJB implements AccEJBRemote, Serializable {

    @Inject
    private EntityManager em;

    @Override
    public List<Operation> getOperationList() {
        return em.createNamedQuery("getLastOperations").getResultList();
    }

    @Override
    public List<Operation> getLastOperationList() {
        //return em.createQuery("select top 20 o from Operation o order by date desc").getResultList();
        return em.createNamedQuery("getLastOperations").setMaxResults(5).getResultList();
    }

    public @NotNull Operation addOperation(@NotNull Operation operation) {
        try {
            em.persist(operation);
        } catch (ValidationException e) {
            e.printStackTrace();
            return null;
        }

        //operation.setName("operationChanged");
        return operation;
    }

}

