package la3v.logic.security.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
@Entity
@Table(name = "oc_groups")
public class Group {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(Group.class);

    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "gid")
    private String group_id;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    public Group() {
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + group_id + '\'' +
                ", users=" + users +
                '}';
    }
}
