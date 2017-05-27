package la3v.logic.mappers.document;

import la3v.logic.entities.document.EntityDocumentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 25.05.2017.
 */
public class MapperDocumentType implements RowMapper<EntityDocumentType> {

    public EntityDocumentType mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocumentType entityDocumentType = new EntityDocumentType();

        entityDocumentType.setType_id(rs.getInt("type_id"));
        entityDocumentType.setType_name(rs.getString("type_name"));

        return entityDocumentType;
    }
}
