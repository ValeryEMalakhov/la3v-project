package la3v.logic.entities.archive;

import java.util.Date;
import com.google.gson.*;
/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityDocument {

    private Integer id;
    private String name, author, path, comments, archivePath;
    private JsonObject attributes;
    private double archivingTerm;
    private String dateOfArchiving;

    public EntityDocument() {
    }

    public EntityDocument(Integer id, String name, String author,  String path, String comments, String archivePath, JsonObject attributes, double archivingTerm, String dateOfArchiving) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.path = path;
        this.comments = comments;
        this.archivePath = archivePath;
        this.attributes = attributes;
        this.archivingTerm = archivingTerm;
        this.dateOfArchiving = dateOfArchiving;
    }

    public EntityDocument(String name, String author, String path, String comments, String archivePath, JsonObject attributes, double archivingTerm, String dateOfArchiving) {
        this.name = name;
        this.author = author;
        this.path = path;
        this.comments = comments;
        this.archivePath = archivePath;
        this.attributes = attributes;
        this.archivingTerm = archivingTerm;
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

    public String getDateOfArchiving() {
        return dateOfArchiving;
    }

    public void setDateOfArchiving(String dateOfArchiving) {
        this.dateOfArchiving = dateOfArchiving;
    }
}
