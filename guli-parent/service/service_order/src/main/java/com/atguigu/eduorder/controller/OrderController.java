package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {


    @Autowired
    private OrderService orderService;



    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable Long courseId, HttpServletRequest request){

        String courseId1=String.valueOf(courseId);
        String orderNo=orderService.createOrders(courseId1);


        return R.ok().data("orderId",orderNo);
    }

}

