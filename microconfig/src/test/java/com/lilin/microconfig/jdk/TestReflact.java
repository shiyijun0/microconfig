package com.lilin.microconfig.jdk;

import java.lang.reflect.Method;

public class TestReflact {
    public static void main(String[] args) {
        //测试反射方法
        try {
            Class cal=Class.forName("com.lilin.microconfig.jdk.Stduent");
           /* Stduent s=(Stduent)cal.getConstructor(String.class,Integer.class).newInstance("小施",28);
            System.out.println("********:"+s.toString());*/
            //测试反射属性
            Stduent s=(Stduent)cal.newInstance();
            /*Field field=cal.getDeclaredField("name");
            field.setAccessible(true);
            field.set(s,"小苏");
            System.out.println("name:"+s.getName());*/
            //测试反射方法
            Method method=cal.getDeclaredMethod("get", String.class, Integer.class);
            method.setAccessible(true);
            method.invoke(s,"想的",345);
          //  System.out.println("*******:"+s.get());
          String sd=(String)  method.invoke(s,"东风风光",908);
            System.out.println(sd);
            System.out.println("**测试jdk动态代理*********");

        }catch (ClassNotFoundException c){

        }catch (Exception n){

        }


    }
}
