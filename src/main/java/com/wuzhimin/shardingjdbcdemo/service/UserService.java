package com.wuzhimin.shardingjdbcdemo.service;

import com.wuzhimin.shardingjdbcdemo.dao.OrderMapper;
import com.wuzhimin.shardingjdbcdemo.dao.UserMapper;
import com.wuzhimin.shardingjdbcdemo.entity.Order;
import com.wuzhimin.shardingjdbcdemo.entity.User;
import com.wuzhimin.shardingjdbcdemo.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    public User getUser() throws Exception {

        IdWorker idWorker = IdWorker.getFlowIdWorkerInstance();

        for (Long i = 0L; i < 1000; i++) {
            Long id = idWorker.nextId();
            Order order = new Order();
            order.setOrderId(id);
            order.setOrderNo("123");
            System.out.println("第"+i+"个ID---{" + id + "}");
            orderMapper.insert(order);
        }

        return userMapper.selectByPrimaryKey(1);
    }


}
