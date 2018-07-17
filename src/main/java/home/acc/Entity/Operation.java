package home.acc.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
//@Table(name="Operation", catalog="java-home-acc", schema="DBO")
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

    public void setAll(@NotNull Date date, @NotNull String name, @NotNull Float sum) {
        this.date = date;
        this.name = name;
        this.sum = sum;
    }

    public void setName(String name) {
        this.name = name;
    }
}
