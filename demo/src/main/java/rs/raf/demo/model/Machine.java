package rs.raf.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import rs.raf.demo.permiss.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
// Ova anotacija oznacava klasu kao JPA (Java Persistens API) entitet, sto znaci da ce biti mapirana na tabelu u bazi podataka
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    @JsonIgnore
    @ManyToOne
    private User user;
    private Boolean active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "machine")
    private List<Period> periods = new ArrayList<>();
    private String angularDate;
//    private boolean busy;

//    public boolean isBusy() {
//        return busy;
//    }
//
//    public void setBusy(boolean busy) {
//        this.busy = busy;
//    }

    public String getAngularDate() {
        return angularDate;
    }

    public void setAngularDate(String angularDate) {
        this.angularDate = angularDate;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
