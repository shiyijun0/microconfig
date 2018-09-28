package com.lilin.microconfig.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyExample implements InvocationHandler {
    private Object object;//真实对象
    //实现逻辑关系
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法：*****在调度真实对象之前的服务");
        Object object=method.invoke(this.object,args);
        System.out.println("在调度真实对象之后服务");
        return object;
    }
    //绑定真实对象与代理对象代理关系，并返回代理对象
    public Object bind(Object object){
        this.object=object;
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) throws Exception {
        Class cal=JdkProxyExample.class;
        JdkProxyExample jdkProxyExample=(JdkProxyExample) cal.newInstance();

   // StduentService stduentService=(StduentService) jdkProxyExample.bind(new StudentServiceImpl());//绑定真实对象与代理对象关系并返回代理对象
        StduentService stduentService= (StduentService)Proxy.newProxyInstance(new StudentServiceImpl().getClass().getClassLoader(),new StudentServiceImpl().getClass().getInterfaces(),jdkProxyExample);
        jdkProxyExample.object=new StudentServiceImpl();
        System.out.println(stduentService.get("的地方",78));
    }
}
