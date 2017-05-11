package la3v.logic.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    // получаем имя пользователя (к примеру для приветствия)
    @Override
    public String findLoggedInUsername() {
        //log.info(String.format("Get in findLoggedInUsername"));
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        //log.info(String.format("Find userDetails %s", userDetails));

        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        //log.info(String.format("Get in autoLogin() with %s and %s", username, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.info(String.format("Successfully %s auto logged in", username));
        }
    }
}
