package la3v.logic.entities.document;

import com.google.gson.JsonObject;

import java.util.Date;

/**
 * Created by Marmonth on 13.05.2017.
 */
public class EntityDocument {

    private Integer docId, fileStorage, docDate;
    private JsonObject attributes;
    private String fileUser, docTitle, fileName, docType, authorName, docProcess, docDescription, filePath;

    public EntityDocument() {
    }

    public EntityDocument(Integer docId, Integer fileStorage, Integer docDate, JsonObject attributes,
                          String fileUser, String docTitle, String fileName, String docType,
                          String authorName, String docProcess, String docDescription, String filePath) {
        this.docId = docId;
        this.fileStorage = fileStorage;
        this.docDate = docDate;
        this.attributes = attributes;
        this.fileUser = fileUser;
        this.docTitle = docTitle;
        this.fileName = fileName;
        this.docType = docType;
        this.authorName = authorName;
        this.docProcess = docProcess;
        this.docDescription = docDescription;
        this.filePath = filePath;
    }

    public EntityDocument(Integer fileStorage, Integer docDate, JsonObject attributes,
                          String fileUser, String docTitle, String fileName, String docType,
                          String authorName, String docProcess, String docDescription, String filePath) {
        this.fileStorage = fileStorage;
        this.docDate = docDate;
        this.attributes = attributes;
        this.fileUser = fileUser;
        this.docTitle = docTitle;
        this.fileName = fileName;
        this.docType = docType;
        this.authorName = authorName;
        this.docProcess = docProcess;
        this.docDescription = docDescription;
        this.filePath = filePath;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(Integer fileStorage) {
        this.fileStorage = fileStorage;
    }

    public Integer getDocDate() {
        return docDate;
    }

    public void setDocDate(Integer docDate) {
        this.docDate = docDate;
    }

    public JsonObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JsonObject attributes) {
        this.attributes = attributes;
    }

    public String getFileUser() {
        return fileUser;
    }

    public void setFileUser(String fileUser) {
        this.fileUser = fileUser;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDocProcess() {
        return docProcess;
    }

    public void setDocProcess(String docProcess) {
        this.docProcess = docProcess;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}