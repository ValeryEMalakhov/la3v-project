package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityProtocolOfDelete;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class MapperProtocolOfDelete implements RowMapper<EntityProtocolOfDelete> {

    public EntityProtocolOfDelete mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityProtocolOfDelete entityProtocol = new EntityProtocolOfDelete();

        entityProtocol.setId(rs.getInt("protocolDelId"));
        entityProtocol.setName(rs.getString("protocolDelName"));
        entityProtocol.setDate(rs.getString("protocolDelDate"));
        entityProtocol.setTime(rs.getString("protocolDelTime"));
        entityProtocol.setComments(rs.getString("protocolDelComments"));
        entityProtocol.setUser(rs.getString("protocolDelUser"));
        entityProtocol.setDocumentName(rs.getString("protocolDelDocName"));
        entityProtocol.setDocumentAuthor(rs.getString("protocolDelDocAuthor"));
        entityProtocol.setDocumentArchivePath(rs.getString("protocolDelDocArchivePath"));
        entityProtocol.setDocumentDateOfArchiving(rs.getString("protocolDelDocDateOfArchiving"));
        entityProtocol.setArchivingTerm(rs.getDouble("protocolDelDocArchivingTerm"));
        entityProtocol.setDocumentComments(rs.getString("protocolDelDocComments"));

        return entityProtocol;
    }
}
