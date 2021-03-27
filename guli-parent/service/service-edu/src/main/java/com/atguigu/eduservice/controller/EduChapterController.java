package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.handler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;


    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable Long courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);

        System.out.println(list);
        return R.ok().data("allChapterVideo", list);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        System.out.println("添加章节");
        chapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable Long chapterId) {


        EduChapter eduChapter = chapterService.getById(chapterId);

        return R.ok().data("chapter",eduChapter);

    }
    @PostMapping("updateChapter")
    public  R updateChpater(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return  R.ok();
    }
    //删除的方法
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable Long chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }

}

