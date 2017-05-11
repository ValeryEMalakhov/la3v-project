package la3v.logic.repositories.interfaces;

/**
 * Created by Valery E. Malakhov on 11.03.2017.
 */

public interface IRepository<IR> {

/*    void insert(IR entity);

    void update(IR entity);

    void delete(IR entity);*/

    IR findById(Integer id);
}
