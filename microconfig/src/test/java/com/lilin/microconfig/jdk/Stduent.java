package com.lilin.microconfig.jdk;

public class Stduent {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Stduent(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Stduent() {
    }

    private String get(String weigth,Integer age){
        System.out.println(age+"********"+weigth);
        return weigth+"&&&&&&&&"+age;
    }
public String gt(){
    System.out.println("&&&&&&&:"+name+"age:"+age);
    return name;
}
    @Override
    public String toString() {
        return "Stduent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
