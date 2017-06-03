package la3v.logic.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valery E. Malakhov on 18.05.2017.
 */

@Controller
public class ControllerSchedule {

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/schedule")
    public String showSchedule(Model model) {
        return "schedule";
    }

    @RequestMapping(value = "/json/getEvent", method = RequestMethod.GET)
    public
    @ResponseBody
    String getVacation(HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", 1);
        map.put("title", "event 1");
        map.put("start", "2017-5-19");
        map.put("end", "2017-5-22");

        map.put("id", 2);
        map.put("title", "event 2");
        map.put("start", "2017-05-07T02:00:00");
        map.put("end", "2017-05-07T07:00:00");

        // Convert to JSON string.
        String json = new Gson().toJson(map);

        // Write JSON string.
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return json;
    }
}