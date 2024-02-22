package com.ex.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import com.ex.springboot.dto.CgFood;
import java.util.List;

@Repository
public class CGFoodDaoImpl implements CGFoodDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(CgFood cgFood) {
        String sql = "INSERT INTO CG_food (F_CODE, F_NAME, F_IMAGE, P_CODE_ARR, F_VOLUME_ARR, F_RECIPE, F_TYPE_THEME, F_TYPE_MAIN, F_TYPE_SOUP, F_TYPE_SPICY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cgFood.getF_Code(), cgFood.getF_Name(), cgFood.getF_Image(), cgFood.getP_Code_Arr(), cgFood.getF_Volume_Arr(), cgFood.getF_Recipe(), cgFood.getF_Type_Theme(), cgFood.getF_Type_Main(), cgFood.getF_Type_Soup(), cgFood.getF_Type_Spicy());
    }

    @Override
    public List<CgFood> findAll() {
        String sql = "SELECT * FROM CG_food";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CgFood.class));
    }
    

    
}
