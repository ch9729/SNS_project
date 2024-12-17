package com.example.sns_project.mapper;

import com.example.sns_project.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 전체 회원 조회
    @Select("SELECT * FROM user")
    public List<User> selectUsers();

    // userNum을 통해 1명에 대한 회원 조회
    @Select("SELECT * FROM user WHERE userNum=#{userNum}")
    public User getUserByNum(Long userNum);

    //ID로 사용자 조회
    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(String id);

    // 회원 추가
    @Insert("INSERT INTO user(id, password, name, alias) VALUES (#{id}, #{password}, #{name}, #{alias})")
    int insertUser(String id, String password, String name, String alias);

    // 로그인
    @Select("SELECT count(*) FROM user WHERE id = #{id} AND password = #{password}")
    int selectUser(String id, String password);

    // 회원수정
    @Update("UPDATE user SET name= #{name} , password= #{password}, alias= #{alias}")
    int updateUser(String password, String name, String alias);

    // 회원삭제
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUser(String id);
}
