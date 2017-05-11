package la3v.logic.controllers;

import la3v.logic.entities.EntityDocument;
import la3v.logic.repositories.interfaces.IRepositoryArchive;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Valery E. Malakhov on 01.04.2017.
 */
@Controller
@RequestMapping("/document")
public class ControllerDocument {

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/all")
    public String showDocumentList(Model model) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
//        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
//        List<EntityDocument> entityDocumentList = repositoryDocument.getAllDocumentList();
//        model.addAttribute("entityDocumentList", entityDocumentList);

        return "doc/document";
    }

//    @RequestMapping("/analytics")
//    public String showAnalytics(Model model){
//        IRepositoryArchive repositoryArchive = context.getBean(IRepositoryArchive.class);
//        /*List<EntityProtocolOfDeleteOutput> entityDeleteProtocolList = repositoryArchive.getAllProtocolOfDeleteList();
//        model.addAttribute("entityDeleteProtocolOutputList", entityDeleteProtocolList);*/
//
//        return "doc/analytics";
//    }

//    @RequestMapping("/{id}")
//    public String showInfo(Model model, @PathVariable("id") Integer id){
//        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
//        model.addAttribute("document",  repositoryDocument.findById(id));
//
//        return "info/documentInfo";
//    }
}
