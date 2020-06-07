package board.board.repository;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//스프링 데이터 JPA에서 제공하는 CrudRepository 상속
//파라미터: 리포지토리에서 사용할 도메인 클래스, 도메인의 id 타입
public interface JpaBoardRepository extends CrudRepository<BoardEntity, Integer> {

    //게시글 번호로 정렬하여 전체 게시글 조회
    //규칙에 맞게 메서드를 추가하면 메서드의 이름에 따라 쿼리가 생성되어 실행됨
    List<BoardEntity> findAllByOrderByBoardIdxDesc();

    //@Query 어노테이션: 실행하고 싶은 쿼리 직접 정의
    @Query("SELECT file FROM BoardFileEntity file WHERE board_idx = :boardIdx AND idx = :idx")
    BoardFileEntity findBoardFile(@Param("boardIdx") int boardIdx, @Param("idx") int idx);

}
