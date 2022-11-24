package cn.edu.gxu.oisback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 监考日志类
 * @author cityTS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Logs {
    // 日志编号
    private Integer id;
    // 被监考者姓名
    private String name;
    // 被监考者客户端IP
    private String ip;
    // 日志级别
    private String level;
    // 日志内容
    private String content;
    // 日志创建时间
    private Long createTime;
}
