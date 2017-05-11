package la3v.logic.entities.archive;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityUser {

    private Integer id, positionId;
    private String firstName, patronymicName, lastName;

    public EntityUser() {
    }

    public EntityUser(Integer id, Integer positionId, String firstName, String patronymicName, String lastName) {
        this.id = id;
        this.positionId = positionId;
        this.firstName = firstName;
        this.patronymicName = patronymicName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
