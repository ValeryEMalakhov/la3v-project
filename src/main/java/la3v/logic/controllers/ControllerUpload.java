package la3v.logic.controllers;

import la3v.logic.entities.document.EntityDocumentAuthor;
import la3v.logic.entities.document.EntityDocumentProc;
import la3v.logic.entities.document.EntityDocumentType;
import la3v.logic.files.entities.EntityNewFile;
import la3v.logic.files.entities.FileBucket;
import la3v.logic.files.packages.SystemCommandExecutor;
import la3v.logic.files.validators.FileValidator;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valery E. Malakhov on 27.05.2017.
 */
@Controller
@RequestMapping("/new")
public class ControllerUpload {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerUpload.class);

    private ApplicationContext context;

    private final String rootPath = System.getProperty("catalina.home");
    private final String regexAuthor = "(\\p{L}+?\\s\\p{L}\\.\\s??\\p{L}\\.)[\\;]??";
    private Integer resultProc;

    @Autowired
    FileValidator fileValidator;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {

        log.info(String.format("Logger in GET file ControllerUpload"));

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        List<EntityDocumentType> entityDocumentTypeList = repositoryDocument.getAllDocumentType();

        FileBucket fileModel = new FileBucket();

        model.addAttribute("fileBucket", fileModel);
        model.addAttribute("entityDocumentTypeList", entityDocumentTypeList);

        return "doc/fileUpload";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String singleFileUpload(@ModelAttribute la3v.logic.files.entities.FileBucket fileBucket,
                                   BindingResult result,
                                   Principal principal,
                                   ModelMap model)
            throws IOException, ParserConfigurationException, SAXException, InterruptedException {

        if (result.hasErrors()) {
            return "doc/fileUpload";
        } else {

            log.info(String.format("Logger in POST file ControllerUpload"));

            MultipartFile file = fileBucket.getFile();
            List<String> authors = new ArrayList<String>();

            IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);

            EntityDocumentAuthor entityDocumentAuthor;
            EntityDocumentProc entityDocumentProc = repositoryDocument.getDocumentProcById(1);
            EntityDocumentType entityDocumentType = repositoryDocument.getDocumentTypeById(Integer.parseInt(fileBucket.getDocType()));

            File directory = new File(rootPath + "/tmpFiles/" + principal.getName());
            if (! directory.exists()){
                directory.mkdir();
            }

            File serverFile = new File(rootPath + "/tmpFiles/" +
                    principal.getName() + "/" + file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(serverFile);
            fos.write(file.getBytes());
            fos.close();

            // logic

//            ('Процесс не определен', 'files/Documents/Unknown'),
//            ('Учебный процесс', 'files/Documents/Educational_process'),
//            ('Научный процесс', 'files/Documents/Scientific_process'),
//            ('Организационный процесс', 'files/Documents/Organizational_process'),
//            ('МАТ обеспечение', 'files/Documents/MAT_support')

            if (entityDocumentType.getType_id() == 1) {
                entityDocumentProc = repositoryDocument.getDocumentProcById(1);
            } else if (entityDocumentType.getType_id() >= 2 & entityDocumentType.getType_id() <= 12) {
                entityDocumentProc = repositoryDocument.getDocumentProcById(2);
            } else if (entityDocumentType.getType_id() >= 13 & entityDocumentType.getType_id() <= 23) {
                entityDocumentProc = repositoryDocument.getDocumentProcById(3);
            } else if (entityDocumentType.getType_id() >= 24 & entityDocumentType.getType_id() <= 35) {
                entityDocumentProc = repositoryDocument.getDocumentProcById(4);
            }

            log.info(String.format("=-=-=-=-=-=-=-="));
            log.info(String.format("Get fileName: %s", file.getOriginalFilename()));

            //log.info(String.format("Get fileID: %s", entityNewFile.getDocId()));
            log.info(String.format("Get fileOwner: %s", principal.getName()));

            log.info(String.format("Get docName: %s", fileBucket.getDocTitle()));
            log.info(String.format("Get doc_type: %s", fileBucket.getDocType()));
            log.info(String.format("Get proc_name: %s", entityDocumentProc.getProcName()));

            log.info(String.format("Get authors: %s", fileBucket.getDocAuthorsString()));
            log.info(String.format("Get date: %d", fileBucket.getDocDate()));

            log.info(String.format("=-=-=-=-=-=-=-="));
            log.info(String.format("Get current absolute filePath: %s", serverFile.getAbsolutePath()));
            log.info(String.format("Get new absolute filePath: %s", "/var/www/owncloud/data/" +
                    principal.getName() + "/files/" + entityDocumentProc.getProcDefaultWay() + "/"));

            //  bash /opt/tomcat/addNew.sh mod "Отчёт по научной работе МОКС 2016.doc" "Educational_process"
            /*
                #!/bin/bash
                echo 424@564ftfss | sudo -S chmod 777 -R /opt/tomcat/tmpFiles/
                echo 424@564ftfss | sudo -S mv "/opt/tomcat/tmpFiles/$1/$2" "/var/www/owncloud/data/$1/files/$3/"
                echo 424@564ftfss | sudo -S chown -R www-data:www-data "/var/www/owncloud/data/$1/files/$3/$2"
                echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan $1
            */

/*            Process process = Runtime.getRuntime().exec("echo 424@564ftfss | sudo -S bash /opt/tomcat/addNew.sh " + principal.getName() + " \"" + file.getOriginalFilename() + "\" \"" + entityDocumentProc.getProcDefaultWay() + "\"");
            try {
                process.waitFor();
            } catch (InterruptedException e) {
            }*/

            //String cmd = ("echo 3059 | sudo -S /bin/bash /opt/tomcat/addNew.sh " + principal.getName() + " \"" + file.getOriginalFilename() + "\" \"" + entityDocumentProc.getProcDefaultWay() + "\"");
            String cmd = ("/bin/bash /opt/tomcat/addNew.sh " + principal.getName() + " \"" + file.getOriginalFilename() + "\" \"" + entityDocumentProc.getProcDefaultWay() + "\"");

            try {
                log.info(String.format("Get command with params: [ %s ]", cmd));
            } catch (Exception e) {
            }

// build my command as a list of strings
            List<String> command = new ArrayList<String>();
            command.add("bash");
            command.add("/opt/tomcat/addNew.sh");
            command.add(principal.getName());
            command.add(file.getOriginalFilename());
            command.add(entityDocumentProc.getProcDefaultWay());

// execute my command
            SystemCommandExecutor commandExecutor = new SystemCommandExecutor(command, "424@564ftfss");
            resultProc = commandExecutor.executeCommand();

// get the output from the command
            StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
            StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();

// print the output from the command
            log.info(String.format("STDOUT"));
            log.info(String.format("%s", stdout));
            log.info(String.format("STDERR"));
            log.info(String.format("%s", stderr));

//            if (runProgramAndWait(cmd)) {
            log.info(String.format("File move!"));

            // получаем новый Id
            EntityNewFile entityNewFile = repositoryDocument.getNewDocId(principal.getName());
            fileBucket.setDocId(entityNewFile.getDocId());

            // Обновляем поля добавленного документа
            repositoryDocument.updateNewDocument(fileBucket);

            //region Получаем список авторов из строки
            log.info(String.format("=-=-=-=-=-=-=-="));
            Pattern pattern = Pattern.compile(regexAuthor);
            Matcher matcher = pattern.matcher(fileBucket.getDocAuthorsString());
            log.info(String.format("=-=-=-=-=-=-=-="));
            while (matcher.find()) {
                log.info(String.format("Start matcher.find"));
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    authors.add(matcher.group(i));
                    log.info(String.format("Get %d docAuthor: %s", i, matcher.group(i)));
                }
            }
            log.info(String.format("End matcher.find"));
            log.info(String.format("=-=-=-=-=-=-=-="));
            //endregion

            for (String str : authors) {
                log.info(String.format("Search docAuthor: %s", str));
                entityDocumentAuthor = repositoryDocument.getDocumentAuthorByName(str);
                if (entityDocumentAuthor != null) {
                    log.info(String.format("Get docAuthor: %d , from table", entityDocumentAuthor.getAuthor_id()));
                    repositoryDocument.insertNewDocumentDocAuthor(fileBucket.getDocId(), entityDocumentAuthor.getAuthor_id());
                } else {
                    log.info(String.format("docAuthor: %s - not found", str));
                    repositoryDocument.insertNewDocumentAuthor(str);
                    log.info(String.format("docAuthor: %s - added in table", str));
                    entityDocumentAuthor = repositoryDocument.getDocumentAuthorByName(str);
                    repositoryDocument.insertNewDocumentDocAuthor(fileBucket.getDocId(), entityDocumentAuthor.getAuthor_id());
                    log.info(String.format("Get docAuthor: %d , from table", entityDocumentAuthor.getAuthor_id()));
                }
            }

            return "redirect:/document/view/" + fileBucket.getDocId();
            //return "doc/fileUpload";
        }
    }
}
