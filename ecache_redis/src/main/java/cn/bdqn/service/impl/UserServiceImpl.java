package cn.bdqn.service.impl;

import cn.bdqn.domain.User;
import cn.bdqn.mapper.UserMapper;
import cn.bdqn.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响


    @Override
    @CachePut(value = "syj",key = "#p0")
    @Transactional
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    //@Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    @Cacheable(value = "allsyj",key="#p0")
    public List<User> findAllUser(int pageNum, int pageSize) {

    /*    SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        PageHelper.startPage(1,10,true);  //第一页 每页显示10条
        Page<User> page=userMapper.findUserAll();
        PageHelper.startPage(1,-1,true);//不分页
        PageInfo<User> info=new PageInfo<User>(userMapper.findUserAll());//查询总条数*/
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAllUser();
    }

    //@Transactional(propagation = Propagation.SUPPORTS ,readOnly = true)
    public PageInfo findAllProduct(Integer pageNum, Integer pageSize){
        //1.在调用dao查询前，先调用PageHelper的静态方法
        PageHelper.startPage(pageNum, pageSize);
        //2.调用dao查询
        List<User> products = userMapper.selectAllUser();
        //3.将查询以构造方式存入到PageHelper为我们提供的分页工具类PageInfo，返回给控制层
        PageInfo pageInfo = new PageInfo(products);
        return pageInfo;
    };

    @Override
    @Cacheable(value = "syj",key = "#p0" )
    public User   find(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
