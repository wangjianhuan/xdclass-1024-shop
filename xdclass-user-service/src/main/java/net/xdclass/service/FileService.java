package net.xdclass.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author WJH
 * @class xdclass-1024-shop UserService
 * @date 2021/7/12 下午7:57
 * @QQ 1151777592
 */
public interface FileService {

    /**
     * 上传用户头像
     * @param file
     * @return
     */
    public String uploadUserImg(MultipartFile file);
}
