package com.demo.shop.vo;

import java.io.Serializable;

/**
 * 购物车数据的VO类
 */
public class CartVo implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private String image;
    private Long realprice;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getRealprice() {
        return realprice;
    }

    public void setRealprice(Long realprice) {
        this.realprice = realprice;
    }

    @Override
    public String toString() {
        return "CartVo{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", realprice=" + realprice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartVo cartVo = (CartVo) o;

        if (cid != null ? !cid.equals(cartVo.cid) : cartVo.cid != null) return false;
        if (uid != null ? !uid.equals(cartVo.uid) : cartVo.uid != null) return false;
        if (pid != null ? !pid.equals(cartVo.pid) : cartVo.pid != null) return false;
        if (price != null ? !price.equals(cartVo.price) : cartVo.price != null) return false;
        if (num != null ? !num.equals(cartVo.num) : cartVo.num != null) return false;
        if (title != null ? !title.equals(cartVo.title) : cartVo.title != null) return false;
        if (image != null ? !image.equals(cartVo.image) : cartVo.image != null) return false;
        return realprice != null ? realprice.equals(cartVo.realprice) : cartVo.realprice == null;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (realprice != null ? realprice.hashCode() : 0);
        return result;
    }
}
