package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.handler.GuliException;
import com.atguigu.vod.Utils.ConstantVodUtils;
import com.atguigu.vod.Utils.InitVodCilent;
import com.atguigu.vod.service.VodService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {



    @Override
    public String uploadVideoAly(MultipartFile file) {
        if(file !=null){


        try {
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest("LTAI4G2QiAFRr173TQ863u5k", "vRuZtlfrRynaxsy7VuexDMGoSUh7yd",title, fileName,inputStream);
            /* 可指定分片上传时每个分片的大小，默认为1M字节 */

            /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/

    /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/


            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            String videoId = null;
            if (response.isSuccess()) {

                videoId = response.getVideoId();

            } else {
                /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
                videoId = response.getVideoId();
            }

            return videoId;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        }else{
            System.out.println("没有文件");
            return  null;
        }

    }

    @Override
    public void removeMoreAlyVideo(List videoList) {
        try{
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstantVodUtils.ACCESS_KET_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request=new DeleteVideoRequest();

            String videoIds= org.apache.commons.lang.StringUtils.join(videoList.toArray(),",");

            request.setVideoIds(videoIds);
            client.getAcsResponse(request);


        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"删除视频失败");
        }
    }



}
