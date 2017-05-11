package la3v.logic.repositories.interfaces;


import la3v.logic.entities.archive.EntityUser;

import java.util.List;

/**
 * Created by Marmonth on 11.05.2017.
 */
public interface IRepositoryUser extends IRepository<EntityUser> {

    List<EntityUser> getAllUsersList();
}
