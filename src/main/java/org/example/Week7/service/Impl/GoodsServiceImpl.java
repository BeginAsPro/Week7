package org.example.Week7.service.Impl;


import org.example.Week7.common.DynamicDataSource;
import org.example.Week7.common.ReadOnly;
import org.example.Week7.dao.IGoodsDap;
import org.example.Week7.service.IGoodsService;
import org.example.Week7.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private IGoodsDap goodsDao;

    @Override
    public void add(GoodsVO goodsVO) {
        goodsDao.add(goodsVO);
    }

    @Override
    public void update(GoodsVO goodsVO) {

    }

    @Override
    @ReadOnly
    public GoodsVO get(int id) {
        System.out.println(DynamicDataSource.getDataSource());
        return goodsDao.get(id);
    }

    @Override
    @ReadOnly
    public List<GoodsVO> getList(GoodsVO goodsVO) {
        return null;
    }
}