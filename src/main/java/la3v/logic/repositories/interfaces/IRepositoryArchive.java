package la3v.logic.repositories.interfaces;

import la3v.logic.entities.archive.*;

import java.util.List;

/**
 * Created by Marmonth on 21.04.2017.
 */
public interface IRepositoryArchive extends IRepository<EntityDocument> {

    List<EntityDocument> getAllArchivedDocumentList();

    List<EntityProtocolOutput> getAllProtocolList();

    List<EntityProtocolOfDeleteOutput> getAllProtocolOfDeleteList();
}
