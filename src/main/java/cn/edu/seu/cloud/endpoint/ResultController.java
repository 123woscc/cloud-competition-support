package cn.edu.seu.cloud.endpoint;

import cn.edu.seu.cloud.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hhzhang on 2018/4/21.
 */
@Controller
public class ResultController {
    private static Logger logger = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String showUploadPage(HttpSession session) {
        try {
            if (!session.getAttribute("id").equals("0000"))
                return "login.ftl";
            return "upload.ftl";
        } catch (Exception ex) {
            logger.error("Exception in showUploadPage, ex: " + ex);
            return "login.ftl";
        }
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public int saveReports(MultipartFile uploadFile,
                           HttpSession session,
                           HttpServletResponse response) throws IOException {
        try {
            if (!session.getAttribute("id").equals("0000"))
                response.sendRedirect("login");
            resultService.addResult(uploadFile.getInputStream());
            return 1;
        } catch (Exception ex) {
            logger.error("Exception in saveReports: ex " + ex);
            return 0;
        }
    }
}
