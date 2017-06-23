package la3v.logic.controllers.archive;

import la3v.logic.attributes.archive.AttributesMap;
import la3v.logic.attributes.archive.AttributesTranslate;
import la3v.logic.attributes.archive.IAttributes;
import la3v.logic.attributes.archive.ProcessTypeTranslate;
import la3v.logic.entities.archive.EntityDocument;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Marmonth on 03.06.2017.
 */
@Controller
@RequestMapping("/archive/analytics")
public class ControllerAnalytics {

    private ApplicationContext context;

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerArchive.class);

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/")
    public String showAnalytics(Model model){
        return "archive/analytics/analyticsMain";
    }


    @RequestMapping(value = "/doctype", method = RequestMethod.GET)
    public String showAnalyticsDocType(Model model){
        String option = "";
        model.addAttribute("optionString", option);
        return "archive/analytics/docType";
    }

    @RequestMapping(value = "/doctype", method = RequestMethod.POST)
    public String showAnalyticsDocTypePOST(Model model, @ModelAttribute("optionString") String option) throws Exception {
        log.info(String.format("option: " + option));
        return "redirect:/archive/analytics/doctype/" + option;
    }

    private String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    @RequestMapping(value = "/doctype/{docType}", method = RequestMethod.GET)
    public String showDocTypeExtended(Model model, @PathVariable("docType") String docType) throws Exception {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);

        String docTypeRus = new AttributesTranslate().getAttributesEngRus().get(docType.toLowerCase());

        List<EntityDocument> entityList = repositoryArchive.getExtendedDocType(firstUpperCase(docTypeRus));

        IAttributes attr = new AttributesMap().getAttributeMap().get(docType.toLowerCase());

        HashMap<EntityDocument, IAttributes> mapDocument = new HashMap<>();

        for (EntityDocument entity:entityList) {
            mapDocument.put(entity,attr.getFromJson(entity.getAttributes()));
        }

        model.addAttribute("mapDocument", mapDocument);

        return "archive/analytics/extended/" + docType + "Extended";
    }

    @RequestMapping(value = "/proctype", method = RequestMethod.GET)
    public String showAnalyticsProcType(Model model){
        String option = "";
        model.addAttribute("optionString", option);
        return "archive/analytics/processType";
    }

    @RequestMapping(value = "/proctype", method = RequestMethod.POST)
    public String showAnalyticsProcTypePOST(Model model, @ModelAttribute("optionString") String option) throws Exception {
        log.info(String.format("option: " + option));
        return "redirect:/archive/analytics/proctype/" + option;
    }

    @RequestMapping(value = "/proctype/{proctype}", method = RequestMethod.GET)
    public String showProcTypeExtended(Model model, @PathVariable("proctype") String procType) throws Exception {
        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);

        String procTypeRus = new ProcessTypeTranslate().getProcessEngRus().get(procType.toLowerCase());

        List<EntityDocument> entityList = repositoryArchive.getExtendedProcess(firstUpperCase(procTypeRus));

        model.addAttribute("documentList", entityList);
        model.addAttribute("processType", procTypeRus);

        return "archive/analytics/processResult";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("err/pageControllerError");

        model.addObject("errMsg", ex.getMessage());
        return model;
    }
}
