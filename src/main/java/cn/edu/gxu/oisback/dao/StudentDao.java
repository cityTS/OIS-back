package cn.edu.gxu.oisback.dao;

import cn.edu.gxu.oisback.domain.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    List<Student> selectStudent(Student student);
    Integer updateStudentLastIp(Student student);
}
