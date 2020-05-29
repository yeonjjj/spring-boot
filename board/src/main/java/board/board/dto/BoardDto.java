package board.board.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data   //롬복의 어노테이션, setter의 경우 final이 선언되지 않은 필드에만 적용됨
public class BoardDto {

    //자바는 카멜 표기법, DB는 스테이크 표기법 사용 -> 마이바티스 이용하여 양측의 데이터 매핑
    private int boardIdx;
    private String title;
    private String contents;
    private int hitCnt;
    private String creatorId;
    private LocalDateTime createdDatetime;
    private String updaterId;
    private LocalDateTime updatedDatetime;
    private List<BoardFileDto> fileList;

}

