package com.ex.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import com.ex.springboot.dto.CgProduct;
import java.util.List;

@Repository
public class CGProductDaoImpl implements CGProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(CgProduct cgProduct) {
        String sql = "INSERT INTO cg_product (P_CODE, P_NAME, P_CATEGORY, P_PRICE, P_STOCK, P_IMAGE, P_CONTENT, P_DC_YN, P_DC_PERCENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cgProduct.getP_Code(), cgProduct.getP_Name(), cgProduct.getP_Category(), cgProduct.getP_Price(), cgProduct.getP_Stock(), cgProduct.getP_Image(), cgProduct.getP_Content(), cgProduct.getP_Dc_Yn(), cgProduct.getP_Dc_Percent());
    }

    @Override
    public List<CgProduct> findAll() {
        String sql = "SELECT * FROM cg_product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CgProduct.class));
    }
}
