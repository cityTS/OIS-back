package cn.edu.gxu.oisback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考生类
 * @author cityTS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    // 考生编号
    private Integer id;
    // 考生名称
    private String name;
    // 考生学号
    private String studentNumber;
    // 考试编号
    private Integer examinationId;
    // 考生上次登录IP
    private String lastLoginIp;
}
