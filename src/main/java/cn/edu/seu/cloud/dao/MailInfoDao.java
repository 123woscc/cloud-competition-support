package cn.edu.seu.cloud.dao;

import cn.edu.seu.cloud.domain.MailInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hhzhang on 2018/4/21.
 */
public interface MailInfoDao {
    public void insertMailInfo(@Param(value = "uid") String uid,
                               @Param(value = "name") String name,
                               @Param(value = "telephone") String telephone,
                               @Param(value = "backlog") String backlog,
                               @Param(value = "address") String address);

    public MailInfo selectMailInfoByUid(@Param(value = "uid") String uid);

    public List<MailInfo> selectAllMailInfo();
}
