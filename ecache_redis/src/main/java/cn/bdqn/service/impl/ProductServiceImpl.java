package cn.bdqn.service.impl;

import cn.bdqn.dao.ProductDao;
import cn.bdqn.domain.ProductInfo;
import cn.bdqn.service.ProductService;
import cn.bdqn.zooker.ZookeeperLockSingle;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class ProductServiceImpl implements ProductService {
    private Log log = LogFactory.getLog(ProductServiceImpl.class);

    //@Resource
    @Autowired
    private Jedis cluster;
   // @Resource
   /*@Autowired
    private JedisCluster jedisCluster;*/
    @Autowired
    private ProductDao productDao;
    private static final String CACHE_STRATEGY = "local";
    private static final String KET_PREFIX = "key_";
    @CachePut(value=CACHE_STRATEGY,key="'key_'+#info.getProduct_id()")
    @Override
    public ProductInfo saveProductInfo(ProductInfo info) throws Exception {
        log.error("*********************保存数据********************"+System.currentTimeMillis());
        long s=System.currentTimeMillis();
        info.setModifyTime(s);
        //productService.saveProductInfo(info);
        productDao.save(info);
        return info;
    }

    @Cacheable(value=CACHE_STRATEGY,key="'key_'+#id")
    @Override
    public ProductInfo getProductInfoById(Long id) throws Exception {
        log.error("*********************没有Ehcache缓存********************");
        log.error("*********************查询Redis********************");
        ProductInfo productInfoById = null;
        try {
            productInfoById = getProductInfoByIdToRedis(id);
            if (null != productInfoById) {
                return productInfoById;
            }
        } catch (Exception e) {
            log.error("*********************查询了Redis无缓存********************");
        }
        log.error("*********************查询了数据库********************");
        productInfoById = productDao.findone(id);
        ProductInfo   productInfoByIds=productDao.findtwo(id);
        System.out.println("*********"+productInfoById.getProduct_name());
        //保存到Redis
        commonSaveToRedis(productInfoById);
        return productInfoById;
    }

    /**
     * 以安全的方式保存到Redis中
     * @Function:  CacheServiceImpl.java
     * @Description:
     *
     * @param productInfoById
     * @param productInfoById
     * @throws Exception
     * @return void
     * @version: v1.0.0
     * @date:  2018年1月29日 下午7:53:29
     */
    private void commonSaveToRedis(ProductInfo productInfoById) throws Exception {
        long product_id = productInfoById.getProduct_id();
        //保存到Redis
        ZookeeperLockSingle lockSingle = ZookeeperLockSingle.getSingleZKLock();
        boolean acquireDistrbutedLock = lockSingle.acquireDistrbutedLock(product_id);
        //获取zookeeper锁

        if (acquireDistrbutedLock) {
            //比较Redis中缓存对象是否存在
            ProductInfo productInfoByIdToRedis = getProductInfoByIdToRedis(product_id);
            String key = KET_PREFIX + productInfoById.getProduct_id();
            if (null != productInfoByIdToRedis) {
                long redisTime = productInfoByIdToRedis.getModifyTime();
                long nowTime = productInfoById.getModifyTime();

                //比较缓存中数据是否为最新
                if (redisTime < nowTime) {

                    String jsonStr = JSONObject.toJSONString(productInfoById);
                    String set = cluster.set(key,jsonStr);
                    log.error("*********************保存数据结果********************"+set);
                }
            } else {
                String jsonStr = JSONObject.toJSONString(productInfoById);
                String set = cluster.set(key,jsonStr);
                log.error("*********************保存数据结果********************"+set);
            }
        }
        //释放锁
        lockSingle.releaseDistrbuteProductLock(product_id);
    }

    @CacheEvict(value=CACHE_STRATEGY, key="'key_'+#id")
    @Override
    public void releaseProductById(Long id) throws Exception {

    }

    @Override
    public void saveProductInfoToRedis(ProductInfo info) throws Exception {
        commonSaveToRedis(info);

    }

    @Override
    public ProductInfo getProductInfoByIdToRedis(Long id) throws Exception {
        String key = KET_PREFIX + id;
        String string = cluster.get(key);
        ProductInfo parseObject = JSONObject.parseObject(string, ProductInfo.class);
        return parseObject;
    }

    @Override
    public void releaseProductByIdToRedis(Long id) throws Exception {
        String key = KET_PREFIX + id;
        Long del = cluster.del(key);
        log.error("*********************删除数据结果********************"+del);
    }

}
