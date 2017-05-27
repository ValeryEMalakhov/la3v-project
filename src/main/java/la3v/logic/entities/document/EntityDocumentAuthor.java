package la3v.logic.entities.document;

/**
 * Created by Valery E. Malakhov on 25.05.2017.
 */
public class EntityDocumentAuthor {

    private Integer author_id;
    private String author_name;

    public EntityDocumentAuthor(Integer author_id, String author_name) {
        this.author_id = author_id;
        this.author_name = author_name;
    }

    public EntityDocumentAuthor() {
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
