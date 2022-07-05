package com.its.member.repository;

import com.its.member.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {


//   List<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q1, String q2);

   List<BoardEntity> findByBoardTitleContaining(String q1);
   List<BoardEntity> findByBoardWriterContaining(String q2);
}
