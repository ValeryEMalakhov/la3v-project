package la3v.logic.repositories.interfaces;

import la3v.logic.entities.EntityDocument;

import java.util.List;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
public interface IRepositoryDocument extends IRepository<EntityDocument> {

    List<EntityDocument> getAllDocumentList();

    List<EntityDocument> getAllArchivedDocumentList();

    EntityDocument findByDocTemplateId(Integer id);
}
