package demo.fc.lifecycle.service;

import demo.fc.lifecycle.entity.Student;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-14 11:23
 */
public interface StudentService {

    /**
     * 查询学生
     * @param id id
     * @return 学生
     */
    Student getStudent(Long id);
}
