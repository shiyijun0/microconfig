package cn.bdqn.dao;

import cn.bdqn.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<ProductInfo,Long> {

    //ProductInfo findByProduct_idOrderByProduct_idAsc(Long product_id);
    @Query("select q from ProductInfo q where q.product_id=?1")
    ProductInfo findone(Long product_id);

    @Query(value = "select * from Product_info q where q.productId=?1",nativeQuery = true)
    ProductInfo findtwo(Long productId);


}
