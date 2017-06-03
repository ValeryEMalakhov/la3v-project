package la3v.logic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marmonth on 11.05.2017.
 */

@Controller
@RequestMapping("/profile")
public class ControllerProfile {

        private ApplicationContext context;

        @Autowired
        public void context(ApplicationContext context) {
            this.context = context;
        }

//        @RequestMapping("/{id}")
//        public String showDocumentList(Model model, @PathVariable("id") Integer id) {
//            IRepositoryUser repositoryUser = context.getBean(IRepositoryUser.class);
//            EntityUser user = repositoryUser.findById(id);
//            model.addAttribute("user", user);
//            return "profile";
//        }
        /*@RequestMapping("/")
        public String showDocumentList(Model model/*, @PathVariable("id") Integer id) {
            IRepositoryUser repositoryUser = context.getBean(IRepositoryUser.class);
            EntityUser user = repositoryUser.findById(1);
            model.addAttribute("position", "Должность: " + user.getPositionId().toString());
            model.addAttribute("firstName", "Имя: " + user.getFirstName());
            model.addAttribute("patronymicName", "Отчество: " + user.getPatronymicName());
            model.addAttribute("lastName", "Фамилия: " + user.getLastName());
            return "profile";
        }*/

}
