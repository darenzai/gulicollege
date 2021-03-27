package com.atguigu.educenter.controller;


import com.atguigu.commonutils.R;
import com.atguigu.educenter.entity.LoginVo;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.Vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */

@CrossOrigin
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

//    //登录
//    @PostMapping("/login")
//    public R login(@RequestBody UcenterMember member) {
//        //返回token，使用jwt生成
//        String token = ucenterMemberService.login(member);
//        return R.ok().data("token", token);
//
//
//    }

//    @PostMapping("register")
//    public R registerUser(@RequestBody RegisterVo registerVo){
//        ucenterMemberService.register(registerVo);
//        return  R.ok();
//    }

    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count=ucenterMemberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }



}