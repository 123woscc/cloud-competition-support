package cn.edu.seu.cloud.endpoint;

import cn.edu.seu.cloud.domain.MailInfo;
import cn.edu.seu.cloud.service.MailInfoService;
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

/**
 * Created by hhzhang on 2018/4/21.
 */
@Controller
public class MailInfoController {
    private static Logger logger = LoggerFactory.getLogger(MailInfoController.class);

    @Autowired
    private MailInfoService mailInfoService;

    @RequestMapping(value = "addressRegister", method = RequestMethod.GET)
    public String showAddressRegister(HttpSession session, ModelMap modelMap) {
        try {
            if (session.getAttribute("username") == null)
                return "login.ftl";
            String uid = (String) session.getAttribute("id");
            MailInfo mailInfo = mailInfoService.getMailInfoByUid(uid);
            modelMap.addAttribute("mail", mailInfo);
            return "addressRegister.ftl";
        } catch (Exception ex) {
            logger.error("Exception in showAddressRegister: " + ex);
            return "login.ftl";
        }
    }

    @RequestMapping(value = "addressRegister", method = RequestMethod.POST)
    @ResponseBody
    public int saveMailInfo(@RequestParam String name,
                            @RequestParam String telephone,
                            @RequestParam String backlog,
                            @RequestParam String address,
                            HttpSession session) {
        try {
            if (session.getAttribute("username") == null)
                return 0;
            String uid = (String) session.getAttribute("id");
            mailInfoService.addMailInfo(uid, name, telephone, backlog, address);
            return 1;
        } catch (Exception ex) {
            logger.error("Exception in saveMailInfo: " + ex);
            return 0;
        }
    }

    @RequestMapping(value = "downloadMail/**", method = RequestMethod.GET)
    @ResponseBody
    public void downloadMailInfoExcel(HttpSession session,
                                   HttpServletResponse response) throws IOException {
        try {
            if (!session.getAttribute("id").equals("0000"))
                response.sendRedirect("login");
            response.setContentType("application/x-download");
            OutputStream outputStream = response.getOutputStream();
            mailInfoService.exportToExcel().write(outputStream);
            outputStream.close();
        } catch (Exception ex) {
            logger.error("Exception in downloadMailInfoExcel: " + ex);
        }
    }
}
