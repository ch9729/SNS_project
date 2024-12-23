package com.example.sns_project.mapper;

import com.example.sns_project.dto.UserDTO;
import com.example.sns_project.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 전체 회원 조회
    @Select("SELECT * FROM user")
    public List<User> selectUsers();

    @Select("SELECT userNum FROM user WHERE id = #{name}")
    public Long getUserNumByUsername(String name);

    // userNum을 통해 1명에 대한 회원 조회
    @Select("SELECT * FROM user WHERE userNum=#{userNum}")
    public User getUserByNum(Long userNum);

    //ID로 사용자 조회
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(String id);

    // 회원 추가
    @Insert("INSERT INTO user(id, password, name, alias, profile) VALUES (#{id}, #{password}, #{name}, #{alias}, #{profile})")
    int insertUser(String id, String password, String name, String alias, String profile);

    // 로그인
    @Select("SELECT count(*) FROM user WHERE id = #{id} AND password = #{password}")
    int selectUser(String id, String password);

    // 회원수정
    //Update({"<script>", "UPDATE user", "<set>", "<if test='password != null'> password = #{password}, </if>", "<if test='name != null'> name = #{name}, </if>", "<if test='alias != null'> alias = #{alias}, </if>", "<if test='profile != null'> profile = #{profile}, </if>", "</set>", "WHERE id = #{id}", "</script>"})
    //int updateUser(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("alias") String alias, @Param("profile") String profile);
    @Update({"<script>", "UPDATE user", "<set>", "<if test='password != null'> password = #{password}, </if>", "<if test='name != null'> name = #{name}, </if>", "<if test='alias != null'> alias = #{alias}, </if>", "<if test='profile != null'> profile = #{profile}, </if>", "</set>", "WHERE id = #{id}", "</script>"})
    int updateUser(@Param("id") String id, @Param("password") String password, @Param("name") String name, @Param("alias") String alias, @Param("profile") String profile);

    // 회원삭제
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(String id);

    // 회원검색
    @Select("SELECT * FROM user WHERE name LIKE CONCAT('%',#{query},'%') OR alias LIKE CONCAT('%',#{query},'%')")
    List<User> selectUserLike(@Param("query") String query);


}
