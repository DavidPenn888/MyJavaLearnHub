package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServletImpl implements BrandService {
    //1.创建SqlSessionFactory工厂对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        //2.获取SQL Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2.获取SQL Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2.获取SQL Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取SQL Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        //4-1.计算开始索引
        int begin = (currentPage - 1 ) * pageSize;
        //4-2.计算查询条目数
        int size = pageSize;
        //4-3.查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin,size);
        //4-4.查询总记录数
        int totalCount = mapper.selectTotalCount();
        //4-5.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public int selectAllCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int totalCount = mapper.selectTotalCount();
        return totalCount;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SQL Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        //4-1.计算开始索引
        int begin = (currentPage - 1 ) * pageSize;
        //4-2.计算查询条目数
        int size = pageSize;

        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() != 0) {
            brand.setBrandName("%"+brandName+"%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() != 0) {
            brand.setCompanyName("%"+companyName+"%");
        }
        //4-3.查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin,size,brand);
        //4-4.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);
        //4-5.封装PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public int selectTotalCountByCondition(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int totalCount = mapper.selectTotalCountByCondition(brand);
        System.out.println(brand+" successful");
        return totalCount;
    }


}
