package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;
/**
 * Created by Marmonth on 04.05.2017.
 */
public class EntityProtocolOutput {

    private Integer id;
    private String name, action, comments, userLastName, documentName, date, time;

    public EntityProtocolOutput(Integer id, String name, String action, String comments, String userLastName, String documentName, String date, String time) {
        this.id = id;
        this.name = name;
        this.action = action;
        this.comments = comments;
        this.userLastName = userLastName;
        this.documentName = documentName;
        this.date = date;
        this.time = time;
    }

    public EntityProtocolOutput() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
