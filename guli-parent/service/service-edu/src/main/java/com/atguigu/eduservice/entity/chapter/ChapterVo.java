package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ChapterVo {

    private Long id;

    private String title;

    private List<VideoVo> children= new ArrayList<>();
}
