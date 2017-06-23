package la3v.logic.repositories.classes;

import la3v.logic.entities.archive.*;
import la3v.logic.mappers.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final String FIND_ARCHIVED_DOCUMENT_BY_ID = "SELECT * FROM \"archive\".\"Document\" WHERE documentId = ?";
    private static final String FIND_ALL_PROTOCOLS = "SELECT protocolid, protocolname, protocoldate, protocoltime, protocoluser, protocolaction, protocolcomments, documentname\n" +
            "FROM \"archive\".\"Protocol\" as Prot \n" +
            "JOIN \"archive\".\"Document\" as Doc ON Prot.protocoldocumentid = Doc.documentid;";

    private static final String FIND_ALL_DELETE_PROTOCOLS =
            "SELECT * FROM \"archive\".\"ProtocolOfDelete\";";

    private static final String INSERT_INTO_DOCUMENT =
            "INSERT INTO \"archive\".\"Document\" (documentname, documentauthor, documentpath, " +
                    "documentdateofarchiving, documentattributes, documentcomments, documentarchivepath, documentarchivingterm) " +
                    "VALUES(?, ?, ?, ?::DATE, ?::JSONB, ?, ?, ?);";


    private static final String INSERT_PROTOCOL = "INSERT INTO \"archive\".\"Protocol\" (protocolName, protocolDate, protocolTime, " +
            "protocolUser, protocolAction, protocolComments, protocolDocumentId)" +
            " VALUES (?, ?::DATE, ?::TIME, ?, ?, ?, ?)";

    private static final String FIND_ARCHIVED_DOCUMENT_BY_DATA = "SELECT * FROM \"archive\".\"Document\" \n" +
            "WHERE documentname = ?\n" +
            "and documentauthor = ?\n" +
            "and documentpath = ?\n" +
            "and documentdateofarchiving = ?::DATE\n" +
            "and documentattributes = ?::JSONB\n" +
            "and documentcomments = ?\n" +
            "and documentarchivepath = ?\n" +
            "and documentarchivingterm = ?";

    private static final String DELETE_ARCHIVED_DOCUMENT = "DELETE FROM \"archive\".\"Document\" WHERE documentId = ?";
    private static final String DELETE_DOCUMENT_PROTOCOL = "DELETE FROM \"archive\".\"Protocol\" WHERE protocolDocumentId = ?";

    private static final String INSERT_PROTOCOL_OF_DELETE = "INSERT INTO \"archive\".\"ProtocolOfDelete\" (protocolDelName,\tprotocolDelDate, protocolDelTime, protocolDelComments, protocolDelUser, protocolDelDocName, protocolDelDocAuthor,\tprotocolDelDocArchivePath,\t\n" +
            "\tprotocolDelDocDateOfArchiving, protocolDelDocArchivingTerm, protocolDelDocComments)\n" +
            " VALUES (?, ?::DATE, ?::TIME, ?, ?, ?, ?, ?, ?::DATE, ?, ?);";

    private static final String FIND_ALL_EXTENDED_DOCTYPE = "SELECT * FROM \"archive\".\"Document\"\n" +
            "WHERE documentattributes ->> 'docType' = ?;";

    private static final String FIND_ALL_EXTENDED_PROCESS = "SELECT * FROM \"archive\".\"Document\"\n" +
            "WHERE documentattributes ->> 'process' = ?;";

    private static final String FIND_MAX_PROTOCOL_ID = "SELECT MAX(protocolid) FROM \"archive\".\"Protocol\"";

    private static final String FIND_MAX_DELETE_PROTOCOL_ID = "SELECT MAX(protocoldelid) FROM \"archive\".\"ProtocolOfDelete\"";

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ImplementationRepositoryArchive.class);

    @Autowired
    public ImplementationRepositoryArchive(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<la3v.logic.entities.archive.EntityDocument> getAllArchivedDocumentList() {
        return this.template.query(FIND_ARCHIVED_DOCUMENT, new Object[]{}, new MapperDocument());
    }

    @Override
    public EntityDocument findById(Integer id) {
        return this.template.queryForObject(FIND_ARCHIVED_DOCUMENT_BY_ID, new Object[]{id}, new MapperDocument());
    }

    @Override
    public List<EntityProtocolOutput> getAllProtocolList() {
        return this.template.query(FIND_ALL_PROTOCOLS, new Object[]{}, new MapperProtocolOutput());
    }

    @Override
    public List<EntityProtocolOfDelete> getAllProtocolOfDeleteList() {
        return this.template.query(FIND_ALL_DELETE_PROTOCOLS, new Object[]{}, new MapperProtocolOfDelete());
    }

    @Override
    public void insertDocument(la3v.logic.entities.archive.EntityDocument entityDocument)
    {

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
    public void insertProtocol(EntityProtocol entityProtocol)
    {
        this.template.update(INSERT_PROTOCOL, new Object[]{
                entityProtocol.getName(),
                entityProtocol.getDate(),
                entityProtocol.getTime(),
                entityProtocol.getUser(),
                entityProtocol.getAction(),
                entityProtocol.getComments(),
                entityProtocol.getDocumentId()
        });
    }

    @Override
    public EntityDocument getDocument(EntityDocument entityDocument)
    {
        return this.template.queryForObject(FIND_ARCHIVED_DOCUMENT_BY_DATA, new Object[]
                {entityDocument.getName(),
                        entityDocument.getAuthor(),
                        entityDocument.getPath(),
                        entityDocument.getDateOfArchiving(),
                        entityDocument.getAttributes().toString(),
                        entityDocument.getComments(),
                        entityDocument.getArchivePath(),
                        entityDocument.getArchivingTerm()}, new MapperDocument());
    }

    @Override
    public void deleteArchivedDocument(Integer id)
    {
        this.template.update(DELETE_ARCHIVED_DOCUMENT, new Object[]{id});
    }

    @Override
    public void deleteDocumentProtocol(Integer id)
    {
        this.template.update(DELETE_DOCUMENT_PROTOCOL, new Object[]{id});
    }

    @Override
    public void insertProtocolOfDelete(EntityProtocolOfDelete entityProtocolOfDelete)
    {
        this.template.update(INSERT_PROTOCOL_OF_DELETE, new Object[]{
                entityProtocolOfDelete.getName(),
                entityProtocolOfDelete.getDate(),
                entityProtocolOfDelete.getTime(),
                entityProtocolOfDelete.getComments(),
                entityProtocolOfDelete.getUser(),
                entityProtocolOfDelete.getDocumentName(),
                entityProtocolOfDelete.getDocumentAuthor(),
                entityProtocolOfDelete.getDocumentArchivePath(),
                entityProtocolOfDelete.getDocumentDateOfArchiving(),
                entityProtocolOfDelete.getArchivingTerm(),
                entityProtocolOfDelete.getDocumentComments()
        });
    }

    @Override
    public List<EntityDocument> getExtendedDocType(String docType)    {
        return this.template.query(FIND_ALL_EXTENDED_DOCTYPE, new Object[]{docType}, new MapperDocument());
    }

    @Override
    public List<EntityDocument> getExtendedProcess(String procType)    {
        return this.template.query(FIND_ALL_EXTENDED_PROCESS, new Object[]{procType}, new MapperDocument());
    }

    @Override
    public Integer getMaxProtocolId()    {
        return this.template.queryForObject(FIND_MAX_PROTOCOL_ID, new Object[]{}, Integer.class);
    }


    @Override
    public Integer getMaxDeleteProtocolId()    {
        return this.template.queryForObject(FIND_MAX_DELETE_PROTOCOL_ID, new Object[]{}, Integer.class);
    }
}

