package la3v.logic.files.entities;

/**
 * Created by Valery E. Malakhov on 27.05.2017.
 */
public class EntityNewFile {

    private Integer docId;

    public EntityNewFile(Integer docId) {
        this.docId = docId;
    }

    public EntityNewFile() {
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }
}
