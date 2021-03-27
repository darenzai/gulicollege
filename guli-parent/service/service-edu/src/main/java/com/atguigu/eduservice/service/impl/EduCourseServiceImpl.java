package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.frontvo.CourseFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.handler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-02-28
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService chapterService;

    public Long saveCourseInfo(CourseInfo courseInfoVo){
        System.out.println("courseInfoVo.getSubjectId()"+courseInfoVo.getSubjectId());
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert=baseMapper.insert(eduCourse);
        if(insert==0){
            throw  new GuliException(2001,"添加课程信息失败");
        }
        EduCourseDescription courseDescription=new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());

        Long cid=eduCourse.getId();
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);


        return eduCourse.getId();

    }

    @Override
    public CourseInfo getCourseInfo(Long courseId) {
        System.out.println("courseId=="+courseId);
        EduCourse eduCourse =baseMapper.selectById(courseId);
        System.out.println("123123131232131231231231231231231232312");
        CourseInfo courseInfo=new CourseInfo();


//        courseInfo.setId(eduCourse.getId());
//        courseInfo.setSubjectParentId(eduCourse.getSubjectParentId());
//        courseInfo.setTeacherId(eduCourse.getTeacherId());
//        courseInfo.setCover(eduCourse.getCover());
//        courseInfo.setPrice(eduCourse.getPrice());
//        courseInfo.setLessonNum(eduCourse.getLessonNum());
//        courseInfo.setSubjectId(eduCourse.getSubjectId());

        BeanUtils.copyProperties(eduCourse,courseInfo);
        System.out.println("123123131232131231231231231231231232312");
        EduCourseDescription courseDescription=courseDescriptionService.getById(courseId);
        System.out.println("123123131232131231231231231231231232312");
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse=new EduCourse();


        BeanUtils.copyProperties(courseInfo,eduCourse);

        int update=baseMapper.updateById(eduCourse);
        if(update==0){
            throw new GuliException(2001,"修改课程信息失败");
        }

        EduCourseDescription description=new EduCourseDescription();
        description.setId(courseInfo.getId());
        description.setDescription(courseInfo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(Long id) {
        CoursePublishVo publishCourseInfo=baseMapper.getPublicCourseInfo(id);
        return  publishCourseInfo;
    }

    @Override
    public void removeCourse(Long courseId) {
        eduVideoService.removeVideoByCourseId(courseId);


        chapterService.removeChapterByCourseId(courseId);

        courseDescriptionService.removeById(courseId);
        System.out.println("123");
        int result=baseMapper.deleteById(courseId);
        if(result==0){
            throw new GuliException(2001,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();

        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())){
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())){
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageCourse,wrapper);

        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(Long courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }


}
