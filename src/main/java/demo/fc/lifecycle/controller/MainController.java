package demo.fc.lifecycle.controller;

import demo.fc.lifecycle.entity.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-07 1:17
 */
@RestController
@RequestMapping("main")
public class MainController {

    private final RedisTemplate<String, String> redisTemplate;

    public MainController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public Student getInfo() {
        if (!redisTemplate.hasKey("l1")) {
            redisTemplate.opsForList().rightPushAll("l1", "<1>Open the fridge door.", "<2>Put the elephant in.", "<3>Close the fridge door.");
        } else {
            redisTemplate.opsForList().leftPop("l1");
        }
        StringBuffer stringBuffer = new StringBuffer();
        List<String> taskList = redisTemplate.opsForList().range("l1", 0, -1);
        for (String s : taskList) {
            stringBuffer.append(s);
        }
        Student student = new Student();
        student.setId(1L);
        student.setName("Fang Chao");
        student.setTaskWaiting(stringBuffer.toString());
        return student;
    }
}
