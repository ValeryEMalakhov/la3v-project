package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityProtocolOfDelete;
import la3v.logic.entities.archive.EntityProtocolOfDeleteOutput;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 04.05.2017.
 */
public class MapperProtocolOfDeleteOutput implements RowMapper<EntityProtocolOfDeleteOutput> {

    public EntityProtocolOfDeleteOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityProtocolOfDeleteOutput entityProtocol = new EntityProtocolOfDeleteOutput();

        entityProtocol.setId(rs.getInt("protocolDelId"));
        entityProtocol.setName(rs.getString("protocolDelName"));
        entityProtocol.setDate(rs.getDate("protocolDelDate"));
        entityProtocol.setTime(rs.getTime("protocolDelTime"));
        entityProtocol.setComments(rs.getString("protocolDelComments"));
        entityProtocol.setUserLastName(rs.getString("userLastName"));
        entityProtocol.setDocumentName(rs.getString("protocolDelDocName"));
        entityProtocol.setDocumentAuthor(rs.getString("protocolDelDocAuthor"));
        entityProtocol.setDocumentArchivePath(rs.getString("protocolDelDocArchivePath"));
        entityProtocol.setDocumentDateOfCreation(rs.getDate("protocolDelDocDateOfCreation"));
        entityProtocol.setDocumentDateOfArchiving(rs.getDate("protocolDelDocDateOfArchiving"));
        entityProtocol.setArchivingTerm(rs.getDouble("protocolDelDocArchivingTerm"));
        entityProtocol.setDocumentComments(rs.getString("protocolDelDocComments"));

        return entityProtocol;
    }
}
