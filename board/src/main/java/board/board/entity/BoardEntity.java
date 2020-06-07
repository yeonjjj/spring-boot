package board.board.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity     //해당 클래스가 JPA의 엔티티임을 나타냄
@Table(name="t_jpa_board")      //t_jpa_board 테이블과 매핑되도록 함
@NoArgsConstructor
@Data
public class BoardEntity {

    @Id     //엔티티의 기본키(Primary Key)임을 나타냄
    @GeneratedValue(strategy = GenerationType.AUTO)     //기본키의 생성 전략 설정
    private int boardIdx;

    @Column(nullable = false)       //컬럼에 Not Null 속성 지정
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private int hitCnt = 0;

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private LocalDateTime createdDatetime=LocalDateTime.now();

    private String updaterId;

    private LocalDateTime updatedDatetime;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)      //1:N의 관계 표현
    @JoinColumn(name="board_idx")       //릴레이션 관계가 있는 테이블의 컬럼 지정
    private Collection<BoardFileEntity> fileList;

}
