package com.its.member.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Getter@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;
    @Column(length = 30 )
    private  String memberId;
    @Column(length = 30)
    private String memberPassword;
    @Column(length = 20)
    private String memberName;
    @Column(length = 30)
    private String memberPhone;

    @Column
    private String memberProfile;

}
