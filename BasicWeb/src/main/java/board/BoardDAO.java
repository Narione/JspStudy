package board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import member.MemberVO;

public class BoardDAO {
	private DataSource dataSource;
	
	public BoardDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<BoardVO> getBoardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
//			String sql = "select no, writer, title, content, create_date, hits from board";
			String sql = 
				// as는 현장에서 거의 볼 일이 없다.
				"""
				select
					a.no,
					b.name writer,
					a.title,
					a.create_date,
					a.hits
				from
					board a
					inner join member b on a.writer = b.id
				""";
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		while(resultSet.next()) {
			int no = resultSet.getInt("no");
			String writer = resultSet.getString("writer");
			String title = resultSet.getString("title");
			Date createDate = resultSet.getDate("create_date");
			int hits = resultSet.getInt("hits");
			list.add(new BoardVO(no, writer, title, createDate.toLocalDate(), hits));
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null) {
					connection.close();					
				}
				if(connection != null) {
					statement.close();									
				}
				if(connection != null) {
					resultSet.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public BoardVO getBoard(int no) {
		return null;
	}
	
	public int insertBoard(BoardVO vo) {
		int executeUpdate = 0;
		try {
			Connection connection = dataSource.getConnection();
			String sql = "insert into board (writer, title, content) values (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, vo.getWriter());
			statement.setString(2, vo.getTitle());
			statement.setString(3, vo.getContent());
			
			executeUpdate = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return executeUpdate;
	}
	
	public int updateBoard(BoardVO vo) {
		
		int executeUpdate = 0;
		try {
			Connection connection = dataSource.getConnection();
			String sql = "update member set title = ?, content = ? where writer=? and title=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, vo.getTitle());
			statement.setString(2, vo.getContent());
			statement.setString(3, writer);
			statement.setString(3, title);
			
			executeUpdate = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return executeUpdate;
		
	}
	
	public int deleteBoard(int deleteNo) {
		int executeUpdate = 0;
		try {
			Connection connection = dataSource.getConnection();
			String sql = "delete from board where no = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, deleteNo);
			
			executeUpdate = statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return executeUpdate;
		
	}
	
	public BoardVO viewBoard(int viewNo) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
//			String sql = "select no, writer, title, content, create_date, hits from board";
			String sql = 
				// as는 현장에서 거의 볼 일이 없다.
				"""
				select
					a.no,
					b.name writer,
					a.title,
					a.create_date,
					a.hits
				from
					board a
					inner join member b on a.writer = b.id
				""";
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();
		BoardVO vo=null;
		if  (resultSet.next()) {
			String id = resultSet.getString("id");
			String name = resultSet.getString("name");
			String password = resultSet.getString("password");
			String email = resultSet.getString("email");
//			vo = new BoardVO(id, name, password, email);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(connection != null) {
					connection.close();					
				}
				if(connection != null) {
					statement.close();									
				}
				if(connection != null) {
					resultSet.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
}