package la3v.logic.controllers;

import la3v.logic.attributes.*;
import la3v.logic.entities.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * Created by Marmonth on 21.04.2017.
 */

@Controller
@RequestMapping("/archive")
public class ControllerArchive {

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/all")
    public String showDocumentList(Model model) {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
        List<EntityDocument> entityArchivedDocumentList = repositoryArchive.getAllArchivedDocumentList();
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
        EntityDocument entityDocumentAttribute = repositoryArchive.findById(id);

        JsonObject jsonObj = entityDocumentAttribute.getAttributes();

        IAttributes attr;
        switch(jsonObj.get("docType").getAsString())
        {
            case "Диплом": attr = DiplomaAttributes.getFromJson(jsonObj); break;
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


}