package la3v.logic.controllers;

import la3v.logic.attributes.archive.*;
import la3v.logic.entities.document.EntityDocument;
import la3v.logic.entities.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import com.google.gson.*;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Marmonth on 21.04.2017.
 */

@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    private ApplicationContext context;
    private la3v.logic.entities.archive.EntityDocument documentToArchive;
    private EntityDocument documentFromSystem;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private Map<String,IAttributes> attributeMap = new AttributesMap().getAttributeMap();
    private Map<String,String> attributeRusEng = new AttributesRusEng().getAttributesRusEng();

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerArchive.class);

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/all")
    public String showDocumentList(Model model) {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        List<la3v.logic.entities.archive.EntityDocument> entityArchivedDocumentList = repositoryArchive.getAllArchivedDocumentList();
        model.addAttribute("entityArchivedDocumentList", entityArchivedDocumentList);

        return "archive/archive";
    }

    @RequestMapping("/protocols")
    public String showProtocols(Model model){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        List<EntityProtocolOutput> entityProtocolList = repositoryArchive.getAllProtocolList();
        model.addAttribute("entityProtocolOutputList", entityProtocolList);

        return "archive/protocols";
    }

    @RequestMapping("/dprotocols")
    public String showDeleteProtocols(Model model){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        List<EntityProtocolOfDelete> entityDeleteProtocolList = repositoryArchive.getAllProtocolOfDeleteList();
        model.addAttribute("entityDeleteProtocolList", entityDeleteProtocolList);

        return "archive/dprotocols";
    }

    @RequestMapping("/analytics")
    public String showAnalytics(Model model){
        return "archive/analytics/analyticsMain";
    }

    @RequestMapping(value = "/analytics/docType", method = RequestMethod.POST)
    public String showAnalyticsDocType(Model model){

        return "archive/analytics/analyticsMain";
    }

    @RequestMapping(value = "/all/delete/{id}", method = RequestMethod.GET)
    public String showDeleteConfirm(Model model, @PathVariable("id") Integer id) throws Exception {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        la3v.logic.entities.archive.EntityDocument document = repositoryArchive.findById(id);

        if(document == null)
            throw new Exception("Database error! There is no document with such ID!");

        model.addAttribute("documentId", id);
        model.addAttribute("documentName", document.getName());

        return "archive/deleteConfirm";
    }


    @RequestMapping(value = "/all/delete/{id}", method = RequestMethod.POST)
    public String showDelete(Model model, @PathVariable("id") Integer id, Principal principal) throws Exception {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);

        la3v.logic.entities.archive.EntityDocument document = repositoryArchive.findById(id);

        if(document == null)
            throw new Exception("Database error! There is no document with such ID!");

        repositoryArchive.deleteDocumentProtocol(id);
        repositoryArchive.deleteArchivedDocument(id);

        EntityProtocolOfDelete deleteProtocol = new EntityProtocolOfDelete(
                principal.getName(),
                "Протокол удаления N",
                "Удаление документа " + document.getName() + " пользователем " + principal.getName() + " в " + dateFormat.format(new Date()) + " " + timeFormat.format(new Date()),
                document.getName(),
                document.getAuthor(),
                document.getArchivePath(),
                document.getComments(),
                dateFormat.format(new Date()),
                document.getDateOfArchiving(),
                timeFormat.format(new Date()),
                document.getArchivingTerm()
        );

        repositoryArchive.insertProtocolOfDelete(deleteProtocol);
        return "redirect:/archive/all";
    }

    @RequestMapping("all/documentattributes/{id}")
    public String showAttributes(Model model, @PathVariable("id") Integer id) throws Exception{
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        la3v.logic.entities.archive.EntityDocument entityDocumentAttribute = repositoryArchive.findById(id);

        JsonObject jsonObj = entityDocumentAttribute.getAttributes();
        String buffDocType = jsonObj.get("docType").getAsString().toLowerCase();
        log.info(String.format("buffDocType " + buffDocType));
        log.info(String.format("attributeMap.containsKey(buffDocType) " + attributeMap.containsKey(buffDocType)));

        IAttributes attributes = attributeMap.get(buffDocType).getFromJson(jsonObj);
        log.info(String.format("attributes.toListString() " + attributes.toListString()));
        model.addAttribute("entityDocumentAttribute", attributes.toListString());
        model.addAttribute("entityDocumentType", attributes.getDocType());

        return "archive/attributes";
    }

    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.GET)
    public String showArchivation(Model model, @PathVariable("id") Integer docId) throws Exception {
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        documentFromSystem = repositoryDocument.findById(docId);

        if(documentFromSystem == null)
            throw new Exception("Database error! There is no document with such ID!");

        model.addAttribute("documentToArchive", documentFromSystem);
        model.addAttribute("addressPart", docId);
        model.addAttribute("date", dateFormat.format(new Date()));

        return "archive/documentToArchive";
    }

    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.POST)
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

    @RequestMapping(value = "/archivation/{id}/attributes/{docType}", method = RequestMethod.GET)
    public String showListAttributes(Model model, @PathVariable("id") Integer documentId, @PathVariable("docType") String docType) throws Exception {

        IAttributes attributes = attributeMap.get(docType.toLowerCase());

        if (attributes == null)
            throw new Exception("There is no such attribute in attribute map!");

        model.addAttribute("attributes", attributes);
        model.addAttribute("documentToArchive", documentToArchive);
        model.addAttribute("documentFromSystem", documentFromSystem);
        model.addAttribute("addressPart", documentId);

        log.info(String.format("IN /archivation/{id}/attributes/" + docType + " GET"));

        return "archive/attributes/" + docType.toLowerCase();
    }


    private void archiveDocument(IAttributes attributes, String documentId, Principal principal)
    {
        log.info(String.format("documentId: %s", documentId));
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(gson.toJson(attributes)).getAsJsonObject();

        documentToArchive.setAttributes(obj);

        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);

        repositoryArchive.insertDocument(documentToArchive);

        EntityProtocol entityProtocol = new EntityProtocol(
                principal.getName(),
                "Протокол N",
                "Добавление в архив",
                "Добавление в архив документа " + documentToArchive.getName() + " пользователем " + principal.getName() + " в " + dateFormat.format(new Date()) + " " + timeFormat.format(new Date()),
                dateFormat.format(new Date()),
                timeFormat.format(new Date()),
                repositoryArchive.getDocument(documentToArchive).getId()
        );

        repositoryArchive.insertProtocol(entityProtocol);

       /* repositoryDocument.deleteFromCoauthor(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));
        repositoryDocument.deleteFromProcess(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));
        repositoryDocument.deleteFromDocument(Integer.parseInt(documentId.substring(1,documentId.length() - 1)));*/
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("err/pageControllerError");

        model.addObject("errMsg", ex.getMessage());
        return model;
    }

