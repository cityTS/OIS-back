package cn.edu.gxu.oisback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员类
 * @author cityTS
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    // 管理员编号
    private Integer id;
    // 管理员姓名
    private String name;
    // 管理员密码
    private String password;
    // 上次登录IP
    private String lastIp;
}
