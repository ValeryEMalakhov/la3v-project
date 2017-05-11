package la3v.logic.entities;

import java.util.Date;

/**
 * Created by Valery E. Malakhov on 01.04.2017.
 */
public class EntityDocument {

    private Integer id, templateId;
    private String name, type, version, path;
    private Date creationDate, updDate, archivingDate;

    public EntityDocument(Integer id, String name,
                          Integer templateId, String type,
                          String version, String path,
                          Date creationDate, Date updDate,
                          Date archivingDate) {
        this.id = id;
        this.name = name;
        this.templateId = templateId;
        this.type = type;
        this.version = version;
        this.path = path;
        this.creationDate = creationDate;
        this.updDate = updDate;
        this.archivingDate = archivingDate;
    }

    public EntityDocument() {

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

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    public Date getArchivingDate() {
        return archivingDate;
    }

    public void setArchivingDate(Date archivingDate) {
        this.archivingDate = archivingDate;
    }
}
