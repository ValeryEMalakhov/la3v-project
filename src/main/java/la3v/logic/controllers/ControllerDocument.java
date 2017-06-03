package la3v.logic.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import la3v.logic.attributes.document.*;
import la3v.logic.entities.document.*;
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

import java.security.Principal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.SimpleDateFormat;

/**
 * Created by Valery E. Malakhov on 01.04.2017.
 */
@Controller
@RequestMapping("/document")
public class ControllerDocument {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerUpload.class);

    la3v.logic.entities.document.EntityDocument entityDocumentBase;

    private ApplicationContext context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private final String regexAuthor = "(\\p{L}+?\\s\\p{L}\\.\\s??\\p{L}\\.)[\\;]??";

    private Map<String, IAttributes> attributeMap = new AttributesMap().getAttributeMap();
    private Map<String, String> attributeRusEng = new AttributesRusEng().getAttributesRusEng();

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showDocumentList(Model model, Principal principal) {
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        List<EntityDocument> entityDocumentList = repositoryDocument.getAllDocumentList(principal.getName());
        model.addAttribute("entityDocumentList", entityDocumentList);

        return "doc/document";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String showViewDocumentForm(Model model, @PathVariable("id") Integer docId) throws Exception {

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        la3v.logic.entities.document.EntityDocument entityDocument = repositoryDocument.findById(docId);

        JsonObject jsonObj = entityDocument.getAttributes();

        if (entityDocument == null)
            throw new Exception("Database error! There is no document with such ID!");

        if (attributeMap.get(entityDocument.getDocType().toLowerCase()) != null) {
            IAttributes attributes = attributeMap.get(entityDocument.getDocType().toLowerCase()).getFromJson(jsonObj);
            model.addAttribute("entityDocumentAttribute", attributes.toListString());
        }

//        if(entityDocument.getDocDate() == 0){
//            entityDocument.setDocDate(Calendar.getInstance().get(Calendar.YEAR));
//        }

        model.addAttribute("entityDocument", entityDocument);
        return "doc/attributes";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.POST)
    public String nextViewDocumentForm(Model model, @PathVariable("id") Integer docId) throws Exception {

        return "redirect:/document/edit/" + docId;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showEditDocumentForm(Model model, @PathVariable("id") Integer docId) throws Exception {

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        la3v.logic.entities.document.EntityDocument entityDocument = repositoryDocument.findById(docId);
        List<EntityDocumentType> entityDocumentTypeList = repositoryDocument.getAllDocumentType();
        List<EntityDocumentProc> entityDocumentProcList = repositoryDocument.getAllDocumentProc();

        model.addAttribute("entityDocument", entityDocument);
        model.addAttribute("entityDocumentTypeList", entityDocumentTypeList);
        model.addAttribute("entityDocumentProcList", entityDocumentProcList);

        return "doc/editBase";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String nextEditDocumentForm(@ModelAttribute("entityDocument") la3v.logic.entities.document.EntityDocument entityDocument,
                                       Model model,
                                       BindingResult result,
                                       Principal principal,
                                       @PathVariable("id") Integer docId) throws Exception {

        entityDocument.setDocId(docId);
        entityDocumentBase = entityDocument;

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        la3v.logic.entities.document.EntityDocument entityDocumentOld = repositoryDocument.findById(docId);

        List<String> authors = new ArrayList<String>();

        EntityDocumentAuthor entityDocumentAuthor;
        EntityDocumentProc entityDocumentProc = repositoryDocument.getDocumentProcById(Integer.parseInt(entityDocument.getDocProcess()));
        EntityDocumentType entityDocumentType = repositoryDocument.getDocumentTypeById(Integer.parseInt(entityDocument.getDocType()));

//        log.info(String.format("=-=-=-=-=-=-=-="));
//        log.info(String.format("Old DocTitle: %s", entityDocumentOld.getDocTitle()));
//        log.info(String.format("New DocTitle: %s", entityDocument.getDocTitle()));
//
//        log.info(String.format("Old DocType: %s", entityDocumentOld.getDocType()));
//        log.info(String.format("New DocType: %s", entityDocumentType.getType_name()));
//
//        log.info(String.format("Old AuthorName: %s", entityDocumentOld.getAuthorName()));
//        log.info(String.format("New AuthorName: %s", entityDocument.getAuthorName()));
//
//        log.info(String.format("Old DocProcess: %s", entityDocumentOld.getDocProcess()));
//        log.info(String.format("New DocProcess: %s", entityDocumentProc.getProcName()));
//        log.info(String.format("=-=-=-=-=-=-=-="));

        // обновляем документ
        repositoryDocument.setUpdDocumentWithoutAttributes(entityDocument);

        if (!Objects.equals(entityDocumentOld.getDocProcess(), entityDocumentProc.getProcName()))
            // обновляем тип процесса
            repositoryDocument.setUpdDocumentProc(entityDocument);

        if (!Objects.equals(entityDocumentOld.getAuthorName(), entityDocument.getAuthorName())) {

            repositoryDocument.deleteFromAuthor(entityDocument.getDocId());

            //region Получаем список авторов из строки
            log.info(String.format("=-=-=-=-=-=-=-="));
            Pattern pattern = Pattern.compile(regexAuthor);
            Matcher matcher = pattern.matcher(entityDocument.getAuthorName());
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

            //region Добавляем авторов и связи
            for (String str : authors) {
                log.info(String.format("Search docAuthor: %s", str));
                entityDocumentAuthor = repositoryDocument.getDocumentAuthorByName(str);

                if (entityDocumentAuthor != null) {
                    log.info(String.format("Get docAuthor: %d , from table", entityDocumentAuthor.getAuthor_id()));
                    repositoryDocument.insertNewDocumentDocAuthor(entityDocument.getDocId(), entityDocumentAuthor.getAuthor_id());
                } else {
                    log.info(String.format("docAuthor: %s - not found", str));
                    repositoryDocument.insertNewDocumentAuthor(str);
                    log.info(String.format("docAuthor: %s - added in table", str));
                    entityDocumentAuthor = repositoryDocument.getDocumentAuthorByName(str);
                    repositoryDocument.insertNewDocumentDocAuthor(entityDocument.getDocId(), entityDocumentAuthor.getAuthor_id());
                    log.info(String.format("Get docAuthor: %d , from table", entityDocumentAuthor.getAuthor_id()));
                }
                log.info(String.format("=-=-=-=-=-=-=-="));
            }
            //endregion
        }

        JsonObject jsonObj = entityDocument.getAttributes();
        if (attributeMap.get(entityDocumentType.getType_name().toLowerCase()) != null) {

            String docType = attributeRusEng.get(entityDocumentType.getType_name().toLowerCase());

            if (docType == null)
                throw new Exception("There is no such attribute in attribute map!");

            log.info(String.format("Get docType: %s", docType));

            return "redirect:{id}/attributes/" + docType;
//            return "redirect:" + entityDocument.getDocId() + "/attributes/" + docType;
        }

        return "redirect:/document/all";
    }


    @RequestMapping(value = "/edit/{id}/attributes/{docType}", method = RequestMethod.GET)
    public String showListAttributes(Model model, @PathVariable("id") Integer docId, @PathVariable("docType") String docType) throws Exception {

        //IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        //la3v.logic.entities.document.EntityDocument entityDocument = repositoryDocument.findById(docId);

        IAttributes attributes = attributeMap.get(docType.toLowerCase());

        if (attributes == null)
            throw new Exception("There is no such attribute in attribute map!");

        model.addAttribute("attributes", attributes);
        model.addAttribute("entityDocumentBase", entityDocumentBase);
        //model.addAttribute("entityDocument", entityDocument);
        model.addAttribute("addressPart", docId);

        log.info(String.format("IN /view/{id}/attributes/" + docType + " GET"));

        return "doc/editViews/"+ docType.toLowerCase();
    }

    private void editDocument(IAttributes attributes, String documentId, Principal principal)
    {
        log.info(String.format("documentId: %s", documentId));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(gson.toJson(attributes)).getAsJsonObject();

        entityDocumentBase.setAttributes(obj);

        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);

        repositoryDocument.setUpdDocumentWithAttributes(entityDocumentBase);

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("err/pageControllerError");

        model.addObject("errMsg", ex.getMessage());
        return model;
    }

    @RequestMapping(value = "/edit/{id}/attributes/article", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ArticleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/commonShedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CommonScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/conferenceIncoming", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceIncomingAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/conferenceThesis", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceThesisAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/courseWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CourseWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/diploma", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DiplomaAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/educationalEdition", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") EducationalEditionAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/lecturerForm", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerFormAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/lecturerPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/monograph", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") MonographAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/patent", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PatentAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/pilpitSessionProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitSessionProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/positionInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PositionInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/progressJournal", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProgressJournalAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/protocolStatements", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProtocolStatementsAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/researchWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/schedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/scientificSeminarProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScientificSeminarProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/specialtiesPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") SpecialtiesPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/studentsList", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") StudentsListAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/traineeship", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TraineeshipAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/tutorial", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TutorialAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }

    @RequestMapping(value = "/edit/{id}/attributes/workingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") WorkingPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        editDocument(attributes, documentId, principal);
        return "redirect:/document/all";
    }
}
