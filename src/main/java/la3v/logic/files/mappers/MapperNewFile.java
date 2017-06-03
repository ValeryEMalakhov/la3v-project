package la3v.logic.files.mappers;

import la3v.logic.files.entities.EntityNewFile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Valery E. Malakhov on 28.05.2017.
 */
public class MapperNewFile implements RowMapper<EntityNewFile> {

    public EntityNewFile mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntityNewFile entityNewFile = new EntityNewFile();

        entityNewFile.setDocId(rs.getInt("doc_id"));

        return entityNewFile;
    }
}
