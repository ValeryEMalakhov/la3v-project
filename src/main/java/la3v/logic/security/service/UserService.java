package la3v.logic.security.service;

import la3v.logic.security.model.User;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
