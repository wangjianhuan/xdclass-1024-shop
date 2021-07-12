package net.xdclass.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.OSSConfig;
import net.xdclass.service.FileService;
import net.xdclass.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author WJH
 * @class xdclass-1024-shop FileServiceImpl
 * @date 2021/7/12 下午8:00
 * @QQ 1151777592
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserImg(MultipartFile file) {

        String bucketName = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //创建oss对象
        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //获取原始文件名的后缀
        String originalFilename = file.getOriginalFilename();

        //JDK8的日期格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //  拼装路径，oss上存储的路径
        String floder = dtf.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newFileName = "user/" + floder + "/" + fileName + extension;

        try {
            PutObjectResult putObjectResult = oss.putObject(bucketName, newFileName, file.getInputStream());

            //拼装返回路径
            if (putObjectResult != null) {
                String imgUrl = "https://" + bucketName + "." + endpoint + "/" + newFileName;
                return imgUrl;
            }
        } catch (IOException e) {
            log.error("文件上传失败:{}", e);
        } finally {
            //oss关闭服务，不然会内存泄漏
            oss.shutdown();
        }

        return null;
    }
}
