package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityProtocol;
import la3v.logic.entities.archive.EntityProtocolOutput;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 04.05.2017.
 */
public class MapperProtocolOutput implements RowMapper<EntityProtocolOutput> {

    public EntityProtocolOutput mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityProtocolOutput entityProtocolOutput = new EntityProtocolOutput();

        entityProtocolOutput.setId(rs.getInt("protocolId"));
        entityProtocolOutput.setName(rs.getString("protocolName"));
        entityProtocolOutput.setDate(rs.getString("protocolDate"));
        entityProtocolOutput.setTime(rs.getString("protocolTime"));
        entityProtocolOutput.setUserLastName(rs.getString("protocolUser"));
        entityProtocolOutput.setAction(rs.getString("protocolAction"));
        entityProtocolOutput.setComments(rs.getString("protocolComments"));
        entityProtocolOutput.setDocumentName(rs.getString("documentName"));

        return entityProtocolOutput;
    }
}
