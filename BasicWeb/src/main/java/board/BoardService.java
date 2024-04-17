package board;

import java.util.List;

import javax.sql.DataSource;

public class BoardService {
	BoardDAO dao;
	
	public BoardService(DataSource dataSource) {
		this.dao = new BoardDAO(dataSource);
	}
	//모든내용 select
	public List<BoardVO> getBoardList() {
		return dao.getBoardList();
	}
	//한개의 글 상세페이지
	public BoardVO getBoard(int searchno) {
		return dao.getBoard(searchno);
	}
	//글쓰기
	public int insertBoard(BoardVO vo) {
		return dao.insertBoard(vo);
	}
	//글 수정
//	public void updateBoard(BoardVO vo) {
//		dao.updateBoard(vo);
//	}
//	// 글 삭제
//	public void deleteBoard(int deleteNo) {
//		dao.deleteBoard(deleteNo);
//	}
}