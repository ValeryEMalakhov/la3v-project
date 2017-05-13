package la3v.logic.repositories.classes;

import la3v.logic.entities.EntityDocument;
import la3v.logic.mappers.MapperDocument;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
@Repository
public class ImplementationRepositoryDocument implements IRepositoryDocument {

    private JdbcTemplate template;

    private static final String FIND_ALL_DOCUMENT =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls') order by file.storage, file.name;";

    private static final String FIND_ALL_DOCUMENT_ID =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where path_hash = ? and st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls');";


    @Autowired
    public ImplementationRepositoryDocument(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public EntityDocument findById(Integer id) {
        return null;
    }

    @Override
    public EntityDocument findByHash(String hash) {
        return this.template.queryForObject(FIND_ALL_DOCUMENT_ID, new Object[]{hash}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getAllDocumentList() {
        //return this.template.query(GET_ALL_DOCUMENT_LIST, new Object[]{}, new MapperDocument());
        return this.template.query(FIND_ALL_DOCUMENT, new Object[]{}, new MapperDocument());
    }

}
