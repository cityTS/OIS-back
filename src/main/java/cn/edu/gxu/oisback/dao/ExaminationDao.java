package cn.edu.gxu.oisback.dao;

import cn.edu.gxu.oisback.domain.Examination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExaminationDao {
    /**
     * 查询比赛
     * @param examination 比赛信息
     * @return
     */
    List<Examination> selectExamination(Examination examination);
}
