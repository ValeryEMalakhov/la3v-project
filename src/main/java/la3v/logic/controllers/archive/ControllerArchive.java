package la3v.logic.controllers.archive;

import la3v.logic.attributes.archive.AttributesMap;
import la3v.logic.attributes.archive.IAttributes;
import la3v.logic.entities.archive.*;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
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
        List<EntityProtocolOfDelete> entityDeleteProtocolList = repositoryArchive.getAllProtocolOfDeleteList();
        model.addAttribute("entityDeleteProtocolList", entityDeleteProtocolList);

        return "archive/dprotocols";
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
}