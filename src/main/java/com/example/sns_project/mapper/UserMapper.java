package com.example.sns_project.mapper;

import com.example.sns_project.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    // 전체 회원 조회
    @Select("SELECT * FROM user")
    public List<User> selectUsers();

    // userNum을 통해 1명에 대한 회원 조회
    @Select("SELECT * FROM user userNum=#{userNum}")
    public User getUserByNum(Integer userNum);

    // 회원 추가
    @Insert("INSERT INTO user VALUES (#{id}, #{passoword}, #{name}, #{alias})")
    int insertUser(String id, String passoword, String name, String alias);
}
