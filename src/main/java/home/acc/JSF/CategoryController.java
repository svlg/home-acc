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

//    @Inject
//    private ViewController viewController;

    private Category category = new Category();

    public CategoryController() {
//        if (operation == null) this.operation = new Operation();
//
//        if (operation.getDate() == null)
//            operation.setDate(Calendar.getInstance().getTime());
    }

//    public String saveCategory() {
//        accEJB.saveCategory(category);
//        return "form_categoryList.xhtml";
//    }

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
}
