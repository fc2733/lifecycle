package demo.fc.lifecycle.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-07 1:24
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {

    private Long id;

    private String name;

    private String taskWaiting;
}
