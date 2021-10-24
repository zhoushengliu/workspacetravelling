package com.atguigu;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

/**
 * TestQiNiu
 *
 * @Author: 马伟奇
 * @CreateTime: 2021-07-28
 * @Description:
 */
public class TestQiNiu {

    @Test
    public void test02() throws Exception{
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        String accessKey = "dzpXBDSa3musX6U7Nq8v0fzv921stt-NnRLGhACK";
        String secretKey = "AB90WSgUo32gY87jOyOW2zVH97fz9wT9JWCpKEm-";
        String bucket = "java-0422";
        // 删除图片
        String key = "FoMAocn5A-LmDoDv6TqrVY2iPfUK";
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (Exception ex) {
            //如果遇到异常，说明删除失败

        }
    }


    /**
     * 测试上传图片到服务器
     */
    @Test
    public void test01() throws Exception{
        //Configuration：表示配置类，全注解
        // Zone.zone2():表示华南服务器
        Configuration cfg = new Configuration(Zone.zone2());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        // 注册的时候，七牛云会进行分配，秘钥
        String accessKey = "dzpXBDSa3musX6U7Nq8v0fzv921stt-NnRLGhACK";
        String secretKey = "AB90WSgUo32gY87jOyOW2zVH97fz9wT9JWCpKEm-";
        // 空间名称，类似QQ空间的相册名称
        String bucket = "java-0422";
//如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:\\img\\abc.jpg";
//默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        // 授权
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (Exception ex2) {

        }
    }
}