package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;


//出错之后执行
@Component
public class VodFileDegradeFeignClient implements  VodClient {
    @Override
    public R removeVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoList) {
        return  R.error().message("删除多个视频出错了");
    }
}
