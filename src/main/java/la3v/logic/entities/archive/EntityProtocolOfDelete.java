package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityProtocolOfDelete {

    private Integer id, userId;
    private String name, comments, documentName, documentAuthor, documentArchivePath, documentComments;
    private Date date, documentDateOfCreation, documentDateOfArchiving;
    private Time time;
    private double archivingTerm;

    public EntityProtocolOfDelete() {
    }

    public EntityProtocolOfDelete(Integer id, Integer userId, String name, String comments, String documentName, String documentAuthor, String documentArchivePath, String documentComments, Date date, Date documentDateOfCreation, Date dateOfArchiving, Time time, double archivingTerm) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.comments = comments;
        this.documentName = documentName;
        this.documentAuthor = documentAuthor;
        this.documentArchivePath = documentArchivePath;
        this.documentComments = documentComments;
        this.date = date;
        this.documentDateOfCreation = documentDateOfCreation;
        this.documentDateOfArchiving = dateOfArchiving;
        this.time = time;
        this.archivingTerm = archivingTerm;
    }

    public String getDocumentComments() {
        return documentComments;
    }

    public void setDocumentComments(String documentComments) {
        this.documentComments = documentComments;
    }

    public Date getDocumentDateOfCreation() {
        return documentDateOfCreation;
    }

    public void setDocumentDateOfCreation(Date documentDateOfCreation) {
        this.documentDateOfCreation = documentDateOfCreation;
    }

    public Date getDocumentDateOfArchiving() {
        return documentDateOfArchiving;
    }

    public void setDocumentDateOfArchiving(Date documentDateOfArchiving) { this.documentDateOfArchiving = documentDateOfArchiving; }

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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentAuthor() {
        return documentAuthor;
    }

    public void setDocumentAuthor(String documentAuthor) {
        this.documentAuthor = documentAuthor;
    }

    public String getDocumentArchivePath() {
        return documentArchivePath;
    }

    public void setDocumentArchivePath(String documentArchivePath) {
        this.documentArchivePath = documentArchivePath;
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

    public double getArchivingTerm() {
        return archivingTerm;
    }

    public void setArchivingTerm(double archivingTerm) {
        this.archivingTerm = archivingTerm;
    }
}
