package la3v.logic.controllers;

import com.sun.deploy.xml.XMLParser;
import la3v.logic.files.entities.FileBucket;
import la3v.logic.files.validators.FileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valery E. Malakhov on 27.05.2017.
 */
@Controller
@RequestMapping("/new")
public class ControllerUpload {

    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(ControllerUpload.class);

    private final String rootPath = System.getProperty("catalina.home");

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {

        log.info(String.format("Logger in GET file ControllerUpload"));

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        return "doc/fileUpload";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    //public String singleFileUpload(@Valid FileBucket fileBucket,
    public String singleFileUpload(@ModelAttribute la3v.logic.files.entities.FileBucket fileBucket,
                                   BindingResult result,
                                   ModelMap model)
            throws IOException, ParserConfigurationException, SAXException {

        if (result.hasErrors()) {
            return "doc/fileUpload";
        } else {

        log.info(String.format("Logger in POST file ControllerUpload"));

            MultipartFile file = fileBucket.getFile();

        log.info(String.format("Get fileBucket in POST"));

            File serverFile = new File(rootPath + File.separator + "tmpFiles" + File.separator + file.getOriginalFilename());

        log.info(String.format("We have file: %s", file.getOriginalFilename()));

            FileOutputStream fos = new FileOutputStream(serverFile);
            fos.write(file.getBytes());
            fos.close();

        log.info(String.format("Get FileOutputStream fos in POST"));

//            List<String> sqlQueriesTextList = new XMLParser().parseXMLFile(serverFile);
//            List<List<String>> parsedFunctionQueryList = new ArrayList<List<String>>();
//            List<Implication<QueryAttribute>> implicationList = new ArrayList<Implication<QueryAttribute>>();
//
//            for (String queryText : sqlQueriesTextList) {
//                parsedFunctionQueryList.add(new SQLFunctionParser(queryText).parseSQLFunction());
//            }
//
//            for (List<String> list : parsedFunctionQueryList) {
//                implicationList.add(new SQLParser(list).getImplication());
//            }
//
//            AcyclicDownTopAlgorithmService acyclicDownTopAlgorithmService = new AcyclicDownTopAlgorithmService(implicationList);
//
//            model.addAttribute("vertexList", acyclicDownTopAlgorithmService.getVertexList());
//            model.addAttribute("vertexConnectionList", acyclicDownTopAlgorithmService.getVertexConnectionList());
//            model.addAttribute("definingAttributes", acyclicDownTopAlgorithmService.getDefiningAttributes());
            return "doc/edit";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("err/page_error");

        model.addObject("errMsg", ex.getMessage());
        return model;
    }


}
