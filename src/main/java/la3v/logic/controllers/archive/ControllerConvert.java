package la3v.logic.controllers.archive;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import la3v.logic.attributes.archive.*;
import la3v.logic.entities.archive.EntityProtocol;
import la3v.logic.entities.document.EntityDocument;
import la3v.logic.files.packages.SystemCommandExecutor;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Marmonth on 05.06.2017.
 */
@Controller
@RequestMapping("/archive/archivation")
public class ControllerConvert {

    private ApplicationContext context;

    private la3v.logic.entities.archive.EntityDocument documentToArchive;
    private EntityDocument documentFromDocumentSystem;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private SimpleDateFormat dateYearFormat = new SimpleDateFormat("yyyy");
    private Map<String,IAttributes> attributeMap = new AttributesMap().getAttributeMap();
    private Map<String,String> attributeRusEng = new AttributesTranslate().getAttributesRusEng();

    private final String rootPath = System.getProperty("/var/www/owncloud/data/archive/files/Archive/");

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerArchive.class);

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showArchivation(Model model, @PathVariable("id") Integer docId) throws Exception {
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        documentFromDocumentSystem = repositoryDocument.findById(docId);

        if(documentFromDocumentSystem == null)
            throw new Exception("Database error! There is no document with such ID!");

        model.addAttribute("documentToArchive", documentFromDocumentSystem);
        model.addAttribute("addressPart", docId);
        model.addAttribute("date", dateFormat.format(new Date()));

        return "archive/documentToArchive";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String toArchive(@ModelAttribute("documentToArchive") la3v.logic.entities.archive.EntityDocument documentFromForm,
                            BindingResult bindingResult, Model model,
                            @PathVariable("id") Integer docId) throws Exception {

        documentToArchive = documentFromForm;

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        EntityDocument document = repositoryDocument.findById(docId);

        if(document == null)
            throw new Exception("Database error! There is no document with such ID!");

        String docType = attributeRusEng.get(document.getDocType().toLowerCase());

        if(docType == null)
            throw new Exception("There is no such attribute in attribute map!");
        return "redirect:{id}/attributes/" + docType;
    }

    @RequestMapping(value = "/{id}/attributes/{docType}", method = RequestMethod.GET)
    public String showListAttributes(Model model, @PathVariable("id") Integer documentId, @PathVariable("docType") String docType) throws Exception {

        IAttributes attributes = attributeMap.get(docType.toLowerCase());

        if (attributes == null)
            throw new Exception("There is no such attribute in attribute map!");

        attributes.getFromJson(documentFromDocumentSystem.getAttributes());

        model.addAttribute("documentFromSystem", documentFromDocumentSystem);
        model.addAttribute("documentFromSystemAttr", attributes.getFromJson(documentFromDocumentSystem.getAttributes()));

        model.addAttribute("attributes", attributes);
        model.addAttribute("documentToArchive", documentToArchive);
        model.addAttribute("addressPart", documentId);

        log.info(String.format("IN /archivation/{id}/attributes/" + docType + " GET"));

        return "archive/attributes/" + docType.toLowerCase();
    }


    private void archiveDocument(IAttributes attributes, String documentId, Principal principal) throws Exception
    {
        log.info(String.format("documentId: %s", documentId));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(gson.toJson(attributes)).getAsJsonObject();

        documentToArchive.setAttributes(obj);

        log.info(String.format("processType: [ %s ]", documentToArchive.getAttributes().get("process").getAsString().toLowerCase()));
        String process = new ProcessTypeTranslate().getProcessRusEng().get(documentToArchive.getAttributes().get("process").getAsString().toLowerCase());

        documentToArchive.setArchivePath("/var/www/owncloud/data/archive/files/Archive/" + dateYearFormat.format(new Date()) + "/" + process);

        String cmd = ("/bin/bash /opt/tomcat/archiveConverter.sh " + principal.getName() + " \""
                + documentFromDocumentSystem.getFileName() + "\" \"" + "/var/www/owncloud/data/archive/files/Archive/" + dateYearFormat.format(new Date()) + "/" + process + "\"");

        try {
            log.info(String.format("Get command with params: [ %s ]", cmd));
        } catch (Exception e) {
        }

//        documentToArchive.setAttributes(obj);
//        documentToArchive.setArchivePath("/var/www/owncloud/data/archive/files/Archive/" + dateYearFormat.format(new Date()) + "/" + process);

        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);

        repositoryArchive.insertDocument(documentToArchive);
        Integer maxProtocolId = repositoryArchive.getMaxProtocolId() + 1;

        EntityProtocol entityProtocol = new EntityProtocol(
                principal.getName(),
                "Протокол " + maxProtocolId,
                "Добавление в архив",
                "Добавление в архив документа " + documentToArchive.getName() + " пользователем " + principal.getName() + " в " + dateFormat.format(new Date()) + " " + timeFormat.format(new Date()),
                dateFormat.format(new Date()),
                timeFormat.format(new Date()),
                repositoryArchive.getDocument(documentToArchive).getId()
        );

        repositoryArchive.insertProtocol(entityProtocol);

        /*
        echo 424@564ftfss | sudo -S mv "$3" "/var/www/owncloud/data/archive/files/archive/$2/$1"
echo 424@564ftfss | sudo -S chown -R www-data:www-data "/var/www/owncloud/data/archive/files/archive/$2/$1"
echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan $2
echo 424@564ftfss | sudo -S -u www-data php /var/www/owncloud/console.php files:scan archive
         */

        File directory = new File(rootPath + dateYearFormat.format(new Date()) + "/" + process + "/");
        if (! directory.exists()){
            directory.mkdir();
        }


// build my command as a list of strings
        List<String> command = new ArrayList<String>();
        command.add("bash");
        command.add("/opt/tomcat/archiveConverter.sh");

        command.add(documentFromDocumentSystem.getFileName());
        command.add(principal.getName());
        //command.add("/var/www/owncloud/data/archive/files/Archive/" + dateYearFormat.format(new Date()) + "/" + process);
        command.add("/var/www/owncloud/data/" + principal.getName() + documentFromDocumentSystem.getFilePath());
        command.add(dateYearFormat.format(new Date()));
        command.add(process);

// execute my command
        SystemCommandExecutor commandExecutor = new SystemCommandExecutor(command, "424@564ftfss");
        Integer resultProc = commandExecutor.executeCommand();

// get the output from the command
        StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
        StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();

// print the output from the command
        log.info(String.format("STDOUT"));
        log.info(String.format("%s", stdout));
        log.info(String.format("STDERR"));
        log.info(String.format("%s", stderr));

//            if (runProgramAndWait(cmd)) {
        log.info(String.format("File moved to archive!"));

        /*repositoryDocument.deleteFromAuthor(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));
        repositoryDocument.deleteFromProcess(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));
        repositoryDocument.deleteFromDocument(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));*/
    }

