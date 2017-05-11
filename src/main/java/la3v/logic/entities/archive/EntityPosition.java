package la3v.logic.entities.archive;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class EntityPosition {

    private Integer id;
    private String name, rank;

    public EntityPosition() {
    }

    public EntityPosition(Integer id, String name, String rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
