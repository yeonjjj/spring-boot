package board.board.service;

import board.board.dto.BoardDto;
import board.board.dto.BoardFileDto;
import board.board.mapper.BoardMapper;
import board.common.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

@Slf4j
@Service    //비즈니스 로직을 처리하는 서비스 클래스임을 나타냄
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;    //DB에 접근하는 DAO 빈 선언

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<BoardDto> selectBoardList() throws Exception{
        return boardMapper.selectBoardList();   //사용자 요청을 처리하기 위한 비즈니스 로직 구현
    }

    @Override
    public void insertBoard(BoardDto board, MultipartHttpServletRequest
            multipartHttpServletRequest) throws Exception{

        boardMapper.insertBoard(board);     //게시글 등록
        //업로드 된 파일을 서버에 저장하고 파일의 정보를 가져옴
        List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list)==false){
            boardMapper.insertBoardFileList(list);
        }

//        if(ObjectUtils.isEmpty(multipartHttpServletRequest)==false){
//            //서버로 한꺼번에 전송되는 한 개 이상의 파일 태그 이름을 이터레이터 형식으로 가져와 구분
//            Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//            String name;
//            while(iterator.hasNext()){
//                name=iterator.next();
//                log.debug("file tag name : "+name);
//                //가져온 파일 태그 이름을 이용해 파일 태그에서 선택된 파일을 가져옴
//                List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
//                //받아온 파일의 정보 출력, 업로드된 파일은 MultipartFile 인터페이스로 표현됨
//                for(MultipartFile multipartFile : list){
//                    log.debug("start file information");
//                    log.debug("file name : "+multipartFile.getOriginalFilename());
//                    log.debug("file size : "+multipartFile.getSize());
//                    log.debug("file content type : "+multipartFile.getContentType());
//                    log.debug("end file information\n");
//                }
//            }
//        }
    }

    @Override
    public BoardDto selectBoardDetail(int boardIdx) throws Exception{
        BoardDto board = boardMapper.selectBoardDetail(boardIdx);   //선택된 게시글의 내용 조회
        //게시블 번호로 게시글의 첨부파일 목록을 조회하여 BoardDto 클래스에 조회된 첨부파일 목록 저장
        List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
        board.setFileList(fileList);

        boardMapper.updateHitCount(boardIdx);   //선택된 게시글의 조회수 증가시킴
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

