package home.acc.Entity;

import home.acc.XML.CategoryAdapter;
import home.acc.XML.DateAdapter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
//@Table(name="Operation", catalog="java-home-acc", schema="DBO")
@NamedQueries({
        @NamedQuery(name = "getAllOperations", query = "select o from Operation o"),
        @NamedQuery(name = "getLastOperations", query = "select o from Operation o order by date desc"),
        @NamedQuery(name = "getReportExpense", query = "select o.category.categoryGroup.name, sum(o.sum) from Operation o " +
                "where DATEFROMPARTS(YEAR(o.date),MONTH(o.date),1) = DATEFROMPARTS(YEAR(:date),MONTH(:date),1) and o.category.type = 'expense'" +
                "group by o.category.categoryGroup.name "),
})
@XmlType
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Operation implements Serializable{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY, optional = true, cascade=CascadeType.MERGE)
    @JoinColumn(name = "category_fk", nullable = false)
    @XmlElement
    @XmlJavaTypeAdapter(CategoryAdapter.class)
    private Category category;

    @NotNull
    @Column(nullable = false)
    @XmlElement
    private Float sum;

    @XmlElement
    private String comment;

    public Operation() {
        this.date = Calendar.getInstance().getTime();
        //this.sum = 0F;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCategoryName(){
        return category.getName();
    }
}
