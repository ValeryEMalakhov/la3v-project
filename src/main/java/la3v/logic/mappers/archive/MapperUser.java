package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class MapperUser implements RowMapper<EntityUser>{

    public EntityUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityUser entityUser = new EntityUser();

        entityUser.setId(rs.getInt("userId"));
        entityUser.setPositionId(rs.getInt("userPositionId"));
        entityUser.setFirstName(rs.getString("userFirstName"));
        entityUser.setPatronymicName(rs.getString("userPatronymicName"));
        entityUser.setLastName(rs.getString("userLastName"));

        return entityUser;
    }
}
