package la3v.logic.mappers.archive;

import la3v.logic.entities.archive.EntityPosition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Marmonth on 21.04.2017.
 */
public class MapperPosition implements RowMapper<EntityPosition> {

    public EntityPosition mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityPosition entityPosition = new EntityPosition();

        entityPosition.setId(rs.getInt("positionId"));
        entityPosition.setName(rs.getString("positionName"));
        entityPosition.setRank(rs.getString("positionRank"));

        return entityPosition;
    }

}
