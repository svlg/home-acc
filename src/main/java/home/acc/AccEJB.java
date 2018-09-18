package home.acc;

import home.acc.Entity.Category;
import home.acc.Entity.Operation;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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

    @Override
    public List<Category> getCategoryList() {
        return em.createNamedQuery("getCategoryList").getResultList();
    }

    @Override
    public List<Category> getCategoryListRoot() {
        return em.createNamedQuery("getCategoryListRoot").getResultList();
    }

    @Override
    public TreeNode getCategoryTree() {
        TreeNode tree = new DefaultTreeNode();
        List<Category> list =  em.createNamedQuery("getCategoryListRoot").getResultList();

        list.forEach(cat -> {TreeNode root = new DefaultTreeNode(cat, tree);
            addNodesToTree(cat, root);
        });
        return tree;
    }

    private void addNodesToTree(Category cat, TreeNode root){
        cat.getChilds().forEach(child -> {TreeNode node = new DefaultTreeNode(child, root);
            addNodesToTree(child, node);
        });
    }

    public @NotNull Category saveCategory(@NotNull Category category) {
        try {
            em.persist(category);
        } catch (ValidationException e) {
            e.printStackTrace();
            return null;
        }

        return category;
    }

    public Object findObjectByID(Class objClass, long id) {
        return em.find(objClass, id);
    }
}

