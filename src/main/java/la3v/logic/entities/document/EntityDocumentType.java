package la3v.logic.entities.document;

/**
 * Created by Valery E. Malakhov on 25.05.2017.
 */
public class EntityDocumentType {

    private Integer type_id;
    private String type_name;

    public EntityDocumentType(Integer type_id, String type_name) {

        this.type_id = type_id;
        this.type_name = type_name;
    }

    public EntityDocumentType() {
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
