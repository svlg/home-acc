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
import javax.persistence.Query;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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

    //@Override
    public List<Object[]> getReport() {

        Date currentDate = Calendar.getInstance().getTime();
        return getReport(currentDate);
    }

    public List<Object[]> getReport(Date currentDate) {

        if (currentDate == null) currentDate = Calendar.getInstance().getTime();
        List<Object[]> list = em.createNamedQuery("getReportExpense").setParameter("date", currentDate).getResultList();
        return list;
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

    public @NotNull Category saveCategoryNative(@NotNull Category category) {
        try {
           //em.persist(category);
            Query query = em.createNativeQuery("SET IDENTITY_INSERT Category ON "+
                    "INSERT INTO Category (ID, name, groupId, parentId, isGroup, type) " +
                    "VALUES(:id, :name, :groupId, :parentId, :isGroup, :typeStr) "+
                    //"ON DUPLICATE KEY UPDATE name = :name, groupId = :groupId, parentId = :parentId, isGroup = :isGroup, type = :typeStr " +
                    "SET IDENTITY_INSERT Category OFF ");
            query.setParameter("id", category.getId());
            query.setParameter("name", category.getName());
            if (category.getCategoryGroup() != null)
                query.setParameter("groupId", category.getCategoryGroup().getId());
            else
                query.setParameter("groupId", null);
            if (category.getParent() != null)
                query.setParameter("parentId", category.getParent().getId());
            else
                query.setParameter("parentId", null);
            query.setParameter("isGroup", category.getisGroup());
            query.setParameter("typeStr", category.getType().name());
            query.executeUpdate();

        } catch (ValidationException e) {
            e.printStackTrace();
            return null;
        }

        return category;
    }

    public @NotNull Object saveObject(@NotNull Object object) {
        try {
            em.persist(object);
        } catch (ValidationException e) {
            e.printStackTrace();
            return null;
        }

        return object;
    }

    public Object findObjectByID(Class objClass, long id) {
        return em.find(objClass, id);
    }

}

