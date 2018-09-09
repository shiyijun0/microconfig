package com.hry.spring.redis.cache;

import com.hry.spring.redis.cache.support.BookQry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
@RestController
public class CacheTestController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookService2 bookService2;

    @RequestMapping("/a")
    public void queryBookCacheable(){
        System.out.println(bookService.queryBookCacheable("1"));
    }

    @RequestMapping("/b")
    public void queryBookCacheable_2(){
        System.out.println(bookService.queryBookCacheable_2("2"));
    }

    @RequestMapping("/c")
    public void queryBookCacheableUseMyKeyGenerator(){
        System.out.println(bookService.queryBookCacheableUseMyKeyGenerator("3"));
    }

    @RequestMapping("/d")
    public void clearBook1All(){
        bookService.clearBook1All();
    }

    @RequestMapping("/e")
    public void updateBook(){
        String id = "2";
        String name = "name_" + Calendar.getInstance().getTimeInMillis();
        // 清空缓存
        bookService.clearBook1All();
        // 执行查询，并缓存数据
        System.out.println(bookService.queryBookCacheable(id));
        // 从缓存中获取数据
        System.out.println(bookService.queryBookCacheable(id));
        // 从缓存中清空刚刚存储的值 
        bookService.updateBook(id, name);
        // 现在缓存没有值，执行方法获取值，并缓存数据 
        System.out.println(bookService.queryBookCacheable(id));
    }

    @RequestMapping("/f")
    public void queryBookCachePut(){
        // 清空缓存
        bookService.clearBook1All();
        // 执行@CachePut注解方法，每次执行都不从缓存中获取，执行成功后会使用新的返回的替换缓存中的值
        System.out.println(bookService.queryBookCachePut("1"));
        System.out.println(bookService.queryBookCachePut("1"));
        // 执行@Cacheable注解方法，此时从缓存中获取值
        System.out.println(bookService.queryBookCacheable("1"));
    }

    @RequestMapping("/g")
    public void queryBookCacheableByBookQry(){
        BookQry qry = new BookQry();
        qry.setId("3");
        System.out.println(bookService.queryBookCacheableByBookQry(qry));
    }

    @RequestMapping("/h")
    public void queryBookCacheableWithCondition(){
        System.out.println(bookService.queryBookCacheableWithCondition("3"));
        System.out.println(bookService.queryBookCacheableWithCondition("3"));
        System.out.println(bookService.queryBookCacheableWithCondition("2"));
        System.out.println(bookService.queryBookCacheableWithCondition("2"));
    }

    @RequestMapping("/i")
    public void queryBookCacheableWithUnless(){
        System.out.println(bookService.queryBookCacheableWithUnless("3"));
        System.out.println(bookService.queryBookCacheableWithUnless("3"));
        System.out.println(bookService.queryBookCacheableWithUnless("2"));
        System.out.println(bookService.queryBookCacheableWithUnless("2"));
    }

    @RequestMapping("/k")
    public void bookService2_queryBookCacheable(){
        System.out.println(bookService2.queryBookCacheable("1"));
    }

    @RequestMapping("/m")
    public void bookService2_queryBookCacheable2(){
        System.out.println(bookService2.queryBookCacheable2("2"));
    }
}
