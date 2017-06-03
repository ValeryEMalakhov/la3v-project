package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityProtocol {

    private Integer id, documentId;
    private String name, user, action, comments, date, time;

    public EntityProtocol() {
    }

    public EntityProtocol(Integer id, String user, String name, String action, String comments, String date, String time, int documentId) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.action = action;
        this.comments = comments;
        this.date = date;
        this.time = time;
        this.documentId = documentId;
    }

    public EntityProtocol(String user, String name, String action, String comments, String date, String time, int documentId) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.action = action;
        this.comments = comments;
        this.date = date;
        this.time = time;
        this.documentId = documentId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
