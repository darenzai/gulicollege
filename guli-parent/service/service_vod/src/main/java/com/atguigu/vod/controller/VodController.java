package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.vod.Utils.ConstantVodUtils;
import com.atguigu.vod.Utils.InitVodCilent;
import com.atguigu.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uplodAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){
        String videoId=vodService.uploadVideoAly(file);
        return  R.ok().data("videoId",videoId);
    }


    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){

        try{
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KET_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request=new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();

        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }

    }

    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList")List<String> videoList){
        vodService.removeMoreAlyVideo(videoList);
        return  R.ok();
    }

}
