package la3v.logic.mappers.document;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import la3v.logic.entities.document.EntityDocument;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
public class MapperDocument implements RowMapper<EntityDocument> {

    public EntityDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityDocument entityDocument = new EntityDocument();

        entityDocument.setDocId(rs.getInt("doc_id"));
        entityDocument.setFileUser(rs.getString("uid"));
        entityDocument.setFileStorage(rs.getInt("storage"));
        entityDocument.setDocTitle(rs.getString("doc_title"));
        entityDocument.setFileName(rs.getString("name"));
        entityDocument.setDocType(rs.getString("type_name"));
//        entityDocument.setDocTypeId(rs.getInt("doc_type_id"));
        entityDocument.setAuthorName(rs.getString("author_name"));
        entityDocument.setDocProcess(rs.getString("proc_name"));

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(rs.getString("doc_attributes")).getAsJsonObject();

        entityDocument.setDocDate(rs.getInt("doc_date"));
        entityDocument.setDocDescription(rs.getString("doc_description"));
        entityDocument.setFilePath(rs.getString("path"));

        entityDocument.setAttributes(obj);

        return entityDocument;
    }
}