//    @RequestMapping(value = "/archivation/{id}/attributes/{docType}", method = RequestMethod.POST)
//    public String showListAttributes(@ModelAttribute("attributes") IAttributes attributes, @PathVariable("id") Integer documentId, Principal principal){
//        archiveDocument(attributes, documentId, principal);
//        return "redirect:/archive/all";
//    }

    @RequestMapping(value = "/{id}/attributes/advert", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") AdvertAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/article", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ArticleAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/classroomLoadJournal", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ClassroomLoadJournalAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/commonShedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CommonScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/conferenceIncoming", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceIncomingAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/conferenceProgram", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceProgramAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/conferenceThesis", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceThesisAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/conferenceThesisCollection", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceThesisCollectionAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/courseWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CourseWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/diploma", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DiplomaAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/disciplineCourseWorkInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DisciplineCourseWorkInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/disciplineExtramuralInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DisciplineExtramuralInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/disciplineLabWorkInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DisciplineLabWorkInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/disciplineLectionConspectus", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DisciplineLectionConspectusAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/disciplineWorkingProgram", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DisciplineWorkingProgramAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/educationalEdition", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") EducationalEditionAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/lecturerForm", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerFormAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/lecturerPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/monograph", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") MonographAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/patent", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PatentAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/pilpitSessionProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitSessionProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/pilpitWorkingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/pilpitWorkingReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingReportAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/positionInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PositionInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/progressJournal", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProgressJournalAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/protocolStatements", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProtocolStatementsAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/researchWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/researchWorkReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkReportAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/researchWorkRequest", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkRequestAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/schedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/scientificSeminarProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScientificSeminarProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/specialtiesPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") SpecialtiesPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/studentsList", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") StudentsListAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/traineeship", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TraineeshipAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/tutorial", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TutorialAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/workingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") WorkingPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/{id}/attributes/undefined", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") UndefinedAttributes attributes, @PathVariable("id") String documentId, Principal principal) throws Exception {
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("err/pageControllerError");

        model.addObject("errMsg", ex.getMessage());
        return model;
    }
}