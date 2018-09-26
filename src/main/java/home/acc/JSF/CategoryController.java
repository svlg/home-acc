package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Category;
import org.primefaces.event.SelectEvent;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.io.Serializable;

//@Named
@ManagedBean
@RequestScoped
public class CategoryController implements Serializable {

    @Inject
    private AccEJB accEJB;

    private Category category = new Category();

    public CategoryController() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void onCategoryChosen(SelectEvent event) {
        Category cat = (Category) event.getObject();
        category.setParent(cat);
    }

    public String saveCategory() {
        //accEJB.saveCategory(viewController.getSelectedCategory());
        accEJB.saveCategory(category);
        return "index.xhtml";
    }

    public Category.Type[] getTypes(){
        return Category.Type.values();
    }

}
