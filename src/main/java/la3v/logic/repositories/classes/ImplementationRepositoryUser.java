package la3v.logic.repositories.classes;

import la3v.logic.entities.archive.EntityUser;
import la3v.logic.mappers.archive.MapperUser;
import la3v.logic.repositories.interfaces.IRepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Marmonth on 11.05.2017.
 */
@Repository
public class ImplementationRepositoryUser implements IRepositoryUser {
    private JdbcTemplate template;

    private static final String FIND_ALL_USERS = "SELECT * FROM \"archive\".\"User\" ORDER BY userId ASC";
    private static final String FIND_USER_BY_ID = "SELECT * FROM \"archive\".\"User\" WHERE userId = ?";


    @Autowired
    public ImplementationRepositoryUser(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<EntityUser> getAllUsersList(){
        return this.template.query(FIND_ALL_USERS, new Object[] {}, new MapperUser());
    }
    @Override
    public EntityUser findById(Integer id)
    {
        return this.template.queryForObject(FIND_USER_BY_ID, new Object[] {id}, new MapperUser());
    }
}
