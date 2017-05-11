package la3v.logic.security.service;

import la3v.logic.security.dao.UserDao;
//import la3v.logic.security.model.Role;
import la3v.logic.security.model.Group;
import la3v.logic.security.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //log.info(String.format("Get in UserDetails() with %s", username));

        User user = userDao.findByUsername(username);

        //log.info(String.format("Find userDao %s", user));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        // добаляем пользователю, все роли, что у него есть в базе
        for (Group group : user.getGroups()) {
            //log.info(String.format("getGroup_id ROLE_%s", group.getGroup_id()));
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + group.getGroup_id()));
        }
        // возвращаем имя, пароли и роли
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
