package board.board.controller;

import board.board.dto.BoardDto;
import board.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller + @ResponseBody : 해당 API의 응답 결과(JSON 형식)를 응답 바디를 이용해 보내줌
@RestController
public class RestBoardApiController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value="/api/board", method=RequestMethod.GET)
    public List<BoardDto> openBoardList() throws Exception{
        return boardService.selectBoardList();  //뷰에 보낼 필요 없이 바로 API의 응답 결과로 사용
    }

    @RequestMapping(value="/api/board/write", method=RequestMethod.POST)
    //POST, PUT : 파라미터를 HTTP 패킷의 바디에 담아서 전송 -> @RequestBody 어노테이션 사용
    //GET : 요청 주소에 파라미터를 같이 보냄 -> @RequestParam 어노테이션 사용
    public void insertBoard(@RequestBody BoardDto board) throws Exception{
        boardService.insertBoard(board, null);
    }

    @RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.GET)
    public BoardDto openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception{
        return boardService.selectBoardDetail(boardIdx);
    }

    @RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.PUT)
    public String updateBoard(@RequestBody BoardDto board) throws Exception{
        boardService.updateBoard(board);
        return "redirect:/board";
    }

    @RequestMapping(value="/api/board/{boardIdx}", method=RequestMethod.DELETE)
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/board";
    }

}
