package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.handler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-22
 */

@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {


    @Autowired
    private EduTeacherService  teacherService;


    //查询讲师数据
    @GetMapping("findAll")
    @ApiOperation(value = "查询讲师数据")
    public R findAllTeacher(){
        List<EduTeacher> list=teacherService.list(null);


        return R.ok().data("item",list);

    }


    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable long id){
        teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true)@PathVariable Long page,@ApiParam(name = "limit",value = "每页记录数",required = true)@PathVariable Long limit){

        Page<EduTeacher> pageParam=new Page<>(page,limit);

        teacherService.page(pageParam,null);
        List<EduTeacher> records=pageParam.getRecords();
        long total=pageParam.getTotal();


        return R.ok().data("total",total).data("rows",records);
    }



    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher>  pageTeacher=new Page<>(current,limit);

        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();

        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);

        }
        if (!StringUtils.isEmpty(level) ) {
            wrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        wrapper.orderByDesc("gmt_create");

        teacherService.page(pageTeacher,wrapper);
        long total=pageTeacher.getTotal();

        List<EduTeacher> records=pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }

    @PostMapping("addTeacher")
    public R save(
            @ApiParam(name = "teacher",value ="讲师对象",required = true) @RequestBody EduTeacher eduTeacher
    ){
        Boolean save =teacherService.save(eduTeacher);
        if(save){
            return  R.ok();
        }else{
            R.error();
        }
        return  R.ok();
    }


    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable long id){

        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher")
    public R updateById(
            @RequestBody EduTeacher eduTeacher){

        boolean flag = teacherService.saveOrUpdate(eduTeacher);

        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}

