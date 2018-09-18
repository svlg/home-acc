package home.acc;

import home.acc.Entity.Category;
import home.acc.Entity.Operation;
import org.primefaces.model.TreeNode;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.List;

@Remote
public interface AccEJBRemote {
    List<Operation> getOperationList();
    List<Operation> getLastOperationList();
    Operation addOperation(@NotNull Operation operation);

    List<Category> getCategoryList();
    List<Category> getCategoryListRoot();
    TreeNode getCategoryTree();
}
