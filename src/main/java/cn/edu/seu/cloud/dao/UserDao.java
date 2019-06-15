package cn.edu.seu.cloud.dao;

import cn.edu.seu.cloud.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 14-6-30.
 */
public interface UserDao {
    public List<User> selectAllUsers();
    public User selectUser(@Param(value = "username") String username);
    public User selectUserById(@Param(value = "id") String id);
    public List<String> getAllNames();
}

