package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private Long id;
    private String title;

    private List<TwoSubejct> children=new ArrayList<>();
}
