package home.acc.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "getCategoryGroupList", query = "select c from CategoryGroup c"),
        @NamedQuery(name = "getCategoryGroupBy1cId", query = "select c from CategoryGroup c where c.id_1c = :id_1c")
})
@XmlType
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryGroup {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @XmlElement

    private Long id;

    @NotNull
    @Column(nullable = false)
    @XmlElement
    private String name;

    @Column
    @XmlElement
    private Long id_1c;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement
    @XmlID
    public String getIdString() {
        return id.toString();
    }
    public void setIdString(String strId) {
        this.id = Long.parseLong(strId);
    }
}
