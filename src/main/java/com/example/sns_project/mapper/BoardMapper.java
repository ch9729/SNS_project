package com.example.sns_project.mapper;

import com.example.sns_project.dto.BoardDTO;
import com.example.sns_project.entity.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public void insertBoard(Board board);

    List<BoardDTO> getBoardList();

    List<BoardDTO> getBoard(String keyword);
}
