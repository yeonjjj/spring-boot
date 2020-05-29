package board.board.service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service    //비즈니스 로직을 처리하는 서비스 클래스임을 나타냄
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;    //DB에 접근하는 DAO 빈 선언

    @Override
    public List<BoardDto> selectBoardList() throws Exception{
        return boardMapper.selectBoardList();   //사용자 요청을 처리하기 위한 비즈니스 로직 구현
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception{
        boardMapper.insertBoard(board);
    }

    @Override
    public BoardDto selectBoardDetail(int boardIdx) throws Exception{
        boardMapper.updateHitCount(boardIdx);   //선택된 게시글의 조회수 증가시킴
    //    int i = 10/0;
        BoardDto board = boardMapper.selectBoardDetail(boardIdx);   //선택된 게시글의 내용 조회
        return board;
    }

    @Override
    public void updateBoard(BoardDto board) throws Exception{
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int boardIdx) throws Exception{
        boardMapper.deleteBoard(boardIdx);
    }

}

