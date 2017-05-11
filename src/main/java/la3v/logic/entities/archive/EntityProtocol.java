package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityProtocol {

    private Integer id, userId, documentId;
    private String name, action, comments;
    private Date date;
    private Time time;

    public EntityProtocol() {
    }

    public EntityProtocol(Integer id, Integer userId, String name, String action, String comments, Date date, Time time) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.action = action;
        this.comments = comments;
        this.date = date;
        this.time = time;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
