package la3v.logic.repositories.interfaces;

import la3v.logic.entities.archive.*;

import java.util.List;

/**
 * Created by Marmonth on 21.04.2017.
 */
public interface IRepositoryArchive extends IRepository<EntityDocument> {

    List<EntityDocument> getAllArchivedDocumentList();

    List<EntityProtocolOutput> getAllProtocolList();

    List<EntityProtocolOfDelete> getAllProtocolOfDeleteList();

    void insertDocument(EntityDocument entityDocument);

    void insertProtocol(EntityProtocol entityProtocol);

    EntityDocument getDocument(EntityDocument entityDocument);

    void deleteArchivedDocument(Integer id);

    void deleteDocumentProtocol(Integer id);

    void insertProtocolOfDelete(EntityProtocolOfDelete entityProtocolOfDelete);

    List<EntityDocument> getExtended(String docType);
}
