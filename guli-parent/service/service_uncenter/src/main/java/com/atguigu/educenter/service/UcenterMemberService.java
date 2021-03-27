package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.LoginVo;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.Vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {


    Integer countRegisterDay(String day);


}
