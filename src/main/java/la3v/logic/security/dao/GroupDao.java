package la3v.logic.security.dao;

import la3v.logic.security.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Valery E. Malakhov on 08.05.2017.
 */
public interface GroupDao extends JpaRepository<Group, Long> {
}
