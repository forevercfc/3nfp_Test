package com.ex.springboot.dao;

import java.util.List;
import com.ex.springboot.dto.CgProduct;

public interface CGProductDao {
    void save(CgProduct cgProduct);
    List<CgProduct> findAll();
}
