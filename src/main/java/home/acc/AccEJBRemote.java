package home.acc;

import home.acc.Entity.Operation;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Remote
public interface AccEJBRemote {
    List<Operation> listOperations();
    Operation addOperation(@NotNull Operation operation);
}
