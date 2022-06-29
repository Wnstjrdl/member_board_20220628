package com.its.member.entity;


import com.its.member.dto.MemberDTO;
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
    @Column(length = 30 ,updatable = false)
    private  String memberId;
    @Column(length = 30,nullable = false)
    private String memberPassword;
    @Column(length = 20,nullable = false)
    private String memberName;

    @Column(length = 50)
    private  String memberEmail;

    @Column(length = 30)
    private String memberMobile;


    @Column(length = 500)
    private String memberProfile;


    public  static  MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity= new MemberEntity();
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberProfile(memberDTO.getMemberProfile());

    return  memberEntity;
    }
}
