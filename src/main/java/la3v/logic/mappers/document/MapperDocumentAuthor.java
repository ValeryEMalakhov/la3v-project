package la3v.logic.mappers.document;

import la3v.logic.entities.document.EntityDocumentAuthor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 25.05.2017.
 */
public class MapperDocumentAuthor implements RowMapper<EntityDocumentAuthor> {

    public EntityDocumentAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocumentAuthor entityDocumentAuthor = new EntityDocumentAuthor();

        entityDocumentAuthor.setAuthor_id(rs.getInt("author_id"));
        entityDocumentAuthor.setAuthor_name(rs.getString("author_name"));

        return entityDocumentAuthor;
    }
}
