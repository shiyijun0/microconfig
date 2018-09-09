package cn.bdqn.controller;

import cn.bdqn.domain.StudentEntity;
import cn.bdqn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentServices;

    /**
     *新增数据
     * */
    @RequestMapping("/save1")
    public String save() {

        int row = studentServices.saveStudent();
        //判断结果
        if (row == -1) {
            return "新增失败";
        }
            else{
                return "新增成功";
            }

    }
        /**
         * 查询数据
         * */
        @RequestMapping("/query")
        public String query () {
            //查寻数据
            List list = studentServices.queryAllStudent();
            //组装数据
            List newlist = new ArrayList();
            //循环取出结果
            for (int i = 0; i < list.size(); i++) {
                //新建学生对象
                StudentEntity stu = (StudentEntity) list.get(i);
                //填充数据
                newlist.add(stu.getId());
                newlist.add(stu.getName());
                newlist.add(stu.getAge());
                newlist.add(stu.getSex());
                newlist.add(stu.getAddress());
            }
            //返回数据
            return newlist.toString();
        }


        /**
         * 更新数据
         * */
        @RequestMapping("/update")
        public String update () {
            //新建对象传递数据
            StudentEntity stu = new StudentEntity();
            stu.setId(2);
            stu.setName("尼古拉斯");
            stu.setAddress("东北");
            //执行更新操作
            int row = studentServices.updateStudent(stu);
            //判断结果
            if (row == -1) {
                return "更新失败";
            } else {
                return "更新成功";
            }
        }

        /**
         * 删除数据
         * */
        @RequestMapping("/delete")
        public String delete () {
            //初始化数据
            Integer id = 3;
            //执行删除
            int row = studentServices.deleteStudent(id);
            //判断结果
            if (row == -1) {
                return "删除失败";
            } else {
                return "删除成功";
            }
        }
    }
