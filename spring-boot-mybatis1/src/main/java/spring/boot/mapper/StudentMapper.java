package spring.boot.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import spring.boot.entity.Student;

/**
 * @author yangjian
 * @date 2022/12/6 19:00
 */
@Component
@Mapper
public interface StudentMapper {
    @Insert("insert into student(sno,sname,ssex) values(#{sno},#{name},#{sex})")
    int add(Student student);

    @Update("update student set sname=#{name},ssex=#{sex} where sno=#{sno}")
    int update(Student student);

    @Delete("delete from student where sno=#{sno}")
    int deleteBysno(String sno);

    @Select("select * from student where sno=#{sno}")
    @Results(id = "student", value = {
            @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "sname", javaType = String.class),
            @Result(property = "sex", column = "ssex", javaType = String.class)
    })
    Student queryStudentBySno(String sno);

    @Insert("insert into serial(serialnum) values(#{uuid})")
    int insertBo(String uuid);
}
