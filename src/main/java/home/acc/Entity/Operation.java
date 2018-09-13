package home.acc.Entity;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
//@Table(name="Operation", catalog="java-home-acc", schema="DBO")
@NamedQueries({
@NamedQuery(name = "getAllOperations", query = "select o from Operation o"),
@NamedQuery(name = "getLastOperations", query = "select o from Operation o order by date desc")
})
public class Operation implements Serializable{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private Float sum;
    private String comment;


    public void setAll(@NotNull Date date, @NotNull String name, @NotNull Float sum) {
        this.date = date;
        this.name = name;
        this.sum = sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
