package la3v.logic.repositories.interfaces;

import la3v.logic.entities.document.EntityDocument;
import la3v.logic.entities.document.EntityDocumentAuthor;
import la3v.logic.entities.document.EntityDocumentProc;
import la3v.logic.entities.document.EntityDocumentType;
import la3v.logic.files.entities.EntityNewFile;
import la3v.logic.files.entities.FileBucket;

import java.util.List;

/**
 * Created by Valery E. Malakhov on 02.04.2017.
 */
public interface IRepositoryDocument extends IRepository<EntityDocument> {

    List<EntityDocument> getAllDocumentList(String fileUser);

    List<EntityDocumentProc> getAllDocumentProc();

    EntityDocumentProc getDocumentProcById(Integer id);

    EntityDocumentProc getDocumentProcByName(String name);

    List<EntityDocumentType> getAllDocumentType();

    EntityDocumentType getDocumentTypeById(Integer id);

    EntityDocumentType getDocumentTypeByName(String name);

    List<EntityDocumentAuthor> getAllDocumentAuthor();

    EntityDocumentAuthor getDocumentAuthorById(Integer id);

    EntityDocumentAuthor getDocumentAuthorByName(String name);

    EntityNewFile getNewDocId(String fileUser);

    void updateNewDocument(FileBucket fileBucket);

    void insertNewDocumentDocProc(Integer docId, Integer docProcess);

    void insertNewDocumentDocAuthor(Integer docId, Integer authorId);

    void insertNewDocumentAuthor(String authorName);

    void setUpdDocumentWithAttributes(EntityDocument entityDocument);

    void setUpdDocumentWithoutAttributes(EntityDocument entityDocument);

    void setUpdDocumentProc(EntityDocument entityDocument);

    void setUpdDocumentProc(Integer docId, Integer procId);

    void deleteFromDocument(Integer id);

    void deleteFromProcess(Integer id);
    
    void deleteFromAuthor(Integer id);
}
