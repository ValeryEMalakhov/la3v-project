package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityProtocolOfDelete {

    private Integer id;
    private String name, comments, documentName, documentAuthor, documentArchivePath, documentComments,
            user, date, documentDateOfArchiving, time;
    private double archivingTerm;

    public EntityProtocolOfDelete() {
    }

    public EntityProtocolOfDelete(Integer id, String user, String name, String comments, String documentName, String documentAuthor, String documentArchivePath,
                                  String documentComments, String date, String dateOfArchiving, String time, double archivingTerm) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.comments = comments;
        this.documentName = documentName;
        this.documentAuthor = documentAuthor;
        this.documentArchivePath = documentArchivePath;
        this.documentComments = documentComments;
        this.date = date;
        this.documentDateOfArchiving = dateOfArchiving;
        this.time = time;
        this.archivingTerm = archivingTerm;
    }

    public EntityProtocolOfDelete(String user, String name, String comments, String documentName, String documentAuthor, String documentArchivePath,
                                  String documentComments, String date, String dateOfArchiving, String time, double archivingTerm) {
        this.user = user;
        this.name = name;
        this.comments = comments;
        this.documentName = documentName;
        this.documentAuthor = documentAuthor;
        this.documentArchivePath = documentArchivePath;
        this.documentComments = documentComments;
        this.date = date;
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

    public String getDocumentDateOfArchiving() {
        return documentDateOfArchiving;
    }

    public void setDocumentDateOfArchiving(String documentDateOfArchiving) { this.documentDateOfArchiving = documentDateOfArchiving; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String userId) {
        this.user = userId;
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

    public double getArchivingTerm() {
        return archivingTerm;
    }

    public void setArchivingTerm(double archivingTerm) {
        this.archivingTerm = archivingTerm;
    }
}
