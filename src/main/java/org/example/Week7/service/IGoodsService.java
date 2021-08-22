package org.example.Week7.service;


import org.example.Week7.vo.GoodsVO;

import java.util.List;

public interface IGoodsService {

    void add(GoodsVO goodsVO);

    void update(GoodsVO goodsVO);

    GoodsVO get(int id);

    List<GoodsVO> getList(GoodsVO goodsVO);

}