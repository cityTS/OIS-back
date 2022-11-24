package cn.edu.gxu.oisback.dao;

import cn.edu.gxu.oisback.domain.Logs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogsDao {
    /**
     * 新增日志
     * @param logs 日志
     * @return
     */
    Integer insertLog(Logs logs);

    /**
     * 查询日志
     * @param offset 偏移量
     * @param count 行数
     * @param level 日志等级
     * @return
     */
    List<Logs> selectLogsPage(Integer offset, Integer count, String level);

    /**
     * 查询level等级的日志数量
     * @param level 日志等级
     * @return
     */
    Integer selectLogsCount(String level);
}
