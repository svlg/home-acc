package home.acc.XML;

import home.acc.Entity.CategoryGroup;
import org.w3c.dom.Element;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@ApplicationScoped
public class CategoryGroupAdapter extends XmlAdapter<Object, CategoryGroup> {

    @Override
    public CategoryGroup unmarshal(Object v) throws Exception {

        CategoryGroup categoryGroup = new CategoryGroup();
        Element element = (Element) v;
        categoryGroup.setId(Long.parseLong(element.getTextContent()));
//        categoryGroup = (CategoryGroup) accEJB.findObjectByID(CategoryGroup.class, );
        return categoryGroup;
    }

    @Override
    public Object marshal(CategoryGroup v) throws Exception {
        return null;
    }
}
