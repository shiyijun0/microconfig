package cn.bdqn.domain;

import lombok.Data;

import javax.persistence.*;

@Table(name = "student")
@Entity
@Data
public class StudentEntity {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别
    private String sex;
    //住址
    private String address;
    //是否逻辑删除(0:未删除，1:已删除)
    private Integer isDelete;
}
