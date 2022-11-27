package cn.edu.gxu.oisback.utils;

import cn.edu.gxu.oisback.domain.Examination;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FirewallUtil {
    /**
     * IP地址与比赛的映射路由
     * Map<客户端IP, 考试编号>
     */
    private static final Map<String, Integer> ClientRouter = new TreeMap<>();
    private static final ReadWriteLock CLIENT_ROUTER_READ_WRITE_LOCK = new ReentrantReadWriteLock();
    /**
     * 比赛与OJ的路由映射
     * Map<考试编号, 服务器IP>
     */
    private static final Map<Integer, String> ServerRouter = new TreeMap<>();
    private static final ReadWriteLock SERVER_ROUTER_READ_WRITE_LOCK = new ReentrantReadWriteLock();

    /**
     * 路由注册
     * @param clientIp 客户端IP
     * @param examination 考试信息
     */
    public static Boolean register(String clientIp, Examination examination) {
        // ClientRouter 加读锁
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().lock();
        if(ClientRouter.containsKey(clientIp)) {
            // IP已在线，重复注册
            return false;
        }
        // ClientRouter 释放读锁
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().unlock();
        // ServerRouter 加读锁
        SERVER_ROUTER_READ_WRITE_LOCK.readLock().lock();
        if(!ServerRouter.containsKey(examination.getId())) {
            // ServerRouter 释放读锁
            SERVER_ROUTER_READ_WRITE_LOCK.readLock().unlock();
            // ServerRouter 加写锁
            SERVER_ROUTER_READ_WRITE_LOCK.writeLock().lock();
            ServerRouter.put(examination.getId(), examination.getServerRouter());
            // ServerRouter 释放写锁
            SERVER_ROUTER_READ_WRITE_LOCK.writeLock().unlock();
        } else {
            // ServerRouter 释放读锁
            SERVER_ROUTER_READ_WRITE_LOCK.readLock().unlock();
        }
        // ClientRouter 加写锁
        CLIENT_ROUTER_READ_WRITE_LOCK.writeLock().lock();
        ClientRouter.put(clientIp, examination.getId());
        // ClientRouter 释放写锁
        CLIENT_ROUTER_READ_WRITE_LOCK.writeLock().unlock();
        return true;
    }

    /**
     * 取消注册记录
     * @param clientIp 客户端IP
     */
    public static void unRegister(String clientIp) {
        // ClientRouter 加写锁
        CLIENT_ROUTER_READ_WRITE_LOCK.writeLock().lock();
        ClientRouter.remove(clientIp);
        // ClientRouter 释放写锁
        CLIENT_ROUTER_READ_WRITE_LOCK.writeLock().unlock();
    }

    /**
     * 查询客户端映射路由
     * @param clientIp 客户端IP
     * @return OJ实际IP
     */
    public static String queryClientRouter(String clientIp) {
        // ClientRouter 加读锁
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().lock();
        if(!ClientRouter.containsKey(clientIp)) {
            // ClientRouter 释放读锁
            CLIENT_ROUTER_READ_WRITE_LOCK.readLock().unlock();
            return null;
        }
        Integer examId = ClientRouter.get(clientIp);
        // ClientRouter 释放读锁
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().unlock();
        // ServerRouter 加读锁
        SERVER_ROUTER_READ_WRITE_LOCK.readLock().lock();
        String serverRouter = ServerRouter.get(examId);
        // ServerRouter 释放读锁
        SERVER_ROUTER_READ_WRITE_LOCK.readLock().unlock();
        return serverRouter;
    }

    /**
     * 查询IP是否活跃
     * @param clientIp 客户端IP
     * @return
     */
    public static Boolean isActive(String clientIp) {
        boolean active = false;
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().lock();
        if(ClientRouter.containsKey(clientIp)) {
            active = true;
        }
        CLIENT_ROUTER_READ_WRITE_LOCK.readLock().unlock();
        return active;
    }
}
