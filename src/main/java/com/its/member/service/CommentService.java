package com.its.member.service;


import com.its.member.dto.CommentDTO;
import com.its.member.entity.BoardEntity;
import com.its.member.entity.CommentEntity;
import com.its.member.entity.MemberEntity;
import com.its.member.repository.BoardRepository;
import com.its.member.repository.CommentRepository;

import com.its.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final CommentRepository commentRepository;
    private  final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;

    public Long save(CommentDTO commentDTO) {
        MemberEntity memberEntity=memberRepository.findByMemberEmail(commentDTO.getCommentWriter()).get();
        BoardEntity boardEntity= boardRepository.findById(commentDTO.getBoardId()).get();
        CommentEntity commentEntity=CommentEntity.toSaveEntity(boardEntity,memberEntity);
        return commentRepository.save(commentEntity).getId();
    }


    public List<CommentDTO> findAll(Long boardId) {
    BoardEntity boardEntity=boardRepository.findById(boardId).get();
    List<CommentEntity> commentEntityList=boardEntity.getCommentEntityList();
    List<CommentDTO> commentDTOList=new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList){
        CommentDTO commentDTO= CommentDTO.toCommentDTO(commentEntity);
        commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

}
