package com.cck.cache;

import com.cck.utils.RedisUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

/**
 * 自定义mybatis二级缓存
 */
public class RedisCache implements Cache {
    //当前放入缓存的mapper的namespace
    private final String id;

    public RedisCache(String id) {
        this.id = id;
    }


    //返回cache唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //存放缓存数据
    @Override
    public void putObject(Object o, Object o1) {

        //将数据放入redis缓存
        RedisUtils.getRedisTemplate().opsForHash().put(id.toString(),getKeyToMD(o.toString()),o1);
        if (id.equals("com.cck.dao.userDao")){
            //设置当前业务的超时时间为1H
            RedisUtils.getRedisTemplate().expire(id.toString(),1, TimeUnit.DAYS);
        }
    }

    //获取缓存数据
    @Override
    public Object getObject(Object o) {

        //获取缓存中的数据
        return RedisUtils.getRedisTemplate().opsForHash().get(id.toString(), getKeyToMD(o.toString()));
    }

    //指定key删除缓存 mybatis保留方法
    @Override
    public Object removeObject(Object o) {
        return null;
    }

    //清空缓存 只要发生增删改默认执行
    @Override
    public void clear() {

        RedisUtils.getRedisTemplate().delete(id.toString());
    }

    //获取缓存中的key value数量
    @Override
    public int getSize() {
        return RedisUtils.getRedisTemplate().opsForHash().size(id.toString()).intValue();
    }

    //封装冗余代码

    //对key进行md5加密
    private String getKeyToMD(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
