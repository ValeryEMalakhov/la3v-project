package la3v.logic.files.validators;

import la3v.logic.files.entities.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Артём on 30.03.2017.
 */
@Component
public class FileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;

        if(file.getFile()!=null){
            if (file.getFile().isEmpty()) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}
