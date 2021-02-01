package co.micol.mvc.board.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.mvc.board.service.BoardVO;
import co.micol.mvc.common.DAO;

public class BoardsDAO extends DAO{
    //보드테이블 접속하는 dao
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private final String BOARDSELECTLIST="SELECT * FROM BOARDS ORDER BY BOARD_NO";
	private final String BOARDSELECT= "SELECT * FROM BOARDS WHERE BOARD_NO = ?";
	private final String BOARDINSERT = "INSERT INTO BOARDS VALUES(?, ?, ?, ?, ?)";
	private final String BOARDDELETE = "DELETE FROM BOARDS WHERE BOARD_NO = ?";
	private final String BOARDUPDATE = "UPDATE BOARDS SET TITLE =?, CONTENT=? WHERE BOARD_NO=?";
	public ArrayList<BoardVO> selectList() {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		
		try {
			psmt = conn.prepareStatement(BOARDSELECTLIST);
			rs = psmt.executeQuery(); //record set (자바에서의 resultset과 같음)을 리턴한다.
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreationDate(rs.getDate("creation_date"));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(); 
		}
		return list;
	}
	
	
	public BoardVO select(BoardVO vo ) {
		// 레코드 한개 조회 구문 작성.
		try {
			psmt = conn.prepareStatement(BOARDSELECT);
			psmt.setInt(1, vo.getBoardNo()); //물음표 값을 채워주는 과정
			rs=psmt.executeQuery();
			if(rs.next()) {
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCreationDate(rs.getDate("creation_date"));		
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	
	public int insert(BoardVO vo) { //boards 테이블에 데이터를 삽입.
		int n = 0;
		//여기에 입력 작업을 쓴다.
		try {
			psmt = conn.prepareStatement(BOARDINSERT);
			psmt.setInt(1, vo.getBoardNo());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setString(4, vo.getWriter());
			psmt.setDate(5, vo.getCreationDate());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	
	public int update(BoardVO vo) {
		int n = 0;
		//여기에 변경(수정) 작업을 쓴다.
		try {
			psmt =  conn.prepareStatement(BOARDUPDATE);
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getContent());
			psmt.setInt(3, vo.getBoardNo());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	public int delete(BoardVO vo) {
	int n = 0;
		//여기에 삭제 작업을 쓴다.
		try {
			psmt = conn.prepareStatement(BOARDDELETE);
			psmt.setInt(1, vo.getBoardNo());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}
	
	
	private void close() { // 열린 순서의 반대로 닫아주기.
		try {
			if(rs !=null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			
		}
	}
}
