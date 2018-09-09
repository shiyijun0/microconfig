package cn.bdqn.service;

import cn.bdqn.domain.ProductInfo;

/**
 * 缓存服务
 */
public interface ProductService {
    /**
     * 保存商品信息到本地的ehcache
     * @Description:
     *
     * @param info
     * @return
     * @throws Exception
     * @return ProductInfo
     * @version: v1.0.0
     */
    ProductInfo saveProductInfo(ProductInfo info) throws Exception;
    /**
     * 根据商品ID查询商品信息
     * @Description:
     *
     * @param id
     * @return
     * @throws Exception
     * @return ProductInfo
     * @version: v1.0.0
     */
    ProductInfo getProductInfoById(Long id) throws Exception;

    /**
     * 根据Id清楚本地Ehcache缓存
     * @Function:  ICacheService.java
     * @Description:
     *
     * @param id
     * @throws Exception
     * @return void
     * @version: v1.0.0
     */
    void releaseProductById(Long id) throws Exception;
    /**
     *
     * @Function:  ICacheService.java
     * @Description: 把信息保存到Redis
     *
     * @param info
     * @throws Exception
     * @return void
     * @version: v1.0.0
     * @date:  2018年1月29日 下午7:34:19
     */
    void saveProductInfoToRedis(ProductInfo info) throws Exception;
    /**
     * 从Redis中获取缓存信息
     * @Function:  ICacheService.java
     * @Description:
     *
     * @param id
     * @return
     * @throws Exception
     * @return ProductInfo
     * @version: v1.0.0
     */
    ProductInfo getProductInfoByIdToRedis(Long id) throws Exception;

    /**
     * 根据Id清楚本地Redis缓存
     * @Function:  ICacheService.java
     * @Description:
     *
     * @param id
     * @throws Exception
     * @return void
     * @version: v1.0.0
     */
    void releaseProductByIdToRedis(Long id) throws Exception;
}
