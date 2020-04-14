package demo.fc.lifecycle.service.impl;

import demo.fc.lifecycle.entity.Student;
import demo.fc.lifecycle.mapper.StudentMapper;
import demo.fc.lifecycle.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-14 11:23
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }


    /**
     * 查询学生
     * @param id id
     * @return 学生
     */
    @Override
    public Student getStudent(Long id) {
        return studentMapper.getStudent(id);
    }
}
