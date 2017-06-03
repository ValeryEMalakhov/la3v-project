package la3v.logic.mappers.document;

import la3v.logic.entities.document.EntityDocumentProc;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 28.05.2017.
 */
public class MapperDocumentProc implements RowMapper<EntityDocumentProc> {

    public EntityDocumentProc mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocumentProc entityDocumentProc = new EntityDocumentProc();

        entityDocumentProc.setProcId(rs.getInt("proc_id"));
        entityDocumentProc.setProcName(rs.getString("proc_name"));
        entityDocumentProc.setProcDefaultWay(rs.getString("proc_default_way"));

        return entityDocumentProc;
    }
}