//    @RequestMapping(value = "/archivation/{id}/attributes/{docType}", method = RequestMethod.POST)
//    public String showListAttributes(@ModelAttribute("attributes") IAttributes attributes, @PathVariable("id") Integer documentId, Principal principal){
//        archiveDocument(attributes, documentId, principal);
//        return "redirect:/archive/all";
//    }

    @RequestMapping(value = "/archivation/{id}/attributes/advert", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") AdvertAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/article", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ArticleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/commonShedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CommonScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/conferenceIncoming", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceIncomingAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/conferenceThesis", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceThesisAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/courseWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CourseWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/diploma", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DiplomaAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/educationalEdition", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") EducationalEditionAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/lecturerForm", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerFormAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/lecturerPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/monograph", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") MonographAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/patent", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PatentAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitSessionProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitSessionProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitWorkingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitWorkingReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingReportAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/positionInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PositionInstructionsAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/progressJournal", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProgressJournalAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/protocolStatements", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProtocolStatementsAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/researchWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/researchWorkReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkReportAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/schedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScheduleAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/scientificSeminarProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScientificSeminarProtocolAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/specialtiesPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") SpecialtiesPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/studentsList", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") StudentsListAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/traineeship", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TraineeshipAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/tutorial", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TutorialAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/workingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") WorkingPlanAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/undefined", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") UndefinedAttributes attributes, @PathVariable("id") String documentId, Principal principal){
        archiveDocument(attributes, documentId, principal);
        return "redirect:/archive/all";
    }
}
/*
            case "диплом": attributes = new DiplomaAttributes(); break;
            case "курсовая работа": attributes = new CourseWorkAttributes(); break;
            case "учебный план специальности": attributes = new SpecialtiesPlanAttributes(); break;
            case "рабочий учебный план": attributes = new WorkingPlanAttributes(); break;
            case "список фамилий студентов": attributes = new StudentsListAttributes(); break;
            case "журнал текущей успеваемости": attributes = new ProgressJournalAttributes(); break;
            case "тезис конференций": attributes = new ConferenceThesisAttributes(); break;
            case "статья": attributes = new ArticleAttributes(); break;
            case "монография": attributes = new MonographAttributes(); break;
            case "учебное пособие": attributes = new TutorialAttributes(); break;
            case "учебное издание": attributes = new EducationalEditionAttributes(); break;
            case "патент": attributes = new PatentAttributes(); break;
            case "проводимая конференция": attributes = new ConferenceIncomingAttributes(); break;
            case "нир кафедры": attributes = new ResearchWorkAttributes(); break;
            case "отчёт нир": attributes = new ResearchWorkReportAttributes(); break;
            case "протокол научного семинара": attributes = new ScientificSeminarProtocolAttributes(); break;
            case "протокол заседания кафедры": attributes = new PilpitSessionProtocolAttributes(); break;
            case "выписка из протокола": attributes = new ProtocolStatementsAttributes(); break;
            case "должностные инструкции": attributes = new PositionInstructionsAttributes(); break;
            case "анкета перподавателя": attributes = new LecturerFormAttributes(); break;
            case "штатное расписание": attributes = new CommonScheduleAttributes(); break;
            case "план работы кафедры": attributes = new PilpitWorkingPlanAttributes(); break;
            case "отчёт работы кафедры": attributes = new PilpitWorkingReportAttributes(); break;
            case "расписание": attributes = new ScheduleAttributes(); break;
            case "индивидуальный план преподавателя": attributes = new LecturerPlanAttributes(); break;
            case "стажировка": attributes = new TraineeshipAttributes(); break;
            case "объявление": attributes = new AdvertAttributes(); break;
            default: return null;
    }*/