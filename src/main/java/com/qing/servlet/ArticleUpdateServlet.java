package com.qing.servlet;

import com.qing.db.DBUtil;
import com.qing.exception.BusinessExceeption;
import com.qing.po.Article;
import com.qing.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            //获取JSON类型的请求数据
            Article article = JSONUtil.get(request, Article.class);


            String sql = "update article set title=?,content=? where id=?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setInt(3, article.getId());
            int effect = preparedStatement.executeUpdate();
            if (effect > 0) {
                return effect;
            } else {
                throw new BusinessExceeption("没有该文章" + article.getId());
            }
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }
}
