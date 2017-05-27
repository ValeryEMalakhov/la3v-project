package la3v.logic.repositories.classes;

import la3v.logic.entities.document.EntityDocument;
import la3v.logic.entities.document.EntityDocumentType;
import la3v.logic.mappers.document.MapperDocument;
import la3v.logic.mappers.document.MapperDocumentType;
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

    private static final String FIND_ALL_FILE =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls') order by file.storage, file.name;";

    private static final String FIND_ALL_DOCUMENT =
            "select\tdoc.doc_id, u.uid, file.storage, doc.doc_title, file.name,\n" +
                    "\ttype.type_name, string_agg(a.author_name, ',') as author_name, proc.proc_name,\n" +
                    "\tdoc.doc_attributes, doc.doc_date, doc.doc_description, '/var/www/owncloud/data/'||u.uid||'/'||file.path as path\n" +
                    "\n" +
                    "from\t\"public\".oc_users as u, \"public\".oc_storages as st, \"public\".oc_filecache as file,\n" +
                    "\t\"document\".\"type\" as type, \"document\".\"document\" as doc\n" +
                    "\t\tleft join \"document\".\"doc_author\" as da on doc.doc_id = da.doc_id\n" +
                    "\t\tleft join \"document\".\"author\" a on da.author_id = a.author_id\n" +
                    "\n" +
                    "\t\tleft join \"document\".\"doc_proc\" as dp on doc.doc_id = dp.doc_id\n" +
                    "\t\tleft join \"document\".\"proc\" as proc on dp.proc_id = proc.proc_id\n" +
                    "\t\t\n" +
                    "where\tst.id = 'home::' || u.uid\n" +
                    "\tand file.storage = st.numeric_id\n" +
                    "\tand u.uid = ?\n" +
                    "\tand (file.path like '%.doc'\n" +
                    "\tor file.path like '%.docx'\n" +
                    "\tor file.path like '%.pdf'\n" +
                    "\tor file.path like '%.xls')\n" +
                    "\tand doc.doc_id = file.fileid\n" +
                    "\tand doc.doc_type_id = type.type_id\n" +
                    "\n" +
                    "group by doc.doc_id, type.type_name, proc.proc_name, u.uid, file.storage, file.name, file.path\n" +
                    "order by file.storage, doc.doc_id\n" +
                    ";";


    private static final String FIND_ALL_DOCUMENT_ID =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where path_hash = ? and st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls');";

    private static final String FIND_ALL_ATTRIBUTES =
            "select file.storage, file.name, file.path, file.path_hash, st.name from oc_filecache as file, oc_systemtag as st, oc_systemtag_object_mapping as stm where st.id=stm.systemtagid and stm.objectid::int=file.fileid;";

    private static final String FIND_ALL_DOCUMENT_TYPES =
            "select type_id, type_name from \"document\".\"type\";";

    @Autowired
    public ImplementationRepositoryDocument(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public EntityDocument findById(Integer id) {
        return null;
    }

    @Override
    public List<EntityDocument> getAllDocumentList(String fileUser) {
        //return this.template.query(GET_ALL_DOCUMENT_LIST, new Object[]{}, new MapperDocument());
        return this.template.query(FIND_ALL_DOCUMENT, new Object[]{fileUser}, new MapperDocument());
    }

    @Override
    public List<EntityDocumentType> getAllDocumentType() {
        return this.template.query(FIND_ALL_DOCUMENT_TYPES, new Object[]{}, new MapperDocumentType());
    }

}
