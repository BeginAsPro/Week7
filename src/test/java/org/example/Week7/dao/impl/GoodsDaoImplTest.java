package org.example.Week7.dao.impl;

import org.example.Week7.Week7Application;
import org.example.Week7.service.IGoodsService;
import org.example.Week7.vo.GoodsVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * GoodsDaoImpl Tester.
 *
 * @author <Authors name>
 * @since
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Week7Application.class)
public class GoodsDaoImplTest {

    @Autowired
    IGoodsService goodsService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: add(GoodsVO goodsVO)
     *
     */
    @Test
    public void testAdd() throws Exception {
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setId(1);
        goodsVO.setPrice(new BigDecimal(3000.00));
        goodsVO.setName("maotai");
        goodsVO.setProductTag('S');
        goodsVO.setContent("this is maotai");
        goodsVO.setCreatedTime(new Date(System.currentTimeMillis()));
        goodsVO.setModifiedTime(new Date(System.currentTimeMillis()));
        goodsService.add(goodsVO);
    }

    @Test
    public void testGet() throws  Exception {
        GoodsVO goodsVO = goodsService.get(1);
        System.out.println(goodsVO.getName());
    }

}
