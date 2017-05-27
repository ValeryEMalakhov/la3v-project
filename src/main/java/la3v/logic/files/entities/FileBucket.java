package la3v.logic.files.entities;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Артём on 30.03.2017.
 */
public class FileBucket {

    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
