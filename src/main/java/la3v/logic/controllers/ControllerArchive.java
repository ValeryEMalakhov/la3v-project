package la3v.logic.controllers;

import la3v.logic.attributes.*;
import la3v.logic.entities.EntityDocument;
import la3v.logic.entities.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by Marmonth on 21.04.2017.
 */

@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    private ApplicationContext context;
    private la3v.logic.entities.archive.EntityDocument archivedDocument;
    private Map<String,IAttributes> attributeMap = new AttributesMap().getAttributeMap();

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
        List<EntityProtocolOfDeleteOutput> entityDeleteProtocolList = repositoryArchive.getAllProtocolOfDeleteList();
        model.addAttribute("entityDeleteProtocolOutputList", entityDeleteProtocolList);

        return "archive/dprotocols";
    }

    @RequestMapping("/analytics")
    public String showAnalytics(Model model){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        /*List<EntityProtocolOfDeleteOutput> entityDeleteProtocolList = repositoryArchive.getAllProtocolOfDeleteList();
        model.addAttribute("entityDeleteProtocolOutputList", entityDeleteProtocolList);*/

        return "archive/analytics";
    }

    @RequestMapping("all/documentattributes/{id}")
    public String showAttributes(Model model, @PathVariable("id") Integer id){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        la3v.logic.entities.archive.EntityDocument entityDocumentAttribute = repositoryArchive.findById(id);

        JsonObject jsonObj = entityDocumentAttribute.getAttributes();

        IAttributes attributes;
        if (attributeMap.containsKey(jsonObj.get("docType").getAsString())) {
            attributes = attributeMap.get(jsonObj.get("docType").getAsString()).getFromJson(jsonObj);
            model.addAttribute("entityDocumentAttribute", attributes.toListString());
            model.addAttribute("entityDocumentType", attributes.getDocType());
        }

        return "archive/attributes";
    }

    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.GET)
    public String showArchivation(Model model, @PathVariable("id") String pathHash){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        EntityDocument documentToArchive = repositoryArchive.findByHash(pathHash);
        model.addAttribute("documentToArchive", documentToArchive);
        model.addAttribute("addressPart", pathHash);

        log.info(String.format("IN /archivation/{id} GET"));

        return "archive/documentToArchive";
    }

    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.POST)
    public String toArchive(@ModelAttribute("documentToArchive") la3v.logic.entities.archive.EntityDocument documentToArchive, BindingResult bindingResult, Model model) {
        archivedDocument = documentToArchive;

        log.info(String.format("IN /archivation/{id} POST"));
        log.info(String.format("getPath %s", documentToArchive.getPath()));
        String docType = "progressJournal";

        return "redirect:{id}/attributes/" + docType;
    }

    @RequestMapping(value = "/archivation/{id}/attributes/{docType}", method = RequestMethod.GET)
    public String showListAttributes(Model model, @PathVariable("id") String pathHash, @PathVariable("docType") String docType){

        String buffDocType = docType.toLowerCase();

        log.info(String.format("IN showListAttributes GET"));
        log.info(String.format("buffDocType: %s", buffDocType));
        log.info(String.format("attributeMap.containsKey(buffDocType): %b", attributeMap.containsKey(buffDocType)));

        IAttributes attributes;
        //if (attributeMap.containsKey(buffDocType)) {
            attributes = attributeMap.get(buffDocType);

            model.addAttribute("attributes", attributes);
            model.addAttribute("archivedDocument", archivedDocument);
            model.addAttribute("addressPart", pathHash);

            log.info(String.format("IN /archivation/{id}/attributes/" + docType + " GET"));

            return "attributes/" + docType;
        //}
    }

//    @RequestMapping(value = "/archivation/{id}/attributes/{docType}", method = RequestMethod.POST)
//    public String showListAttributes(@ModelAttribute("attributes") IAttributes attributes){
//        archiveDocument(attributes);
//        return "redirect:/archive/all";
//    }

    @RequestMapping(value = "/archivation/{id}/attributes/advert", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") AdvertAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/article", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ArticleAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/commonShedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CommonScheduleAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/conferenceIncoming", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceIncomingAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/conferenceThesis", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ConferenceThesisAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/courseWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") CourseWorkAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/diploma", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") DiplomaAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/educationalEdition", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") EducationalEditionAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/lecturerForm", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerFormAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/lecturerPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") LecturerPlanAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/monograph", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") MonographAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/patent", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PatentAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitSessionProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitSessionProtocolAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitWorkingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingPlanAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/pilpitWorkingReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PilpitWorkingReportAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/positionInstructions", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") PositionInstructionsAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/progressJournal", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProgressJournalAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/protocolStatements", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ProtocolStatementsAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/researchWork", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/researchWorkReport", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ResearchWorkReportAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/schedule", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScheduleAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/scientificSeminarProtocol", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") ScientificSeminarProtocolAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/specialtiesPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") SpecialtiesPlanAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/studentsList", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") StudentsListAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/traineeship", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TraineeshipAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/tutorial", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") TutorialAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/workingPlan", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("attributes") WorkingPlanAttributes attributes){
        archiveDocument(attributes);
        return "redirect:/archive/all";
    }

    private void archiveDocument(IAttributes attributes)
    {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(gson.toJson(attributes)).getAsJsonObject();

        archivedDocument.setAttributes(obj);

        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        repositoryArchive.insertDocument(archivedDocument);
    }

