package com.kuang;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Jedis {
    static redis.clients.jedis.Jedis jedis;
    private String string;
    static {

        jedis = new redis.clients.jedis.Jedis("127.0.0.1", 6379);
        jedis.auth("ck.070601");
        System.out.println("jedis 链接成功。。");

    }

    public static void main(String[] args) {

        new Jedis().init();
    }

    // 存储字符串
    public void setString() {
        jedis.set("name", "张三");
        getString();
    }

    // 获取字符串
    public void getString() {
        string = jedis.get("name");
        System.out.println("获取到的名字：" + string);
        jedis.append("name", "我是拼接的");
        string = jedis.get("name");
        System.out.println("获取到的名字：" + string);
    }

    // 存储集合
    public void setSet() {
        jedis.sadd("set1", "1");
        jedis.sadd("set1", "12");
        jedis.sadd("set1", "13");
        jedis.sadd("set1", "14");
        jedis.sadd("set1", "15");
        jedis.sadd("set1", "1"); //不重复

        System.out.println(jedis.smembers("set1"));

    }

    // 存储列表
    public void setList() {
        //删除列表中的list
        jedis.del("list");
        List<String> list = new ArrayList<>();
        list.add("s1");
        list.add("s2");
        list.add("s3");
        list.add("s3");
        list.add("s3");
        //重新添加，可以重复
        list.forEach(l->{
            jedis.lpush("list", l);
        });

        //第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("list", 0, -1));

    }

    public  void setMap() {

        //key 不重复
        jedis.hset("map", "k", "k111");
        jedis.hset("map", "k2", "k222");
        jedis.hset("map", "k3", "k333");
        jedis.hset("map", "k4", "k111");
        jedis.hset("map", "k", "k111");
        jedis.hset("map", "k", "k666");
        //获取所有key
        System.out.println(jedis.hkeys("map"));
        //根据map key  获取值
        System.out.println(jedis.hget("map", "k"));

        //获取所有的map 中的key 对应的值
        Set<String> hkeys = jedis.hkeys("map");
        hkeys.forEach(keys->{
            System.err.println(jedis.hget("map", keys));;
        });

    }

    public  void init() {
        //String
        setString();
        //列表
        setList();
        //集合
        setSet();
        //hash
        setMap();

    }
}
