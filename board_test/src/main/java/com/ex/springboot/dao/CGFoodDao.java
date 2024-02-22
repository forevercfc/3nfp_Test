package com.ex.springboot.dao;

import com.ex.springboot.dto.CgFood;
import java.util.List;

public interface CGFoodDao {
    void save(CgFood cgFood);
    List<CgFood> findAll();
}
