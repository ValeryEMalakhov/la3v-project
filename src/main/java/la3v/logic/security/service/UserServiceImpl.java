package la3v.logic.security.service;

import la3v.logic.security.dao.GroupDao;
//import la3v.logic.security.dao.RoleDao;
import la3v.logic.security.dao.UserDao;
//import la3v.logic.security.model.Role;
import la3v.logic.security.model.Group;
import la3v.logic.security.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        //log.info(String.format("Get in save() with %s", user));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Group> groups = new HashSet<>();

        // id role in table
        groups.add(groupDao.getOne(1L));
        user.setGroups(groups);

        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        //log.info(String.format("Get in findByUsername with %s", username));
        return userDao.findByUsername(username);
    }
}
