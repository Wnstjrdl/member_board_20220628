package com.its.member.service;

import com.its.member.dto.BoardDTO;
import com.its.member.entity.BoardEntity;
import com.its.member.entity.MemberEntity;
import com.its.member.repository.BoardRepository;
import com.its.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    private final MemberRepository memberRepository;
   public Long save(BoardDTO boardDTO) throws IOException {
       MultipartFile boardFile = boardDTO.getBoardFile();
        String boardFileName= boardFile.getOriginalFilename();
        boardFileName= System.currentTimeMillis()+ "_"+ boardFileName;
        String savePath=  "D:\\springboot_img\\"+boardFileName;
        if(!boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));
        }
        boardDTO.setBoardFileName(boardFileName);

        Optional<MemberEntity> optionalMemberEntity=
                memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity=optionalMemberEntity.get();
            Long savedId= boardRepository.save(BoardEntity.toSaveEntity(boardDTO,memberEntity)).getId();
            return  savedId;
        }else {
            return null;
        }
   }

}
