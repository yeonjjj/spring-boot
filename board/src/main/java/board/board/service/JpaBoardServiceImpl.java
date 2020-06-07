package board.board.service;

import board.board.entity.BoardEntity;
import board.board.entity.BoardFileEntity;
import board.board.repository.JpaBoardRepository;
import board.common.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardServiceImpl implements JpaBoardService{

    @Autowired
    JpaBoardRepository jpaBoardRepository;

    @Autowired
    FileUtils fileUtils;

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public void saveBoard(BoardEntity board, MultipartHttpServletRequest
            multipartHttpServletRequest) throws Exception {
        board.setCreatorId("admin");
        List<BoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
        if(CollectionUtils.isEmpty(list)==false){
            //첨부파일 목록이 @OneToMany 어노테이션으로 연관관계가 설정되어 있음
            //→ 게시글을 저장할 때 첨부파일 목록도 자동으로 저장됨
            board.setFileList(list);
        }
        jpaBoardRepository.save(board);     //CrudRepository 인터페이스에서 제공하는 메서드
    }

    @Override
    public BoardEntity selectBoardDetail(int boardIdx) throws Exception {
        //findById(): JPA의 CrudRepository에서 제공하는 기능으로 주어진 id를 가진 엔티티 조회
        //Optinoal 클래스: 어떤 객체의 값으로 Null이 아닌 값을 가지고 있어 NullPointerException이 발생하지 않음
        Optional<BoardEntity> optional = jpaBoardRepository.findById(boardIdx);
        if(optional.isPresent()){   //객체의 값이 존재한다면
            BoardEntity board = optional.get();     //객체의 값 가져옴
            board.setHitCnt(board.getHitCnt()+1);
            jpaBoardRepository.save(board);     //insert와 update 두 가지 역할 모두 수행 가능
            return board;
        }
        else{   //게시글 번호가 잘못된 경우
            throw new NullPointerException();
        }
    }

    @Override
    public void deleteBoard(int boardIdx) {
        jpaBoardRepository.deleteById(boardIdx);
    }

    @Override
    public BoardFileEntity selectBoardFileInformation(int boardIdx, int idx) throws Exception {
        BoardFileEntity boardFile = jpaBoardRepository.findBoardFile(boardIdx, idx);
        return boardFile;
    }
}
