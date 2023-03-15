package rs.raf.demo.model;


import rs.raf.demo.permiss.Permiss;

import javax.persistence.*;

@Entity
public class Permisije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Permiss permission;
    private Long userPermission;

    @ManyToOne
    private User user;

    public Permiss getPermission() {
        return permission;
    }

    public void setPermission(Permiss permission) {
        this.permission = permission;
    }

    public Long getId() {
        return id;
    }

    public Long getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(Long userPermission) {
        this.userPermission = userPermission;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Permisije{" +
                "permission=" + permission +
                '}';
    }
}
