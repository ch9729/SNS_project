package com.example.sns_project.mapper;

import com.example.sns_project.entity.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO board (title,content) VALUES title = #{title}, content = #{content}")
    public int insert(Board board);
}
