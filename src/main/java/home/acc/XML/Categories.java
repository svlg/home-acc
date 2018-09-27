package home.acc.XML;

import home.acc.Entity.Category;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
@XmlRootElement
public class Categories {

    @XmlElements(@XmlElement(name="category", type=Category.class))
    private List listCategories;

    public List getListcategories() {
        return listCategories;
    }

    public void setListcategories(List listcategories) {
        this.listCategories = listcategories;
    }
}
