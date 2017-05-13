package la3v.logic.repositories.classes;

import la3v.logic.entities.EntityDocument;
import la3v.logic.entities.archive.*;
import la3v.logic.mappers.MapperDocument;
import la3v.logic.mappers.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Marmonth on 21.04.2017.
 */
@Repository
public class ImplementationRepositoryArchive implements IRepositoryArchive {

    private JdbcTemplate template;

    private static final String FIND_ARCHIVED_DOCUMENT = "SELECT * FROM \"archive\".\"Document\" ORDER BY documentId ASC";
    private static final String FIND_DOCUMENT_BY_ID = "SELECT * FROM \"archive\".\"Document\" WHERE documentId = ?";
    //private static final String FIND_ALL_PROTOCOLS = "SELECT * FROM \"archive\".\"Protocol\"";
    private static final String FIND_ALL_PROTOCOLS = "SELECT protocolid, protocolname, protocoldate, protocoltime, userlastname, protocolaction, protocolcomments, documentname\n" +
            "FROM \"archive\".\"Protocol\" as Prot \n" +
            "JOIN \"archive\".\"Document\" as Doc ON Prot.protocoldocumentid = Doc.documentid\n" +
            "JOIN \"archive\".\"User\" as Usr ON Usr.userid = Prot.protocoluserid;";

    //private static final String FIND_ALL_DELETE_PROTOCOLS = "SELECT * FROM \"archive\".\"ProtocolOfDelete\"";
    private static final String FIND_ALL_DELETE_PROTOCOLS = "SELECT protocoldelid, protocoldelname, protocoldeldate, protocoldeltime, protocoldelcomments, userlastname, protocoldeldocname, protocoldeldocauthor, protocoldeldocarchivepath, protocolDelDocDateOfCreation, protocolDelDocDateOfArchiving, protocolDelDocArchivingTerm, protocolDelDocComments\n" +
            "FROM \"archive\".\"ProtocolOfDelete\" as Prot \n" +
            "JOIN \"archive\".\"User\" as Usr ON Usr.userid = Prot.protocoldeluserid;";

    private static final String FIND_ALL_DOCUMENT_OWNCLOUD =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls') order by file.storage, file.name;";

    private static final String FIND_ALL_DOCUMENT_OWNCLOUD_ID =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file where path_hash = ? and st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls');";

    @Autowired
    public ImplementationRepositoryArchive(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<la3v.logic.entities.archive.EntityDocument> getAllArchivedDocumentList() {
        return this.template.query(FIND_ARCHIVED_DOCUMENT, new Object[]{}, new la3v.logic.mappers.archive.MapperDocument());
    }

    @Override
    public la3v.logic.entities.archive.EntityDocument findById(Integer id) {
        return this.template.queryForObject(FIND_DOCUMENT_BY_ID, new Object[]{id}, new la3v.logic.mappers.archive.MapperDocument());
    }

    @Override
    public List<EntityProtocolOutput> getAllProtocolList() {
        return this.template.query(FIND_ALL_PROTOCOLS, new Object[]{}, new MapperProtocolOutput());
    }

    @Override
    public List<EntityProtocolOfDeleteOutput> getAllProtocolOfDeleteList() {
        return this.template.query(FIND_ALL_DELETE_PROTOCOLS, new Object[]{}, new MapperProtocolOfDeleteOutput());
    }

    @Override
    public EntityDocument findByHash(String hash) {
        return this.template.queryForObject(FIND_ALL_DOCUMENT_OWNCLOUD_ID, new Object[]{hash}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getAllDocumentList() {
        return this.template.query(FIND_ALL_DOCUMENT_OWNCLOUD, new Object[]{}, new MapperDocument());
    }
}
