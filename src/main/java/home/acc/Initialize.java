package home.acc;

import home.acc.Entity.Operation;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;

@Singleton
@Startup

public class Initialize {

    @Inject
    AccEJB accEJB;
    private Operation operation1;

    @PostConstruct
    private void postConstruct() {
//        operation1 = new Operation();
//        operation1.setAll(Calendar.getInstance().getTime(), "operation1", 100F);
//        accEJB.addOperation(operation1);
    }
}
