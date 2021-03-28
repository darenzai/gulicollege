package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;


    //TODO 完善条件查询带分页
    @GetMapping("getCourseList")
    public R getCourseList(){
        List<EduCourse> list=courseService.list(null);
        return R.ok().data("list",list);
    }

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo) {
        //返回添加之后课程id，为了后面添加大纲使用
        System.out.println("添加章节");
        Long id = courseService.saveCourseInfo(courseInfo);
        return R.ok().data("courseId",id);
    }
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable Long courseId){
       CourseInfo courseInfo= courseService.getCourseInfo(courseId);
       return  R.ok().data("courseInfoVo",courseInfo);
    }
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfo courseInfo ){
        courseService.updateCourseInfo(courseInfo);
        return R.ok();
    }

    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublicCourseInfo(@PathVariable Long id){
        CoursePublishVo coursePublishVo=courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }

    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable Long id){
        System.out.println("调用了");
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return  R.ok();
    }
    @DeleteMapping("delete/{courseId}")
    public R deleteCourse(@PathVariable Long courseId){
        courseService.removeCourse(courseId);
        System.out.println("删除方法");
        return R.ok();
    }


}

