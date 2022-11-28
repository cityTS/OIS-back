package cn.edu.gxu.oisback.controller;

import cn.edu.gxu.oisback.domain.Re;
import cn.edu.gxu.oisback.domain.Student;
import cn.edu.gxu.oisback.service.StudentService;
import cn.edu.gxu.oisback.utils.FirewallUtil;
import cn.edu.gxu.oisback.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/login")
@CrossOrigin
public class LoginController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public Re login(HttpServletRequest request, @RequestBody Student student) {
        Integer res = studentService.login(request, student);
        if(res == 0) {
            return new Re(0, null, "登录成功");
        } else if(res == 1) {
            return new Re(1, null, "该账号已在其它客户端登录");
        } else if(res == 2) {
            return new Re(1, null, "您的考试已结束");
        }
        return new Re(1, null, "姓名/学号/考试码错误，请检查信息！");
    }

    @GetMapping
    public Re unLogin(HttpServletRequest request) {
        FirewallUtil.unRegister(IpUtil.loadRemoteUserIP(request));
        return new Re(0, null, "退出成功");
    }

    @PutMapping
    public Re unRegister(HttpServletRequest request) {
        FirewallUtil.unRegister(IpUtil.loadRemoteUserIP(request));
        return new Re(0, null, "注销成功");
    }
}
