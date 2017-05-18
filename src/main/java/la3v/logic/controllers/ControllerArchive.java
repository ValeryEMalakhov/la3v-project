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

        IAttributes attr;
        switch(jsonObj.get("docType").getAsString())
        {
            case "Диплом": attr = DiplomaAttributes.getFromJson(jsonObj); break;
            case "Diploma": attr = DiplomaAttributes.getFromJson(jsonObj); break;
            case "Курсовая работа": attr = CourseWorkAttributes.getFromJson(jsonObj); break;
            case "Учебный план специальности": attr = SpecialtiesPlanAttributes.getFromJson(jsonObj); break;
            case "Рабочий учебный план": attr = WorkingPlanAttributes.getFromJson(jsonObj); break;
            case "Список фамилий студентов": attr = StudentsListAttributes.getFromJson(jsonObj); break;
            case "Журнал текущей успеваемости": attr = ProgressJournalAttributes.getFromJson(jsonObj); break;
            case "Тезис конференций": attr = ConferenceThesisAttributes.getFromJson(jsonObj); break;
            case "Статья": attr = ArticleAttributes.getFromJson(jsonObj); break;
            case "Монография": attr = MonographAttributes.getFromJson(jsonObj); break;
            case "Учебное пособие": attr = TutorialAttributes.getFromJson(jsonObj); break;
            case "Учебное издание": attr = EducationalEditionAttributes.getFromJson(jsonObj); break;
            case "Патент": attr = PatentAttributes.getFromJson(jsonObj); break;
            case "Проводимая конференция": attr = ConferenceIncomingAttributes.getFromJson(jsonObj); break;
            case "НИР кафедры": attr = ResearchWorkAttributes.getFromJson(jsonObj); break;
            case "Отчёт НИР": attr = ResearchWorkReportAttributes.getFromJson(jsonObj); break;
            case "Протокол научного семинара": attr = ScientificSeminarProtocolAttributes.getFromJson(jsonObj); break;
            case "Протокол заседания кафедры": attr = PilpitSessionProtocolAttributes.getFromJson(jsonObj); break;
            case "Выписка из протокола": attr = ProtocolStatementsAttributes.getFromJson(jsonObj); break;
            case "Должностные инструкции": attr = PositionInstructionsAttributes.getFromJson(jsonObj); break;
            case "Анкета перподавателя": attr = LecturerFormAttributes.getFromJson(jsonObj); break;
            case "Штатное расписание": attr = CommonScheduleAttributes.getFromJson(jsonObj); break;
            case "План работы кафедры": attr = PilpitWorkingPlanAttributes.getFromJson(jsonObj); break;
            case "Отчёт работы кафедры": attr = PilpitWorkingReportAttributes.getFromJson(jsonObj); break;
            case "Расписание": attr = ScheduleAttributes.getFromJson(jsonObj); break;
            case "Индивидуальный план преподавателя": attr = LecturerPlanAttributes.getFromJson(jsonObj); break;
            case "Стажировка": attr = TraineeshipAttributes.getFromJson(jsonObj); break;
            case "Объявление": attr = AdvertAttributes.getFromJson(jsonObj); break;
            default: model.addAttribute("entityDocumentType", jsonObj.get("docType").toString()); return "archive/attributes";
        }

        model.addAttribute("entityDocumentAttribute", attr.toListString());
        model.addAttribute("entityDocumentType", attr.getDocType());

        return "archive/attributes";
    }


    //TODO:
    //Забрать из .jsp diplomaAttributes DONE
    // создать Archive.EntityDocument DONE
    // запихнуть его в базу DONE
    // удалить из базы Owncloud запись о документе

    //filecache
    //systemtag_object_mapping
    //activity
    //
    //
    //
    //
    //
    //
    //
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
    }
}