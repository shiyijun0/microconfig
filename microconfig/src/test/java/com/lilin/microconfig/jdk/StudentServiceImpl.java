package com.lilin.microconfig.jdk;

public class StudentServiceImpl  implements StduentService{
    @Override
    public String get(String name,Integer age) {
        System.out.println("大风刮过");
        return "name:"+name+"***age:"+age;
    }
}
