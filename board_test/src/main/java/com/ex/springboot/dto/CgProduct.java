package com.ex.springboot.dto;
import lombok.Data;


@Data
public class CgProduct {
	private String p_Code;
	private String p_Name;
	private String p_Category;
	private int p_Price;
	private int p_Stock;
	private String p_Image;
	private String p_Content;
	private String p_Dc_Yn;
	private int p_Dc_Percent;
	
	
}
