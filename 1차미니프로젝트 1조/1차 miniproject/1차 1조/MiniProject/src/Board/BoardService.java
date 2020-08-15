package Board;

import java.util.List;

public interface BoardService {

    List<BoardVO> getBoardListWithPaging(int currentPage);  // 페이징으로 보드 불러오기

    void insertBoard(BoardVO vo);                           // 게시글 삽입

    BoardVO getBoardContent(String article_no);             // 게시글 가져오기

    void deleteContent(int parseInt);                       // 게시글 삭제

    void updateContent(BoardVO vo);                         // 게시글 수정

    int getBoardCnt();                                      // 조회수 가져오기

    void updateBoardCnt(String article_no);                 // 조회수 +1

    int checkPassword(String article_no, String password);  // 패스워드 체크

    int uploadFile(String file,String article_no);          // 파일 업로드 (수정)
}

