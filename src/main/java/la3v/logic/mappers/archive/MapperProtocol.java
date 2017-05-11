package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityProtocol;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class MapperProtocol implements RowMapper<EntityProtocol> {

    public EntityProtocol mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityProtocol entityProtocol = new EntityProtocol();

        entityProtocol.setId(rs.getInt("protocolId"));
        entityProtocol.setName(rs.getString("protocolName"));
        entityProtocol.setDate(rs.getDate("protocolDate"));
        entityProtocol.setTime(rs.getTime("protocolTime"));
        entityProtocol.setUserId(rs.getInt("protocolUserId"));
        entityProtocol.setAction(rs.getString("protocolAction"));
        entityProtocol.setComments(rs.getString("protocolComments"));
        entityProtocol.setDocumentId(rs.getInt("protocolDocumentId"));

        return entityProtocol;
    }
}
