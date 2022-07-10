package com.its.member.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String boardWriter;
    private String commentWriter;
    private int boardHits;
    private LocalDateTime commentCreatedDate;
}
