package la3v.logic.repositories.classes;

import la3v.logic.entities.document.EntityDocument;
import la3v.logic.entities.document.EntityDocumentAuthor;
import la3v.logic.entities.document.EntityDocumentProc;
import la3v.logic.entities.document.EntityDocumentType;
import la3v.logic.files.entities.EntityNewFile;
import la3v.logic.files.entities.FileBucket;
import la3v.logic.files.mappers.MapperNewFile;
import la3v.logic.mappers.document.MapperDocument;
import la3v.logic.mappers.document.MapperDocumentAuthor;
import la3v.logic.mappers.document.MapperDocumentProc;
import la3v.logic.mappers.document.MapperDocumentType;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ImplementationRepositoryDocument.class);

    private static final String FIND_ALL_DOCUMENT =
            "select\tdoc.doc_id, u.uid, file.storage, doc.doc_title, file.name,\n" +
                    "\ttype.type_name, string_agg(a.author_name, '; ') as author_name, proc.proc_name,\n" +
                    "\tdoc.doc_attributes, doc.doc_date, doc.doc_description, '/var/www/owncloud/data/'||u.uid||'/'||file.path as path\n" +
                    "\n" +
                    "from\t\"public\".oc_users as u, \"public\".oc_storages as st, \"public\".oc_filecache as file, \"public\".oc_share as sh,\n" +
                    "\t\"document\".\"type\" as type, \"document\".\"document\" as doc\n" +
                    "\t\tleft join \"document\".\"doc_author\" as da on doc.doc_id = da.doc_id\n" +
                    "\t\tleft join \"document\".\"author\" a on da.author_id = a.author_id\n" +
                    "\n" +
                    "\t\tleft join \"document\".\"doc_proc\" as dp on doc.doc_id = dp.doc_id\n" +
                    "\t\tleft join \"document\".\"proc\" as proc on dp.proc_id = proc.proc_id\n" +
                    "\t\t\n" +
                    "where\tst.id = 'home::' || u.uid\n" +
                    "\tand file.storage = st.numeric_id\n" +
                    "\n" +
                    "\tand (file.path like '%.doc'\n" +
                    "\tor file.path like '%.docx'\n" +
                    "\tor file.path like '%.pdf'\n" +
                    "\tor file.path like '%.xls'\n" +
                    "\tor file.path like '%.xlsx')\n" +
                    "\n" +
                    "\tand (u.uid = ? or (sh.share_with = ? and sh.file_source = file.fileid))\n" +
                    "\tand doc.doc_id = file.fileid\n" +
                    "\t\n" +
                    "\tand doc.doc_type_id = type.type_id\n" +
                    "\n" +
                    "group by doc.doc_id, type.type_name, proc.proc_name, u.uid, file.storage, file.name, file.path\n" +
                    "order by file.storage, doc.doc_id\n" +
                    ";";

    private static final String FIND_ALL_DOCUMENT_NOT_SHARE =
            "select\tdoc.doc_id, u.uid, file.storage, doc.doc_title, file.name,\n" +
                    "\ttype.type_name, string_agg(a.author_name, '; ') as author_name, proc.proc_name,\n" +
                    "\tdoc.doc_attributes, doc.doc_date, doc.doc_description, '/var/www/owncloud/data/'||u.uid||'/'||file.path as path\n" +
                    "\n" +
                    "from\t\"public\".\"oc_users\" as u, \"public\".\"oc_storages\" as st, \"public\".\"oc_filecache\" as file,\n" +
                    "\t\"document\".\"type\" as type, \"document\".\"document\" as doc\n" +
                    "\t\tleft join \"document\".\"doc_author\" as da on doc.doc_id = da.doc_id\n" +
                    "\t\tleft join \"document\".\"author\" a on da.author_id = a.author_id\n" +
                    "\n" +
                    "\t\tleft join \"document\".\"doc_proc\" as dp on doc.doc_id = dp.doc_id\n" +
                    "\t\tleft join \"document\".\"proc\" as proc on dp.proc_id = proc.proc_id\n" +
                    "\t\t\n" +
                    "where\tst.id = 'home::' || u.uid\n" +
                    "\tand file.storage = st.numeric_id\n" +
                    "\n" +
                    "\tand (file.path like '%.doc'\n" +
                    "\tor file.path like '%.docx'\n" +
                    "\tor file.path like '%.pdf'\n" +
                    "\tor file.path like '%.xls'\n" +
                    "\tor file.path like '%.xlsx')\n" +
                    "\n" +
                    "\tand u.uid = ?\n" +
                    "\tand doc.doc_id = file.fileid\n" +
                    "\t\n" +
                    "\tand doc.doc_type_id = type.type_id\n" +
                    "\n" +
                    "group by doc.doc_id, type.type_name, proc.proc_name, u.uid, file.storage, file.name, file.path\n" +
                    "order by file.storage, doc.doc_id\n" +
                    ";";

    private static final String FIND_NEW_DOCUMENT_ID =
            "select\tdoc.doc_id\n" +
                    "\n" +
                    "from\t\"public\".oc_users as u, \"public\".oc_storages as st, \"public\".oc_filecache as file,\n" +
                    "\t\"document\".\"document\" as doc\n" +
                    "\t\t\n" +
                    "where\tst.id = 'home::' || u.uid\n" +
                    "\tand file.storage = st.numeric_id\n" +
                    "\t\n" +
                    "\tand u.uid = ?\n" +
                    "\t\n" +
                    "\tand (file.path like '%.doc'\n" +
                    "\tor file.path like '%.docx'\n" +
                    "\tor file.path like '%.pdf'\n" +
                    "\tor file.path like '%.xls'\n" +
                    "\tor file.path like '%.xlsx')\n" +
                    "\tand doc.doc_id = file.fileid\n" +
                    "\n" +
                    "\tand doc.doc_id =\n" +
                    "\t\t(select max(doc.doc_id)\n" +
                    "\t\tfrom \"public\".oc_users as u, \"public\".oc_storages as st, \"public\".oc_filecache as file, \"document\".\"document\" as doc\n" +
                    "\t\twhere\tst.id = 'home::' || u.uid\n" +
                    "\t\t\tand file.storage = st.numeric_id\n" +
                    "\t\t\t\n" +
                    "\t\t\tand u.uid = ?\n" +
                    "\t\t\t\n" +
                    "\t\t\tand (file.path like '%.doc'\n" +
                    "\t\t\tor file.path like '%.docx'\n" +
                    "\t\t\tor file.path like '%.pdf'\n" +
                    "\t\t\tor file.path like '%.xls'\n" +
                    "\t\t\tor file.path like '%.xlsx')\n" +
                    "\t\t\tand doc.doc_id = file.fileid\n" +
                    "\t\t)\n" +
                    ";";

    private static final String FIND_ONLY_DOC_TABLE =
            "SELECT doc_id, doc_title, doc_type_id, doc_date, doc_attributes, doc_description\n" +
                    "\tFROM \"document\".\"document\"\n" +
                    "WHERE doc_id = ?;";

    //region Document update
    private static final String UPD_DOCUMENT_WITH_ATTRIBUTES =
            "UPDATE \"document\".\"document\"\n" +
                    "\tSET doc_attributes = ?::JSONB\n" +
                    "WHERE doc_id = ?;";

    private static final String UPD_DOCUMENT_WITHOUT_ATTRIBUTES =
            "UPDATE \"document\".\"document\"\n" +
                    "\tSET doc_title = ?, doc_type_id = ?, doc_date = ?, doc_description = ?\n" +
                    "WHERE doc_id = ?;";
    //endregion

    //region FIND PROC
    private static final String FIND_ALL_DOCUMENT_PROC =
            "select proc_id, proc_name, proc_default_way from \"document\".\"proc\" order by proc_id;";

    private static final String FIND_DOCUMENT_PROC_BY_ID =
            "select proc_id, proc_name, proc_default_way from \"document\".\"proc\" where proc_id = ?;";

    private static final String FIND_DOCUMENT_PROC_BY_NAME =
            "select proc_id, proc_name, proc_default_way from \"document\".\"proc\" where proc_name = ?;";

    private static final String UPD_DOCUMENT_PROC =
            "UPDATE \"document\".\"doc_proc\"\n" +
                    "\tSET proc_id = ?\n" +
                    "WHERE doc_id = ?;\n";
    //endregion

    //region FIND TYPES
    private static final String FIND_ALL_DOCUMENT_TYPES =
            "select type_id, type_name from \"document\".\"type\" order by type_id ;";

    private static final String FIND_DOCUMENT_TYPES_BY_ID =
            "select type_id, type_name from \"document\".\"type\" where type_id = ?;";

    private static final String FIND_DOCUMENT_TYPES_BY_NAME =
            "select type_id, type_name from \"document\".\"type\" where type_name = ?;";
    //endregion

    //region FIND AUTHORS
    private static final String FIND_ALL_DOCUMENT_AUTHORS =
            "select author_id, author_name from \"document\".\"author\" order by author_id;";

    private static final String FIND_DOCUMENT_AUTHORS_BY_ID =
            "select author_id, author_name from \"document\".\"author\" where author_id = ?;";

    private static final String FIND_DOCUMENT_AUTHORS_BY_NAME =
            "select author_id, author_name from \"document\".\"author\" where author_name = ?;";
    //endregion

    private static final String UPDATE_NEW_DOCUMENT_BY_ID =
            "update\tdocument.document\n" +
                    "set\tdoc_title = ?, doc_type_id = ?,\n" +
                    "\tdoc_date = ?, doc_description = ?\n" +
                    "where\tdoc_id = ?" +
                    ";";

    private static final String INSERT_NEW_INTO_AUTHOR =
            "insert into \"document\".\"author\"\n" +
                    "\t(author_name)\n" +
                    "values (?);";

    private static final String INSERT_NEW_INTO_DOC_PROC =
            "insert into \"document\".\"doc_proc\"\n" +
                    "\t(doc_id, proc_id)\n" +
                    "values (?, ?);";

    private static final String INSERT_NEW_INTO_DOC_AUTHOR =
            "insert into \"document\".\"doc_author\"\n" +
                    "\t(doc_id, author_id)\n" +
                    "values (?, ?);";

    private static final String FIND_ALL_DOCUMENT_ID =
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
                    "\tand doc.doc_id = ?\n" +
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

    //DELETE (USED IN ARCHIVE SYSTEM)
    private static final String DELETE_FROM_DOCUMENT = "DELETE FROM \"document\".\"document\" WHERE doc_id = ?";
    private static final String DELETE_FROM_DOC_PROC = "DELETE FROM \"document\".\"doc_proc\" WHERE doc_id = ?;";
    private static final String DELETE_FROM_DOC_AUTHOR = "DELETE FROM \"document\".\"doc_author\" WHERE doc_id = ?;";

    @Autowired
    public ImplementationRepositoryDocument(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public EntityDocument findById(Integer id) {
        return this.template.queryForObject(FIND_ALL_DOCUMENT_ID, new Object[]{id}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getAllDocumentList(String fileUser) {

        //return this.template.query(FIND_ALL_DOCUMENT, new Object[]{fileUser, fileUser}, new MapperDocument());
        return this.template.query(FIND_ALL_DOCUMENT_NOT_SHARE, new Object[]{fileUser}, new MapperDocument());
    }

    @Override
    public List<EntityDocumentProc> getAllDocumentProc() {
        return this.template.query(FIND_ALL_DOCUMENT_PROC, new Object[]{}, new MapperDocumentProc());
    }

    @Override
    public EntityDocumentProc getDocumentProcById(Integer id) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_PROC_BY_ID, new Object[]{id}, new MapperDocumentProc());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public EntityDocumentProc getDocumentProcByName(String name) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_PROC_BY_NAME, new Object[]{name}, new MapperDocumentProc());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public List<EntityDocumentType> getAllDocumentType() {
        return this.template.query(FIND_ALL_DOCUMENT_TYPES, new Object[]{}, new MapperDocumentType());
    }

    @Override
    public EntityDocumentType getDocumentTypeById(Integer id) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_TYPES_BY_ID, new Object[]{id}, new MapperDocumentType());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public EntityDocumentType getDocumentTypeByName(String name) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_TYPES_BY_NAME, new Object[]{name}, new MapperDocumentType());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public List<EntityDocumentAuthor> getAllDocumentAuthor() {
        return this.template.query(FIND_ALL_DOCUMENT_AUTHORS, new Object[]{}, new MapperDocumentAuthor());
    }

    @Override
    public EntityDocumentAuthor getDocumentAuthorById(Integer id) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_AUTHORS_BY_ID, new Object[]{id}, new MapperDocumentAuthor());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public EntityDocumentAuthor getDocumentAuthorByName(String name) {
        try {
            return this.template.queryForObject(FIND_DOCUMENT_AUTHORS_BY_NAME, new Object[]{name}, new MapperDocumentAuthor());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }


    @Override
    public EntityNewFile getNewDocId(String fileUser) {
        try {
            return this.template.queryForObject(FIND_NEW_DOCUMENT_ID, new Object[]{fileUser, fileUser}, new MapperNewFile());
        } catch (EmptyResultDataAccessException ignored) {
        }
        return null;
    }

    @Override
    public void updateNewDocument(FileBucket fileBucket) {
        try {
            this.template.update(UPDATE_NEW_DOCUMENT_BY_ID, new Object[]{
                    fileBucket.getDocTitle(),
                    Integer.parseInt(fileBucket.getDocType()),
                    fileBucket.getDocDate(),
                    fileBucket.getDocDescription(),
                    fileBucket.getDocId()
            });
        } catch (Exception ignored) {
        }
    }

    @Override
    public void insertNewDocumentDocProc(Integer docId, Integer docProcess) {
        try {
            this.template.update(INSERT_NEW_INTO_DOC_PROC, new Object[]{docId, docProcess});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void insertNewDocumentDocAuthor(Integer docId, Integer authorId) {
        try {
            this.template.update(INSERT_NEW_INTO_DOC_AUTHOR, new Object[]{docId, authorId});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void insertNewDocumentAuthor(String authorName) {
        try {
            this.template.update(INSERT_NEW_INTO_AUTHOR, new Object[]{authorName});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void setUpdDocumentWithAttributes(la3v.logic.entities.document.EntityDocument entityDocument) {
        try {
            this.template.update(UPD_DOCUMENT_WITH_ATTRIBUTES, new Object[]{entityDocument.getAttributes().toString(), entityDocument.getDocId()});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void setUpdDocumentWithoutAttributes(la3v.logic.entities.document.EntityDocument entityDocument) {
        try {
//            log.info(String.format("=-=-=-=-=-=-=-="));
//            log.info(String.format("New DocTitle: %s", entityDocument.getDocTitle()));
//            log.info(String.format("New DocType: %s", entityDocument.getDocType()));
//            log.info(String.format("New AuthorName: %s", entityDocument.getAuthorName()));
//            log.info(String.format("=-=-=-=-=-=-=-="));

            this.template.update(UPD_DOCUMENT_WITHOUT_ATTRIBUTES, new Object[]{entityDocument.getDocTitle(),
                    Integer.parseInt(entityDocument.getDocType()), entityDocument.getDocDate(),
                    entityDocument.getDocDescription(), entityDocument.getDocId()});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void setUpdDocumentProc(la3v.logic.entities.document.EntityDocument entityDocument) {
        try {
            this.template.update(UPD_DOCUMENT_PROC, new Object[]{
                    Integer.parseInt(entityDocument.getDocProcess()), entityDocument.getDocId()});
        } catch (Exception ignored) {
        }
    }

    @Override
    public void setUpdDocumentProc(Integer docId, Integer procId) {
        try {
            this.template.update(UPD_DOCUMENT_PROC, new Object[]{
                    docId, procId});
        } catch (Exception ignored) {
        }
    }

    //    Archive part
    @Override
    public void deleteFromDocument(Integer id) {
        this.template.update(DELETE_FROM_DOCUMENT, new Object[]{id});
    }

    @Override
    public void deleteFromProcess(Integer id) {
        this.template.update(DELETE_FROM_DOC_PROC, new Object[]{id});
    }

    @Override
    public void deleteFromAuthor(Integer id) {
        this.template.update(DELETE_FROM_DOC_AUTHOR, new Object[]{id});
    }
}
