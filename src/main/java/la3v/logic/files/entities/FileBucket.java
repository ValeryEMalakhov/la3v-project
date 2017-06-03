package la3v.logic.files.entities;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Артём on 30.03.2017.
 */
public class FileBucket {

    MultipartFile file;

    private Integer docId, docDate;
    private List<String> authors;
    private String docTitle, docType, docAuthorsString, docDescription;

    public FileBucket() {
    }

    public FileBucket(Integer docId, Integer docDate, List<String> authors,
                      String docTitle, String docType, String docAuthorsString,
                      String docDescription) {
        this.docId = docId;
        this.docDate = docDate;
        this.authors = authors;
        this.docTitle = docTitle;
        this.docType = docType;
        this.docAuthorsString = docAuthorsString;
        this.docDescription = docDescription;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocDate() {
        return docDate;
    }

    public void setDocDate(Integer docDate) {
        this.docDate = docDate;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocAuthorsString() {
        return docAuthorsString;
    }

    public void setDocAuthorsString(String docAuthorsString) {
        this.docAuthorsString = docAuthorsString;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public void setDocDescription(String docDescription) {
        this.docDescription = docDescription;
    }
}