package com.ex.springboot.dao;

import java.util.List;
import com.ex.springboot.dto.SimpleBbsDto;

public interface ISimpleBbsDao {
    // 페이징 처리를 위해 매개변수 추가 (page: 페이지 번호, limit: 페이지 당 보여줄 게시물 수)
    public List<SimpleBbsDto> listDao(int page, int limit);
    
    public SimpleBbsDto viewDao(String id);
    
    public int writeDao(String writer, String title, String content);
    
    public int delectDao(String id);
    
    // 게시물 업데이트 메소드
    int updateDao(String id, String writer, String title, String content);
    
    // 전체 게시물 수를 반환하는 메소드
    public int getBoardCount();

    List<SimpleBbsDto> searchDao(String query, String type);

}
