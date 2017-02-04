package com.business.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.util.Date;

/**
 * DemoVO
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/1/20 19:24
 */
public class DemoVO implements Serializable {
    private int id;
    @Length(min = 1,max = 5,message = "名称不能太长",groups = Insert.class)
    private String name;
    private Date date;

    @Range(min = -1,max = 8,message = "价格必须在区间{min}-{max}" ,groups = Insert.class)
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

  public interface Insert{}
    public interface  Update{}
}
