package cn.edu.seu.cloud.dao;

import cn.edu.seu.cloud.domain.Awd;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 14-6-30.
 */
public interface AwdDao {
    public Boolean addAwdInfo(@Param(value = "id") String id,
                              @Param(value = "username") String username,
                              @Param(value = "idnum") String idnum,
                              @Param(value = "accountName") String accountName,
                              @Param(value = "accountNumber") String accountNumber,
                              @Param(value = "coupletNumber") String coupletNumber,
                              @Param(value = "telephone") String telephone);

    public List<Awd> selectAllAwd();
    public Awd selectAwdById(@Param(value = "id") String id);
}