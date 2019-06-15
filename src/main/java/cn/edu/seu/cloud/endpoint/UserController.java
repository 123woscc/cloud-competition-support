package cn.edu.seu.cloud.endpoint;

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

/**
 * Created by Administrator on 14-6-30.
 */
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login.ftl";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public int login(HttpSession session,
                     @RequestParam String username,
                     @RequestParam String password) throws IOException {
        try {
            String sessionName = (String) session.getAttribute("username");
            if (sessionName != null) {
                if (session.getAttribute("id").equals("0000"))
                    return 3;
                return 1;
            }
            if (userService.validateUser(username, password) == 0)
                return 0;//无用户
            else if (userService.validateUser(username, password) == 2)
                return 2;//密码错误
            else {
                session.setAttribute("username", username);
                String sessionId = userService.selectUser(username).getId();
                session.setAttribute("id", sessionId);
                if (sessionId.equals("0000"))
                    return 3;//管理员
                return 1;//登录成功
            }
        } catch (Exception ex) {
            logger.error("Exception in login, ex: ", ex);
        }
        return 0;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        try {
            session.removeAttribute("username");
            session.removeAttribute("id");
            if (session.getAttribute("money") != null)
                session.removeAttribute("money");
        } catch (Exception ex) {
            logger.error("Exception in logout: " + ex);
        }
        return "login.ftl";
    }

}



