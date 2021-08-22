package org.example.Week7.dao.impl;


import org.example.Week7.common.DynamicDataSource;
import org.example.Week7.common.JdbcUtils;
import org.example.Week7.dao.IGoodsDap;
import org.example.Week7.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.data.util.CastUtils.cast;

@Repository
public class GoodsDaoImpl implements IGoodsDap {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet rs;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Override
    public void add(GoodsVO goodsVO) {
        try {
            conn = dynamicDataSource.getConnection();
            String sql = "insert into product_info(id,price,name,product_tag,content,created_time,modified_time)values(?,?,?,?,?,sysdate(),sysdate())";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,goodsVO.getId());
            statement.setBigDecimal(2, goodsVO.getPrice());
            statement.setString(3, goodsVO.getName());
            statement.setString(4, goodsVO.getProductTag().toString());
            statement.setString(5, goodsVO.getContent());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, statement, rs);
        }
    }

    @Override
    public void update(GoodsVO goodsVO) {

    }

    @Override
    public GoodsVO get(int id) {
        GoodsVO goodsVO = new GoodsVO();
        try {
            String sql = "select id,price,name,product_tag,content,created_time,modified_time from product_info where id = ?";
            conn = dynamicDataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                goodsVO.setId(rs.getInt(1));
                goodsVO.setPrice(rs.getBigDecimal(2));
                goodsVO.setName(rs.getString(3));
                goodsVO.setProductTag(cast(rs.getString(4)));
                goodsVO.setContent(rs.getString(5));
                goodsVO.setCreatedTime(rs.getDate(6));
                goodsVO.setModifiedTime(rs.getDate(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn, statement, rs);
        }
        return goodsVO;
    }

    @Override
    public List<GoodsVO> getList(GoodsVO goodsVO) {
        return null;
    }

}