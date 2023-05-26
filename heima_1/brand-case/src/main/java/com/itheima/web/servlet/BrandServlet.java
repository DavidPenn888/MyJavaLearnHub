package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServletImpl;
import com.itheima.web.servlet.BaseServlet;
//import sun.jvm.hotspot.debugger.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private BrandService brandService = new BrandServletImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调动service查询
        List<Brand> brands = brandService.selectAll();

        //2.转化为JSON
        String jsonString = JSON.toJSONString(brands);

        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();//JSON字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(s, Brand.class);

        brandService.add(brand);

        resp.getWriter().write("successful");
    }
    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String s = reader.readLine();//JSON字符串

        //转为int[]对象
        int[] ids = JSON.parseObject(s, int[].class);
        brandService.deleteByIds(ids);

        resp.getWriter().write("successful");
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.传参
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage,pageSize);
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.传参
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        BufferedReader reader = req.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params,Brand.class);
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        String jsonString = JSON.toJSONString(pageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

//    public int selectTotalCountByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //1.传参
//        String _currentPage = req.getParameter("currentPage");
//        String _pageSize = req.getParameter("pageSize");
//        String _brand = req.getParameter("brand");
//        int currentPage = Integer.parseInt(_currentPage);
//        int pageSize = Integer.parseInt(_pageSize);
//        Brand brand = JSON.parseObject(_brand,Brand.class);
//        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);
//        String jsonString = JSON.toJSONString(pageBean);
//        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(jsonString);
//        return
//    }


}
