package com.ex.springboot.dto;

import lombok.Data;
import java.util.Date;

@Data
public class SimpleBbsDto {
	private int id;
	private String writer;
	private String title;
	private String content;
	private int b_hit;
	private String b_reg_date; // 등록 날짜
	private String b_upd_date; // 수정 날짜
}
