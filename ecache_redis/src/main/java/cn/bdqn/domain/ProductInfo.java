package cn.bdqn.domain;


import javax.persistence.*;

@Entity
@Table(name = "product_info")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productId")
    private Long product_id;
    @Column(name="productName")
    private String product_name;
    @Column(name="price")
    private Double price;
    @Column(name="modifyTime")
    private long modifyTime;

    public ProductInfo() {
    }
    public ProductInfo(Long product_id, String product_name, Double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
    }
    public ProductInfo(Long product_id, String product_name, Double price,long modifyTime) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.modifyTime = modifyTime;
    }


    public long getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductInfo [product_id=" + product_id + ", product_name=" + product_name + ", price=" + price
                + ", modifyTime=" + modifyTime + "]";
    }


}

