package home.acc.JSF;

import home.acc.AccEJB;
import home.acc.Entity.Category;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CategoryConverter implements Converter {

    @Inject
    private AccEJB accEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String
            value) {

        if (value.equals("")) return null;

        Long id = Long.parseLong(value);
        return (Category) accEJB.findObjectByID(Category.class, id);

    }

    public String getAsString(FacesContext ctx, UIComponent component, Object
            value) {
        return ((Category) value).getId().toString();
    }

}
