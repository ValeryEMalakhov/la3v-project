package la3v.logic.entities;

import java.util.Date;

/**
 * Created by Marmonth on 13.05.2017.
 */
public class EntityDocument {

    private Integer storage;
    private String name, uid, path, pathHash;

    public EntityDocument() {
    }

    public EntityDocument(Integer storage, String name, String uid, String path, String pathHash) {
        this.storage = storage;
        this.name = name;
        this.uid = uid;
        this.path = path;
        this.pathHash = pathHash;
    }

    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPathHash() {
        return pathHash;
    }

    public void setPathHash(String pathHash) {
        this.pathHash = pathHash;
    }
}
