package home.acc.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
//@Table(name="Category", catalog="java-home-acc", schema="DBO")
@NamedQueries({
        @NamedQuery(name = "getCategoryList", query = "select c from Category c"),
        @NamedQuery(name = "getCategoryListRoot", query = "select c from Category c where parent is null")
})
public class Category implements Serializable{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY, optional = true, cascade=CascadeType.MERGE)
    @JoinColumn (name = "parentId")
    private Category parent;

    @Column(columnDefinition = "BOOLEAN")
    @NotNull
    private Boolean isGroup;

    @OneToMany(cascade=CascadeType.MERGE,fetch= FetchType.LAZY, mappedBy = "parent")
    private Set<Category> childs;

    public enum Type{
        income,
        expense
    }

    public Set<Category> getChilds() {
        return childs;
    }

    public void setChilds(Set<Category> childs) {
        this.childs = childs;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getParentName() {
        if (parent != null)
            return parent.getName();
        else
            return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Boolean getisGroup() {
        return isGroup;
    }

    public void setisGroup(Boolean isgroup) {
        isGroup = isgroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
