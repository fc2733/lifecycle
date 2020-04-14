package demo.fc.lifecycle.mapper;

import demo.fc.lifecycle.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author chao.fang@hand-china.com
 * @date 2020-04-14 11:24
 */
@Mapper
public interface StudentMapper {

    /**
     * 查询学生
     * @param id id
     * @return 学生
     */
    Student getStudent(@Param("id") Long id);
}
