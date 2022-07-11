package com.its.member.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column
    private  String commentWriter;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public  static  CommentEntity toSaveEntity( BoardEntity boardEntity,MemberEntity memberEntity){
       CommentEntity commentEntity= new CommentEntity();
       commentEntity.setCommentWriter(memberEntity.getMemberEmail());
       commentEntity.setMemberEntity(memberEntity);
       commentEntity.setBoardEntity(boardEntity);
       return  commentEntity;


    }
}