/*
    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.GET)
    public String showArchivation(Model model, @PathVariable("id") String pathHash){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        EntityDocument documentToArchive = repositoryArchive.findByHash(pathHash);
        model.addAttribute("documentToArchive", documentToArchive);
        model.addAttribute("addressPart", pathHash);

        log.info(String.format("IN /archivation/{id} GET"));

        return "archive/documentToArchive";
    }

    @RequestMapping(value = "/archivation/{id}", method = RequestMethod.POST)
    //public String toArchive(@ModelAttribute("diplomaAttributes") DiplomaAttributes diplomaAttributes, BindingResult bindingResult, Model model) {
    public String toArchive(@ModelAttribute("documentToArchive") la3v.logic.entities.archive.EntityDocument documentToArchive, BindingResult bindingResult, Model model) {
        archivedDocument = documentToArchive;

        log.info(String.format("IN /archivation/{id} POST"));
        log.info(String.format("getPath %s", documentToArchive.getPath()));

        return "redirect:{id}/attributes/diploma";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/diploma", method = RequestMethod.GET)
    public String showListAttributes(Model model, @PathVariable("id") String pathHash){
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);

        model.addAttribute("diplomaAttributes", new DiplomaAttributes());
        model.addAttribute("archivedDocument", archivedDocument);
        model.addAttribute("addressPart", pathHash);

        log.info(String.format("IN /archivation/{id}/attributes/diploma GET"));

        return "attributes/diploma";
    }

    @RequestMapping(value = "/archivation/{id}/attributes/diploma", method = RequestMethod.POST)
    public String showListAttributes(@ModelAttribute("diplomaAttributes") DiplomaAttributes diplomaAttributes, BindingResult bindingResult, Model model){

        log.info(String.format("IN /archivation/{id}/attributes/diploma POST"));
        log.info(String.format("getDocType %s", diplomaAttributes.getDocType()));

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(gson.toJson(diplomaAttributes)).getAsJsonObject();

        archivedDocument.setAttributes(obj);
        log.info(String.format("---------------------------------------"));
        log.info(String.format("getName %s", archivedDocument.getName()));
        log.info(String.format("getAuthor %s", archivedDocument.getAuthor()));
        log.info(String.format("getPath %s", archivedDocument.getPath()));
        log.info(String.format("getComments %s", archivedDocument.getComments()));
        log.info(String.format("getArchivePath %s", archivedDocument.getArchivePath()));
        log.info(String.format("getAttributes %s", archivedDocument.getAttributes().toString()));
        log.info(String.format("getArchivingTerm %s", archivedDocument.getArchivingTerm()));
        log.info(String.format("getDateOfCreation %s", archivedDocument.getDateOfCreation()));
        log.info(String.format("getDateOfArchiving %s", archivedDocument.getDateOfArchiving()));
        log.info(String.format("---------------------------------------"));

        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        repositoryArchive.insertDocument(archivedDocument);

        return "redirect:/archive/all";
    }*/
}

//TODO:
//Забрать из .jsp diplomaAttributes DONE
// создать Archive.EntityDocument DONE
// запихнуть его в базу DONE

// удалить из базы Owncloud запись о документе
//filecache
//systemtag_object_mapping
//activity

//Profit!


     /*
       +Тема            | ?
        Автор           | ~ (теги)
        Путь            | +
        Комментарии     | (есть в базе)
        Путь в архиве   | в форме
        Аттрибуты       | +
        Срок архивации  | в форме
        Дата создания   | ~
        Дата архивации  | +
        */


    /*@ModelAttribute
    public IAttributes attributes(String docType) {
    //public IAttributes processAttributes(@ModelAttribute("attributes") IAttributes attributes, String docType){
        IAttributes attributes;
        if(docType.isEmpty())
            return null;
        switch(docType.toLowerCase())
        {
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
        }
        return attributes;
    }*/