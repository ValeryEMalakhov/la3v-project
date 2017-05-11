package la3v.logic.mappers;

import la3v.logic.entities.EntityDocument;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
public class MapperDocument implements RowMapper<EntityDocument> {

    public EntityDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocument entityDocument = new EntityDocument();

        entityDocument.setId(rs.getInt("doc_id"));
        entityDocument.setName(rs.getString("doc_name"));
        entityDocument.setTemplateId(rs.getInt("doc_template_id"));
        entityDocument.setType(rs.getString("doc_type"));
        entityDocument.setVersion(rs.getString("doc_version"));
        entityDocument.setPath(rs.getString("doc_path"));
        entityDocument.setCreationDate(rs.getDate("doc_date_creation"));
        entityDocument.setUpdDate(rs.getDate("doc_date_last_upd"));
        entityDocument.setArchivingDate(rs.getDate("doc_date_archiving"));

        return entityDocument;
    }
}
