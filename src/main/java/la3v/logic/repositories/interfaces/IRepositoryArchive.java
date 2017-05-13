package la3v.logic.repositories.interfaces;

import la3v.logic.entities.EntityDocument;
import la3v.logic.entities.archive.*;

import java.util.List;

/**
 * Created by Marmonth on 21.04.2017.
 */
public interface IRepositoryArchive extends IRepository<la3v.logic.entities.archive.EntityDocument> {

    List<la3v.logic.entities.archive.EntityDocument> getAllArchivedDocumentList();

    List<EntityProtocolOutput> getAllProtocolList();

    List<EntityProtocolOfDeleteOutput> getAllProtocolOfDeleteList();

    EntityDocument findByHash(String hash);

    List<EntityDocument> getAllDocumentList();
}
