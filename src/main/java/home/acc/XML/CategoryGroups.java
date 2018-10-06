package home.acc.XML;

import home.acc.Entity.CategoryGroup;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
@XmlRootElement
public class CategoryGroups {

    @XmlElements(@XmlElement(name="categoryGroup", type=CategoryGroup.class))
    List categoryGroup;

    public List getCategoryGroup() {
        return categoryGroup;
    }
}
