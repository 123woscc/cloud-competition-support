package cn.edu.seu.cloud.dao;

import cn.edu.seu.cloud.domain.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * Created by hhzhang on 2018/4/21.
 */
public interface ResultDao {
    public void insertResult(@Param(value = "id") String id,
                             @Param(value = "workname") String name,
                             @Param(value = "school") String school,
                             @Param(value = "teacher") String teacher,
                             @Param(value = "leader") String leader,
                             @Param(value = "labman1") String labman1,
                             @Param(value = "labman2") String labman2,
                             @Param(value = "labman3") String labman3,
                             @Param(value = "category") String category,
                             @Param(value = "grade") String grade,
                             @Param(value = "award") String award,
                             @Param(value = "money") String money);

    public Result selectResultByUid(@Param(value = "id") String id);

    public Result selectResultByTeamName(@Param(value = "team") String team);

    public List<Result> selectResultsByTeamId(@Param(value = "teamId") String teamId);
}
