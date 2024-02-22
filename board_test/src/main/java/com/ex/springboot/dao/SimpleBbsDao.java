package com.ex.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ex.springboot.dto.SimpleBbsDto;

@Repository //이 클래스를 db처리를 목적으로 하는 빈으로 등록하겠다.
public class SimpleBbsDao implements ISimpleBbsDao {

	@Autowired //우리가 설정한 디비정보를 바탕으로 오라클 드라이버 로드, 디비에 접속, 오라클드라이버 로드, 디비 접속 까지 자동으로 알아서 처리
	JdbcTemplate template;
	
//	@Override
//	public List<SimpleBbsDto> listDao() {
//		System.out.println("listDao() 호출됨");
//		String query = "select * from simpleBbs order by id desc";
//		List<SimpleBbsDto> dtos = template.query(query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
//		//쿼리문의 결과가 두 줄 이상 여러개가 나올 때 sql문을 처리하기 위해서는 JdbcTemplate의 query()메소드 사용
//		return dtos;
//	}

	@Override
    public SimpleBbsDto viewDao(String id) {
        System.out.println("viewDao() 호출됨");

        // 조회수 증가 쿼리 실행
        String updateQuery = "UPDATE simpleBbs SET B_HIT = B_HIT + 1 WHERE ID = ?";
        template.update(updateQuery, id);

        // 게시글 상세 정보 조회
        String query = "SELECT * FROM simpleBbs WHERE ID = " + id;
        SimpleBbsDto dto = template.queryForObject(query, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
        return dto;
    }


	@Override
	public int writeDao(String writer, String title, String content) {
		System.out.println("writeDao() 호출됨");
		
		String query = "insert into simpleBbs values(simpleBbs_seq.nextval, ?,?,?,0,sysdate,sysdate)";
		return template.update(query, writer, title, content);
		//sql문의 insert, update, delete 문은 JdbcTemplate의 update()메소드를 사용한다.
	}

	@Override
	public int delectDao(String id) {
		
		System.out.println("delectDao() 호출됨");
		String query = "delete simpleBbs where id=?";
		return template.update(query, id);
	}
	@Override
	public int updateDao(String id, String writer, String title, String content) {
	    System.out.println("updateDao() 호출됨");
	    String query = "UPDATE simpleBbs SET writer=?, title=?, content=? WHERE id=?";
	    return template.update(query, writer, title, content, id);
	}
//	===========페이징 처리=============================
	@Override
	public List<SimpleBbsDto> listDao(int page, int limit, String type, String word) {
	    // 페이지 계산을 위한 시작 인덱스
	    int startRow = (page - 1) * limit;
	    String searchQuery = "";
	    
	    if(word.length() > 0) {
	    	switch (type) {
		        case "title":
		        	searchQuery += "and TITLE LIKE '%"+word+"%'";
		            break;
		        case "content":
		        	searchQuery += "and CONTENT LIKE '%"+word+"%'";
		            break;
		        case "writer":
		        	searchQuery += "and WRITER LIKE '%"+word+"%'";
		            break;
		    }
	    }
	    
	    // Oracle SQL의 ROWNUM을 활용한 페이징 쿼리
	    String query = "SELECT * FROM ( "
	                 + "SELECT temp.*, ROWNUM rnum FROM ( "
	                 + "SELECT * FROM simpleBbs ORDER BY id DESC"
	                 + ") temp "
	                 + "WHERE ROWNUM <= ? "+searchQuery
	                 + ") WHERE rnum > ?";
	    
	    return template.query(query, new Object[]{startRow + limit, startRow}, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
	}

	public int getBoardCount(String type, String word) {
	    String query = "SELECT COUNT(*) FROM simpleBbs";
	    if(word.length() > 0) {
	    	switch (type) {
		        case "title":
		        	query += " where TITLE LIKE '%"+word+"%'";
		            break;
		        case "content":
		        	query += " where CONTENT LIKE '%"+word+"%'";
		            break;
		        case "writer":
		        	query += " where WRITER LIKE '%"+word+"%'";
		            break;
		    }
	    }
	    return template.queryForObject(query, Integer.class);
	}
	
//	===========검색기능
	// SimpleBbsDao 클래스에 메소드 구현
	@Override
	public List<SimpleBbsDto> searchDao(String query, String type) {
	    String sqlQuery = "SELECT * FROM simpleBbs WHERE ";
	    switch (type) {
	        case "title":
	            sqlQuery += "title LIKE ?";
	            break;
	        case "content":
	            sqlQuery += "content LIKE ?";
	            break;
	        case "writer":
	            sqlQuery += "writer LIKE ?";
	            break;
	    }
	    return template.query(sqlQuery, new Object[]{"%" + query + "%"}, new BeanPropertyRowMapper<SimpleBbsDto>(SimpleBbsDto.class));
	}




}
