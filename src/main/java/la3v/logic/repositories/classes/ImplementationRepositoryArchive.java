package la3v.logic.repositories.classes;

import la3v.logic.entities.document.EntityDocument;
import la3v.logic.entities.archive.*;
import la3v.logic.mappers.document.MapperDocument;
import la3v.logic.mappers.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Marmonth on 21.04.2017.
 */
@Repository
public class ImplementationRepositoryArchive implements IRepositoryArchive {

    private JdbcTemplate template;

    private static final String FIND_ARCHIVED_DOCUMENT = "SELECT * FROM \"archive\".\"Document\" ORDER BY documentId ASC";
    private static final String FIND_ARCHIVED_DOCUMENT_BY_ID = "SELECT * FROM \"archive\".\"Document\" WHERE documentId = ?";
    private static final String FIND_ALL_PROTOCOLS = "SELECT protocolid, protocolname, protocoldate, protocoltime, userlastname, protocolaction, protocolcomments, documentname\n" +
            "FROM \"archive\".\"Protocol\" as Prot \n" +
            "JOIN \"archive\".\"Document\" as Doc ON Prot.protocoldocumentid = Doc.documentid\n" +
            "JOIN \"archive\".\"User\" as Usr ON Usr.userid = Prot.protocoluserid;";

    private static final String FIND_ALL_DELETE_PROTOCOLS =
            "SELECT protocoldelid, protocoldelname, protocoldeldate, protocoldeltime, protocoldelcomments, userlastname, protocoldeldocname, protocoldeldocauthor, protocoldeldocarchivepath, protocolDelDocDateOfCreation, protocolDelDocDateOfArchiving, protocolDelDocArchivingTerm, protocolDelDocComments\n" +
            "FROM \"archive\".\"ProtocolOfDelete\" as Prot \n" +
            "JOIN \"archive\".\"User\" as Usr ON Usr.userid = Prot.protocoldeluserid;";

    private static final String FIND_ALL_DOCUMENT_OWNCLOUD =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file " +
                    "where st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' " +
                    "and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls') order by file.storage, file.name;";

    private static final String FIND_ALL_DOCUMENT_OWNCLOUD_ID =
            "select u.uid, file.storage, file.name, file.path, file.path_hash from oc_users as u, oc_storages as st, oc_filecache as file " +
                    "where path_hash = ? and st.id = 'home::' || u.uid and file.storage = st.numeric_id and u.uid = 'main' " +
                    "and (file.path like '%.doc' or file.path like '%.docx'  or file.path like '%.pdf' or file.path like '%.xls');";

    private static final String INSERT_INTO_DOCUMENT =
            "INSERT INTO \"archive\".\"Document\" (documentname, documentauthor, documentpath, documentdateofcreation, " +
                    "documentdateofarchiving, documentattributes, documentcomments, documentarchivepath, documentarchivingterm) " +
                    "VALUES(?, ?, ?, ?::DATE, ?::DATE, ?::JSONB, ?, ?, ?);";

    private static final String FIND_DOCUMENT_BY_ID = "SELECT * FROM \"document\".\"document\" WHERE doc_id = ?";

    private static final String DELETE_FROM_DOCUMENT = "DELETE FROM \"document\".\"document\" WHERE doc_id = ?";
    private static final String DELETE_FROM_DOC_PROC = "DELETE FROM \"document\".\"doc_proc\" WHERE doc_id = ?;";
    private static final String DELETE_FROM_DOC_COAUTHOR = "DELETE FROM \"document\".\"doc_coauthor\" WHERE doc_id = ?;";


    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ImplementationRepositoryArchive.class);

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
        return this.template.queryForObject(FIND_ARCHIVED_DOCUMENT_BY_ID, new Object[]{id}, new la3v.logic.mappers.archive.MapperDocument());
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

    @Override
    public void insertDocument(la3v.logic.entities.archive.EntityDocument entityDocument)
    {
        /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date dateOfArchiving, dateOfCreation;
        try {
            dateOfArchiving = format.parse(entityDocument.getDateOfArchiving());
            dateOfCreation = format.parse(entityDocument.getDateOfCreation());
        } catch (ParseException e) {
            log.info(String.format("---------------------------------------"));
            log.info(String.format("ERROR WITH DATE!!!. dateOfArchiving %s, dateOfCreation %s", entityDocument.getDateOfArchiving(), entityDocument.getDateOfCreation()));
            log.info(String.format("---------------------------------------"));
            e.printStackTrace();
            return;
        }*/

        /*log.info(String.format("---------------------------------------"));
        log.info(String.format("INSERT"));
        log.info(String.format("getName %s", entityDocument.getName()));
        log.info(String.format("getAuthor %s", entityDocument.getAuthor()));
        log.info(String.format("getPath %s", entityDocument.getPath()));
        log.info(String.format("getComments %s", entityDocument.getComments()));
        log.info(String.format("getArchivePath %s", entityDocument.getArchivePath()));
        log.info(String.format("getAttributes %s", entityDocument.getAttributes().toString()));
        log.info(String.format("getArchivingTerm %s", entityDocument.getArchivingTerm()));
        log.info(String.format("getDateOfCreation %s", entityDocument.getDateOfCreation()));
        log.info(String.format("getDateOfArchiving %s", entityDocument.getDateOfArchiving()));*/

        this.template.update(INSERT_INTO_DOCUMENT, new Object[]{
                entityDocument.getName(),
                entityDocument.getAuthor(),
                entityDocument.getPath(),
                entityDocument.getDateOfCreation(),
                entityDocument.getDateOfArchiving(),
                entityDocument.getAttributes().toString(),
                entityDocument.getComments(),
                entityDocument.getArchivePath(),
                entityDocument.getArchivingTerm()
        });
        /*log.info(String.format("AFTER INSERT"));
        log.info(String.format("---------------------------------------"));*/
    }

    @Override
    public void deleteFromDocument(Integer id)
    {
        this.template.update(DELETE_FROM_DOCUMENT, new Object[]{id});
    }

    @Override
    public void deleteFromProcess(Integer id)
    {
        this.template.update(DELETE_FROM_DOC_PROC, new Object[]{id}, new MapperDocument());
    }

    @Override
    public void deleteFromCoauthor(Integer id)
    {
        this.template.update(DELETE_FROM_DOC_COAUTHOR, new Object[]{id}, new MapperDocument());
    }

    @Override
    public EntityDocument findDocumentById(Integer id)
    {
        return this.template.queryForObject(FIND_DOCUMENT_BY_ID, new Object[]{id}, new MapperDocument());
    }
}
