package la3v.logic.security.service;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
