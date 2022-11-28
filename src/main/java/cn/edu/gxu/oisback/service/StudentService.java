package cn.edu.gxu.oisback.service;

import cn.edu.gxu.oisback.dao.ExaminationDao;
import cn.edu.gxu.oisback.dao.LogsDao;
import cn.edu.gxu.oisback.dao.StudentDao;
import cn.edu.gxu.oisback.domain.Examination;
import cn.edu.gxu.oisback.domain.Logs;
import cn.edu.gxu.oisback.domain.Student;
import cn.edu.gxu.oisback.utils.FirewallUtil;
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

    @Autowired
    ExaminationDao examinationDao;

    /**
     * 登录
     * @param request
     * @param student
     * @return 0: 正常登录 -1: 账号密码考试码错误 1: 账号异地登录 2:退出后无考试权限
     */
    public Integer login(HttpServletRequest request, Student student) {
        List<Student> s = studentDao.selectStudent(student);
        if(s.isEmpty()) {
            return -1;
        }
        String oldIp = s.get(0).getLastLoginIp(), newIp = IpUtil.loadRemoteUserIP(request);
        if(oldIp != null && !oldIp.equals(newIp)) {
            Logs logs = new Logs(student.getName(), newIp, "异常", student.getStudentNumber() + "-" + student.getName() + ": 重复登录(原IP:" + oldIp + ", 现IP:" + newIp + ")", System.currentTimeMillis(), student.getExaminationId());
            logsDao.insertLog(logs);
            return 1;
        } else if(oldIp != null) {
            return 2;
        }
        Examination examination = new Examination();
        examination.setId(student.getId());
        List<Examination> list = examinationDao.selectExamination(examination);
        if(FirewallUtil.register(newIp, list.get(0))) {
            return 0;
        }
        return 1;
    }
}
