package board.board.controller;

import board.board.dto.BoardDto;
import board.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller     //해당 클래스를 컨트롤러로 동작하게 함
public class BoardController {

    //로거 생성
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;      //비즈니스 로직을 처리하는 서비스 빈 연결

    @RequestMapping("/board/openBoardList.do")      //클라이언트에서 호출한 주소와 그것을 수행할 메서드 연결
    public ModelAndView openBoardList() throws Exception{
        log.debug("openBoardList");     //debug 레벨, openBoardList라는 문자열 출력
        ModelAndView mv = new ModelAndView("/board/boardList");
        List<BoardDto> list = boardService.selectBoardList();   //비즈니스 로직 수행을 위해 메서드 호출
        mv.addObject("list", list);     //실행된 비즈니스 로직의 결과 값을 뷰에 list라는 이름으로 저장
        return mv;
    }

    @RequestMapping("/board/openBoardWrite.do")     //게시글 작성 화면 호출 주소
    public String openBoardWrite() throws Exception{
        return "/board/boardWrite";
    }

    @RequestMapping("/board/insertBoard.do")    //작성된 게시글 등록 주소
    public String insertBoard(BoardDto board) throws Exception{
        boardService.insertBoard(board);
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws  Exception{
        ModelAndView mv = new ModelAndView("/board/boardDetail");
        BoardDto board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);
        return mv;
    }

    @RequestMapping("/board/updateBoard.do")
    public String updateBoard(BoardDto board) throws Exception{
        boardService.updateBoard(board);
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/deleteBoard.do")
    public String deleteBoard(int boardIdx) throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/board/openBoardList.do";
    }

}

