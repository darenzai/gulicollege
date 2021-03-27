package com.atguigu.educenter.service.impl;

import com.alibaba.nacos.client.naming.utils.StringUtils;
import com.atguigu.educenter.entity.Vo.RegisterVo;
import com.atguigu.servicebase.JwtUtils;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.educenter.utils.MD5;
import com.atguigu.servicebase.handler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {





    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //登录的方法
//
//    public String login(UcenterMember member) {
//        //获取登录手机号和密码
//        String mobile = member.getMobile();
//        String password = member.getPassword();
//
//        //手机号和密码非空判断
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
//            System.out.println(mobile+" === === "+password);
//            throw new GuliException(20001,"登录失败");
//        }
//
//        //判断手机号是否正确
//        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
//        wrapper.eq("mobile",mobile);
//        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
//        //判断查询对象是否为空
//        if(mobileMember == null) {//没有这个手机号
//            throw new GuliException(20001,"登录失败");
//        }
//
//        //判断密码
//        //因为存储到数据库密码肯定加密的
//        //把输入的密码进行加密，再和数据库密码进行比较
//        //加密方式 MD5
//        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
//            throw new GuliException(20001,"登录失败");
//        }
//
//        //判断用户是否禁用
//        if(mobileMember.getIsDisabled()) {
//            throw new GuliException(20001,"登录失败");
//        }
//
//        //登录成功
//        //生成token字符串，使用jwt工具类
//        System.out.println("生成token字符串，使用jwt工具类");
//        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
//
//        System.out.println("生成token字符串，使用jwt工具类");
//        return jwtToken;
//    }

//    @Override
//    public void register(RegisterVo registerVo) {
//        String code = registerVo.getCode();
//        String mobile = registerVo.getMobile();
//        String nickname = registerVo.getNickname();
//        String password = registerVo.getPassword();
//
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname)){
//            throw  new GuliException(2001,"手机号或用户名为空");
//        }



//    }

    @Override
    public Integer countRegisterDay(String day) {

        return baseMapper.countRegisterDay(day);
    }


}

