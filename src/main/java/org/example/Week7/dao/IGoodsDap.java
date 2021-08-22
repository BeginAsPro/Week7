package org.example.Week7.dao;


import org.example.Week7.vo.GoodsVO;

import java.util.List;


public interface IGoodsDap {

    void add(GoodsVO goodsVO);

    void update(GoodsVO goodsVO);

    GoodsVO get(int id);

    List<GoodsVO> getList(GoodsVO goodsVO);
}