package la3v.logic.entities.document;

/**
 * Created by Valery E. Malakhov on 25.05.2017.
 */
public class EntityDocumentProc {

    private Integer procId;
    private String procName, procDefaultWay;

    public EntityDocumentProc() {
    }

    public EntityDocumentProc(Integer procId, String procName, String procDefaultWay) {
        this.procId = procId;
        this.procName = procName;
        this.procDefaultWay = procDefaultWay;
    }

    public Integer getProcId() {
        return procId;
    }

    public void setProcId(Integer procId) {
        this.procId = procId;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getProcDefaultWay() {
        return procDefaultWay;
    }

    public void setProcDefaultWay(String procDefaultWay) {
        this.procDefaultWay = procDefaultWay;
    }
}
