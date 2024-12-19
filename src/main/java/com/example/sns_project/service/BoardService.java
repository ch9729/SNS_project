package com.example.sns_project.service;

import com.example.sns_project.dto.BoardDTO;
import com.example.sns_project.entity.Board;
import com.example.sns_project.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper bMapper;

    public void insertBoard(Board board) {
        bMapper.insertBoard(board);
    }

    public List<BoardDTO> getAllBoards() {
        return bMapper.getBoardList();
    }

    public List<BoardDTO> getBoardsByPage(String keyword) {
        return bMapper.getBoard(keyword);
    }

    public BoardDTO getBoardById(Long id) {
        return bMapper.boardDetail(id);
    }
}
