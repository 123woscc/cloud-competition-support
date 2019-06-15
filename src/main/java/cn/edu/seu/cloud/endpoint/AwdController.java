package cn.edu.seu.cloud.endpoint;

import cn.edu.seu.cloud.domain.Awd;
import cn.edu.seu.cloud.domain.MailInfo;
import cn.edu.seu.cloud.domain.Result;
import cn.edu.seu.cloud.domain.User;
import cn.edu.seu.cloud.service.AwdService;
import cn.edu.seu.cloud.service.MailInfoService;
import cn.edu.seu.cloud.service.ResultService;
import cn.edu.seu.cloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 14-6-30.
 */
@Controller
public class AwdController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MailInfoService mailInfoService;

    @Autowired
    private AwdService awdService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "award", method = RequestMethod.GET)
    public String showAwardPage(HttpSession session, ModelMap modelMap) {
        try {
            if (session.getAttribute("username") == null)
                return "login.ftl";
            String uid = (String) session.getAttribute("id");
            User user = userService.getUserById(uid);
            List<Result> result = resultService.getResultByTeamId(user.getTeamId());
            if (result.isEmpty()) {
                modelMap.addAttribute("result", null);
                modelMap.addAttribute("results", null);
                return "award.ftl";
            }
            modelMap.addAttribute("results", result);
            modelMap.addAttribute("result", result.get(0));
            return "award.ftl";
        } catch (Exception ex) {
            logger.error("Exception in showAwardPage: " + ex);
            return "login.ftl";
        }
    }

    @RequestMapping(value = "awardRegister", method = RequestMethod.GET)
    public String showAwardRegister(HttpSession session,
                                    ModelMap map) {
        try {
            if (session.getAttribute("username") == null)
                return "login.ftl";
            String uid = (String) session.getAttribute("id");
            Awd awd = awdService.getAwdById(uid);
            map.addAttribute("awd", awd);
            return "awardRegister.ftl";
        } catch (Exception ex) {
            logger.error("Exception in showAwardRegister: " + ex);
            return "login.ftl";
        }
    }

    @RequestMapping(value = "awardRegister", method = RequestMethod.POST)
    @ResponseBody
    public int awardRegister(HttpSession session,
                             @RequestParam(value = "username") String username,
                             @RequestParam(value = "idnum") String idnum,
                             @RequestParam(value = "accountName") String accountName,
                             @RequestParam(value = "accountNumber") String accountNumber,
                             @RequestParam(value = "coupletNumber") String coupletNumber,
                             @RequestParam(value = "telephone") String telephone) throws IOException {
        try {
            if (session.getAttribute("username") == null)
                return 0;
            awdService.addAwdInfo((String) session.getAttribute("id"), username, idnum, accountName, accountNumber, coupletNumber, telephone);
            return 1;
        } catch (Exception ex) {
            logger.error("Exception in awdRegister: " + ex);
            return 0;
        }
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String showDownloadPage(ModelMap modelMap, HttpSession session) {
        try {
            if (!session.getAttribute("id").equals("0000"))
                return "login.ftl";
            List<Awd> awdList = awdService.getAllAwd();
            List<MailInfo> mailInfoList = mailInfoService.getAllMailInfo();
            modelMap.addAttribute("awdList", awdList);
            modelMap.addAttribute("mails", mailInfoList);
            return "download.ftl";
        } catch (Exception ex) {
            logger.error("Exception in showDownloadPage, ex: " + ex);
            return "login.ftl";
        }
    }

    @RequestMapping(value = "downloadAward/**", method = RequestMethod.GET)
    @ResponseBody
    public void downloadAwardExcel(HttpSession session,
                                   HttpServletResponse response) throws IOException {
        try {
            if (!session.getAttribute("id").equals("0000"))
                response.sendRedirect("login");
            response.setContentType("application/x-download");
            OutputStream outputStream = response.getOutputStream();
            awdService.getExcelFile().write(outputStream);
            outputStream.close();
        } catch (Exception ex) {
            logger.error("Exception in downloadAwardExcel, ex: " + ex);
        }
    }
}


