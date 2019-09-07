package com.qing.servlet;

import com.qing.db.DBUtil;
import com.qing.po.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ArticleListServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println(req.getParameter("id"));
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "select a.id,a.title,a.content,a.create_time from article a join user u on a.user_id=u.id where u.id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(req.getParameter("id")));
            ResultSet rs = ps.executeQuery();
            List<Article> articleList = new ArrayList<>();
            while (rs.next()){
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getTimestamp("create_time"));
                articleList.add(article);
            }
            System.out.println(articleList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
