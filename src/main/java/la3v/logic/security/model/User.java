package la3v.logic.security.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
@Entity
@Table(name = "oc_users")
public class User {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(User.class);

    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "uid")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private String confirmPassword;

    @ManyToMany
    @JoinTable(name = "oc_group_user", joinColumns = @JoinColumn(name = "uid"),
            inverseJoinColumns = @JoinColumn(name = "gid"))
    private Set<Group> groups;

    public String getUsername() {
        //log.info(String.format("Get in getUsername and take %s", username));
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        //log.info(String.format("Get in getPassword and take $2a%s", password.substring(5)));
        return String.format("$2a%s", password.substring(5));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        //log.info(String.format("Get in getConfirmPass and take %s", confirmPassword));
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
