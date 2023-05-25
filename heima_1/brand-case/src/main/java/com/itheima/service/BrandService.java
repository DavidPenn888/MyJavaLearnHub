package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     */
    List<Brand> selectAll();

    void add(Brand brand);

    void deleteByIds(int[] ids);

    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    int selectAllCount();

    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

    /**
     * 查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}
