package com.example.sns_project.controller;

import com.example.sns_project.dto.BoardDTO;
import com.example.sns_project.entity.Board;
import com.example.sns_project.entity.User;
import com.example.sns_project.service.BoardService;
import com.example.sns_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService bService;
    private final UserService uService;

    @GetMapping
    public String board(Model model) {
        List<BoardDTO> boards = bService.getAllBoards();
        model.addAttribute("boards", boards);
        return "board";
    }

    @GetMapping("/add")
    public String addBoard(Model model) {
        return "boardAdd";
    }

    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board, Principal principal){
        User user = uService.getUserById(principal.getName());

        board.setUserNum(user.getUserNum());

        bService.insertBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/search")
    public String searchBoard(@RequestParam("keyword")String keyword, Model model) {
        List<BoardDTO> boardsByPage = bService.getBoardsByPage(keyword);
        model.addAttribute("boards", boardsByPage);
        return "board";
    }

}
