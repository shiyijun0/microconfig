package cn.bdqn.mapper;

import cn.bdqn.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();

    /*@Select({"SELECT * FROM T_USER WHERE PHONE = #{phone}"})
    User findUserByPhone(@Param("phone") String phone);*/

    /*@Select("select * from stu where id = #{id}")
    @Results(id = "userMap", value = {
            @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    User selectById(@Param("id")int id);*/
}