package com.qing.servlet;

import com.qing.db.DBUtil;
import com.qing.exception.BusinessExceeption;
import com.qing.exception.ParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ArticleDeleteServlet extends BaseServlet{
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("ids");
        int[] intIdArray = null;
        try {
             String[]idArray = ids.split(",");
             intIdArray = new int[idArray.length];
            for (int i = 0; i < idArray.length; i++) {
                intIdArray[i] = Integer.parseInt(idArray[i]);
            }
        }catch (Exception e){
            throw new ParameterException("请求参数错误ids="+ ids);
        }



        Connection connection = null;
        PreparedStatement ps = null;
        //处理业务及数据库操作
        try {
            connection = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in(");
            for (int i = 0; i < ids.length(); i++) {
                if (i==0){
                    sql.append("?");
                }else {
                    sql.append(",?");
                }
            }
            sql.append(")");
            for (int i = 0; i < ids.length(); i++) {
                ps.setInt(i+1,intIdArray[i]);
            }
            int effect = ps.executeUpdate();
            if (effect > 0) {
                return effect;
            } else {
                throw new BusinessExceeption("没有该用户" );
            }
        } finally {
            DBUtil.close(connection, ps, null);
        }
    }
}
