package home.acc;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DBProducer {
    @Produces
    @PersistenceContext(unitName = "accPU")
    private EntityManager em;
}
