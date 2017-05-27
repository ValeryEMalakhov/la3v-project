package la3v.logic.controllers;

import la3v.logic.entities.document.EntityDocument;
import la3v.logic.repositories.interfaces.IRepositoryDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
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
    public String showDocumentList(Model model, Principal principal) {
        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
        List<EntityDocument> entityDocumentList = repositoryDocument.getAllDocumentList(principal.getName());
        model.addAttribute("entityDocumentList", entityDocumentList);

        return "doc/document";
    }

    @RequestMapping("/edit")
    public String showEditDocumentForm(Model model) {
        return "doc/edit";
    }

//    @RequestMapping("/{id}")
//    public String showInfo(Model model, @PathVariable("id") String id) {
//        IRepositoryDocument repositoryDocument = context.getBean(IRepositoryDocument.class);
//        model.addAttribute("document", repositoryDocument.findByHash(id));
//
//        return "doc/analytics";
//    }
}
