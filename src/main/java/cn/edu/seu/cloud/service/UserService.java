package cn.edu.seu.cloud.service;

import cn.edu.seu.cloud.dao.UserDao;
import cn.edu.seu.cloud.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 14-6-30.
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;

    public int validateUser(String username, String password) {
        if (userDao.selectUser(username) == null)
            return 0;//没有注册
        else if (userDao.selectUser(username).getPassword().equals(password))
            return 1;
        else return 2;//密码不对
    }

    public User selectUser(String username){
        return userDao.selectUser(username);
    }

    public User getUserById(String id) {
        return userDao.selectUserById(id);
    }
}

