package cn.edu.gxu.oisback.service;

import cn.edu.gxu.oisback.dao.LogsDao;
import cn.edu.gxu.oisback.dao.StudentDao;
import cn.edu.gxu.oisback.domain.Logs;
import cn.edu.gxu.oisback.domain.Student;
import cn.edu.gxu.oisback.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    LogsDao logsDao;
    public Boolean login(HttpServletRequest request, Student student) {
        List<Student> s = studentDao.selectStudent(student);
        if(s.isEmpty()) {
            return false;
        }
        String oldIp = s.get(0).getLastLoginIp(), newIp = IpUtil.loadRemoteUserIP(request);
        if(s.get(0).getLastLoginIp() != null && !oldIp.equals(newIp)) {
            Logs logs = new Logs(student.getName(), newIp, "异常", student.getStudentNumber() + "-" + student.getName() + ": 重复登录(原IP:" + oldIp + ", 现IP:" + newIp + ")", System.currentTimeMillis());
            logsDao.insertLog(logs);
            student.setLastLoginIp(newIp);
            studentDao.updateStudentLastIp(student);
        }
        return true;
    }
}
