package cn.bdqn.controller;

import cn.bdqn.domain.ProductInfo;
import cn.bdqn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class CacheController {
    //@Resource
    @Autowired
    private ProductService productService;


    @RequestMapping("/save")
    public boolean saveTest(ProductInfo info) throws Exception {
        System.out.println(info.getProduct_name() + ":" );
        info.setPrice(new Random(1000).nextDouble());
        return productService.saveProductInfo(info) == null ? false : true;
    }

    @RequestMapping("/get")
    public ProductInfo getTest(Long id) throws Exception {
        System.out.println(id);
        return productService.getProductInfoById(id);
    }

    @RequestMapping("/getRedis")
    public ProductInfo getToRedisTest(Long id) throws Exception {
        System.out.println(id);
        return productService.getProductInfoByIdToRedis(id);

    }
}