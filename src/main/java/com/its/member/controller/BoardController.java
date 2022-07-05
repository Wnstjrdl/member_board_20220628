package com.its.member.controller;

import com.its.member.common.PagingConst;
import com.its.member.dto.BoardDTO;
import com.its.member.dto.MemberDTO;
import com.its.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //글작성 화면
    @GetMapping("/save")
    public  String saveForm(){return "/boardPages/save";}
    //글작성처리
    @PostMapping("/save")
    public  String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
       boardService.save(boardDTO);
       return "redirect:/board/paging";
    }
    //페이징
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / PagingConst.BLOCK_LIMIT))) - 1) * PagingConst.BLOCK_LIMIT + 1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "boardPages/paging";
    }

    //상세조회
    @GetMapping("/detail/{id}")
    public  String findById(@PathVariable Long id,Model model){
        BoardDTO boardDTO= boardService.findById(id);
        model.addAttribute("board",boardDTO);
        System.out.println("boardDTO = " + boardDTO);

        return "boardPages/detail";
    }
    // 삭제요청
    @GetMapping("/delete/{id}")
    public  String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/paging";
    }
    //수정화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id,Model model){
        BoardDTO boardDTO= boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "boardPages/update";

    }
    // 수정처리
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO){
        boardService.update(boardDTO);
        System.out.println("boardDTO="+boardDTO);
        return  "redirect:/board/detail/"+boardDTO.getId();
    }

    //제목
@GetMapping("/search")
public String search(@RequestParam("q1") String q1, Model model) {
    List<BoardDTO> searchList = boardService.search(q1);
    model.addAttribute("boardList", searchList);
    return "boardPages/search";
}
//작성자
    @GetMapping("/search2")
    public String search2(@RequestParam("q2") String q2, Model model) {
        List<BoardDTO> searchList = boardService.search2(q2);
        model.addAttribute("boardList", searchList);
        return "boardPages/search";
    }
}
