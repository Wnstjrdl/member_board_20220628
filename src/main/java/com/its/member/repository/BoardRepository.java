package com.its.member.repository;

import com.its.member.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

@Modifying
@Query(value = "update BoardEntity b set  b.boardHits=b.boardHits+1 where b.id=:id")
void  boardHits(@Param("id")Long id);

//   List<BoardEntity> findByBoardTitleContainingOrBoardWriterContaining(String q1, String q2);

   List<BoardEntity> findByBoardTitleContaining(String q1);
   List<BoardEntity> findByBoardWriterContaining(String q2);
}
