package org.example.Week7.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsVO implements Serializable {
    private int id;
    private Character productTag;
    private String name;
    private String content;
    private BigDecimal price;
    private Date createdTime;
    private Date modifiedTime;
}