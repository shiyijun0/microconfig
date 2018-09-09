package cn.bdqn.zooker;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperLockSingle {
    private final static int COUNTS = 5;
    private final Log log = LogFactory.getLog(ZookeeperLockSingle.class);
    private ZooKeeper keeper;
    //zk链接是异步的，我们需要等待链接上zk才进行操作
    private CountDownLatch latch = new CountDownLatch(1);
    private ZookeeperLockSingle (){
        try {
           // this.keeper = new ZooKeeper("192.168.5.112:2181,192.168.5.113:2181", 50000,new MyWater());
            this.keeper = new ZooKeeper("127.0.0.1:2181", 50000,new MyWater());
            log.error("等待链接zk...");
            latch.await();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    private class MyWater implements Watcher{

        @Override
        public void process(WatchedEvent arg0) {
            //状态是链接
            if (arg0.getState() == KeeperState.SyncConnected) {
                log.error("zk 已经链接成功");
                latch.countDown();
            }
        }
    }

    private static class GetZookeeperLockSingle{
        private static ZookeeperLockSingle zklockSingle = null;
        static {
            zklockSingle =  new ZookeeperLockSingle();
        }

        private static ZookeeperLockSingle getZookeeperLock(){
            return zklockSingle;
        }
    }

    public static ZookeeperLockSingle getSingleZKLock(){
        return GetZookeeperLockSingle.getZookeeperLock();
    }

    /**
     * 循环获取分布式锁
     * @Function:  ZookeeperLockSingle.java
     * @Description:
     *
     * @param productid
     * @return
     * @return boolean
     * @version: v1.0.0
     */
    public boolean acquireDistrbutedLock(Long productid){
        String path = "/product-lock-"+productid;
        boolean flag = false;
        int counts = 1;
        //这里一直等带获取锁是不是不太好哦
        while (true) {
            if (counts > COUNTS) {
                break;
            }
            try {
                Thread.sleep(200);
                String create = this.keeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(create);
                log.info("创建："+path+"成功");
                flag = true;
                break;
            } catch (Exception e) {
                counts++;
                log.error("锁："+path+"创建失败！！！"+"正在进行第"+counts+"次尝试！");
                continue;
            }
        }

        return flag;
    }

    /**
     * 释放分布式锁
     * @Function:  ZookeeperLockSingle.java
     * @Description:
     *
     * @param productid
     * @return void
     * @version: v1.0.0
     */
    public void releaseDistrbuteProductLock(long productid) throws Exception{
        String path = "/product-lock-"+productid;
        try {
            this.keeper.delete(path, -1);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

}

