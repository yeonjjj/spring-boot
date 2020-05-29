package board.board.mapper;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper     //마이바티스의 매퍼 인터페이스임을 선언
public interface BoardMapper {

    List<BoardDto> selectBoardList() throws Exception;  //메서드의 이름과 SQL의 이름이 동일해야 함

    void insertBoard(BoardDto board) throws Exception;
    void insertBoardFileList(List<BoardFileDto> list) throws Exception;

    void updateHitCount(int boardIdx) throws Exception;
    BoardDto selectBoardDetail(int boardIdx) throws Exception;
    List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;

    void updateBoard(BoardDto board) throws Exception;
    void deleteBoard(int boardIdx) throws Exception;

}
