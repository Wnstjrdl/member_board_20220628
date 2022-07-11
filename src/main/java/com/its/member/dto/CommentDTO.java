package com.its.member.dto;


import com.its.member.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private LocalDateTime commentCreatedDate;




    public static CommentDTO toCommentDTO(CommentEntity commentEntity){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentWriter(commentEntity.getMemberEntity().getMemberEmail());
        commentDTO.setCommentCreatedDate(commentEntity.getCommentCreatedDate());

        return commentDTO;
    }
}
