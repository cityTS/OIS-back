package cn.edu.gxu.oisback.controller;

import cn.edu.gxu.oisback.domain.Re;
import cn.edu.gxu.oisback.domain.Student;
import cn.edu.gxu.oisback.service.StudentService;
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
        if(studentService.login(request, student)) {
            return new Re(0, null, "登录成功");
        }
        return new Re(1, null, "姓名/学号/考试码错误，请检查信息！");
    }
}
