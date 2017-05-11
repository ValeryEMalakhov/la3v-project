package la3v.logic.entities.archive;

import java.util.Date;
import com.google.gson.*;
/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityDocument {

    private Integer id;
    private String name, author, version, path, comments, archivePath;
    private JsonObject attributes;
    private double archivingTerm;
    private Date dateOfCreation, dateOfArchiving;

    public EntityDocument() {
    }

    public EntityDocument(Integer id, String name, String author, String version, String path, String comments, String archivePath, JsonObject attributes, double archivingTerm, Date dateOfCreation, Date dateOfArchiving) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.version = version;
        this.path = path;
        this.comments = comments;
        this.archivePath = archivePath;
        this.attributes = attributes;
        this.archivingTerm = archivingTerm;
        this.dateOfCreation = dateOfCreation;
        this.dateOfArchiving = dateOfArchiving;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getArchivePath() {
        return archivePath;
    }

    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    public JsonObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JsonObject attributes) {
        this.attributes = attributes;
    }

    public double getArchivingTerm() {
        return archivingTerm;
    }

    public void setArchivingTerm(double archivingTerm) {
        this.archivingTerm = archivingTerm;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfArchiving() {
        return dateOfArchiving;
    }

    public void setDateOfArchiving(Date dateOfArchiving) {
        this.dateOfArchiving = dateOfArchiving;
    }
}
