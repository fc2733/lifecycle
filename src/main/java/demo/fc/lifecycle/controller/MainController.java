package demo.fc.lifecycle.controller;

import demo.fc.lifecycle.entity.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-07 1:17
 */
@RestController
@RequestMapping("main")
public class MainController {

    @RequestMapping
    public Student getInfo() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Fang Chao");
        return student;
    }
}
