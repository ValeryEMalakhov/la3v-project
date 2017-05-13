package la3v.logic.mappers;

import la3v.logic.entities.EntityDocument;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 13.05.2017.
 */
public class MapperDocument implements RowMapper<EntityDocument> {

    public EntityDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocument entityDocument = new EntityDocument();

        entityDocument.setUid(rs.getString("uid"));
        entityDocument.setStorage(rs.getInt("storage"));
        entityDocument.setName(rs.getString("name"));
        entityDocument.setPath(rs.getString("path"));
        entityDocument.setPathHash(rs.getString("path_hash"));

        return entityDocument;
    }
}