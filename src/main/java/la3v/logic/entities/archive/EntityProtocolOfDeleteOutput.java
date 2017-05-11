package la3v.logic.entities.archive;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Marmonth on 04.05.2017.
 */
public class EntityProtocolOfDeleteOutput {

    private Integer id;
    private String name, comments, documentName, documentAuthor, documentArchivePath, documentComments, userLastName;
    private Date date, documentDateOfCreation, documentDateOfArchiving;
    private Time time;
    private double archivingTerm;

    public EntityProtocolOfDeleteOutput(Integer id, String name, String comments, String documentName, String documentAuthor, String documentArchivePath, String documentComments, String userLastName, Date date, Date documentDateOfCreation, Date documentDateOfArchiving, Time time, double archivingTerm) {

        this.id = id;
        this.name = name;
        this.comments = comments;
        this.documentName = documentName;
        this.documentAuthor = documentAuthor;
        this.documentArchivePath = documentArchivePath;
        this.documentComments = documentComments;
        this.userLastName = userLastName;
        this.date = date;
        this.documentDateOfCreation = documentDateOfCreation;
        this.documentDateOfArchiving = documentDateOfArchiving;
        this.time = time;
        this.archivingTerm = archivingTerm;
    }

    public EntityProtocolOfDeleteOutput() {

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

    public String getDocumentComments() {
        return documentComments;
    }

    public void setDocumentComments(String documentComments) {
        this.documentComments = documentComments;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setDocumentDateOfArchiving(Date documentDateOfArchiving) {
        this.documentDateOfArchiving = documentDateOfArchiving;
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
