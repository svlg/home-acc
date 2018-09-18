package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Category;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ViewController {

    @Inject
    private AccEJB accEJB;

    private Category selectedCategory;
    private TreeNode root;
    private TreeNode selectedNode;

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    @PostConstruct
    public void init() {
        root = accEJB.getCategoryTree();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void selectCategoryFromDialog() {
        PrimeFaces.current().dialog().closeDynamic(selectedNode.getData());
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    public void setParentSelectedCategory(Category parent) {
        this.selectedCategory.setParent(parent);
    }

    public void onRowSelect(SelectEvent event) {
        this.selectedCategory = (Category) event.getObject();
    }

//    public void onNodeSelect(SelectEvent event) {
//        this.selectedNode = (TreeNode) event.getObject();
//    }

//    public void onCategoryChosen(SelectEvent event) {
//        Category cat = (Category) event.getObject();
//        selectedCategory.setParent(cat);
//    }

    public void chooseCategory(){

        Map<String,Object> options = new HashMap<>();
        //options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("showEffect", "clip");
        options.put("hideEffect", "clip");
        PrimeFaces.current().dialog().openDynamic("form_categoryList.xhtml", options, null);
    }


}
