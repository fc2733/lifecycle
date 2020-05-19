package demo.fc.lifecycle.controller;

import demo.fc.lifecycle.entity.Student;
import demo.fc.lifecycle.service.StudentService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-07 1:17
 */
@Controller
@RequestMapping("main")
public class MainController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RedisTemplate<String, String> redisTemplate;
    private final StudentService studentService;

    public MainController(KafkaTemplate<String, String> kafkaTemplate,
                          RedisTemplate<String, String> redisTemplate,
                          StudentService studentService) {
        this.kafkaTemplate = kafkaTemplate;
        this.redisTemplate = redisTemplate;
        this.studentService = studentService;
    }

    @GetMapping
    public String getInfo(Model model) {
        if (!redisTemplate.hasKey("l1")) {
            redisTemplate.opsForList().rightPushAll("l1", "<1>把冰箱门儿打开。", "<2>把大象装进去。", "<3>把冰箱门儿带上。");
        } else {
            redisTemplate.opsForList().leftPop("l1");
        }
        StringBuffer stringBuffer = new StringBuffer();
        List<String> taskList = redisTemplate.opsForList().range("l1", 0, -1);
        for (String s : taskList) {
            stringBuffer.append(s);
        }
        Student student = studentService.getStudent(1L);
        if (student != null) {
            student.setTaskWaiting(stringBuffer.toString());
        }
        model.addAttribute("student", student);
        model.addAttribute("currentTime", new Date());
        return "main";
    }

    @ResponseBody
    @GetMapping("/kafka-test")
    public String kafkaTest(String msg) {
        kafkaTemplate.send("test-topic", msg);
        return "ok";
    }
}
