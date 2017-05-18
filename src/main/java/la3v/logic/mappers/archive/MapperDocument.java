package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityDocument;
import com.google.gson.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class MapperDocument implements RowMapper<EntityDocument> {

    public EntityDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocument entityDocument = new EntityDocument();

        entityDocument.setId(rs.getInt("documentId"));
        entityDocument.setName(rs.getString("documentName"));
        entityDocument.setAuthor(rs.getString("documentAuthor"));
        entityDocument.setPath(rs.getString("documentPath"));
        entityDocument.setDateOfCreation(rs.getString("documentDateOfCreation"));
        entityDocument.setDateOfArchiving(rs.getString("documentDateOfArchiving"));
        entityDocument.setComments(rs.getString("documentComments"));
        entityDocument.setArchivePath(rs.getString("documentArchivePath"));
        entityDocument.setArchivingTerm(rs.getDouble("documentArchivingTerm"));

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(rs.getString("documentAttributes")).getAsJsonObject();
        entityDocument.setAttributes(obj);


        return entityDocument;
    }
}

