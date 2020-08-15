package Board;

import java.util.List;

public class BoardServiceImpl implements BoardService {

    private BoardDAO dao = new BoardDAO();


    public void insertBoard(BoardVO vo){
        dao.insertBoard(vo);
    }

    public BoardVO getBoardContent(String article_no){
        BoardVO vo= dao.getBoardContent(article_no);
        return vo;
    }

    public List<BoardVO> getBoardList() {
        List<BoardVO> list = dao.getBoardList();
        return list;
    }                                                       // no use

    @Override
    public List<BoardVO> getBoardListWithPaging(int pageNo){
        List<BoardVO> list = dao.getBoardListWithPaging(pageNo);
        return list;
    }

    @Override
    public void updateBoardCnt(String article_no){
        dao.updateBoardCnt(article_no);
    }

    @Override
    public int getBoardCnt(){
        int result = dao.getBoardCnt();
        return result;
    }

    @Override
    public void deleteContent(int article_no){
        dao.deleteContent(article_no);
    }

    @Override
    public void updateContent(BoardVO vo){
        dao.updateContent(vo);
    }
    @Override
    public int checkPassword(String article_no,String input){
        String dbPassword = dao.getPassword(article_no);
        if(dbPassword.equals(input)){
            return 1;
        }
        return 0;
    }

    @Override
    public int uploadFile(String file,String article_no) {
        if(dao.uploadFile(file,article_no)==1){
            return 1;
        }
        return 0;
    }
}
