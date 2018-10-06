package home.acc.XML;

import home.acc.Entity.Category;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CategoryAdapter extends XmlAdapter<Object, Category> {


    @Override
    public Category unmarshal(Object v) throws Exception {

        Category category = new Category();
        Element element = (Element) v;
        category.setId(Long.parseLong(element.getTextContent()));

        return category;
    }

    @Override
    public Object marshal(Category v) throws Exception {
        return null;
    }

}
