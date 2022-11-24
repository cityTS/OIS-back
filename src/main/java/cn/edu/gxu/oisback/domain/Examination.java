package cn.edu.gxu.oisback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 考试类
 * @author cityTS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examination {
    // 比赛编号
    private Integer id;
    // 比赛名称
    private String name;
    // 比赛开始时间
    private Long beginTime;
    // 比赛结束时间
    private Long endTime;
    // 比赛创建时间
    private Long createTime;
    // 比赛删除时间
    private Long deleteTime;
}
