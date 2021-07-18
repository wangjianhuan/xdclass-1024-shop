package net.xdclass.service;

import net.xdclass.VO.BannerVO;

import java.util.List;

/**
 * @author WJH
 * @date 2021/7/18 下午1:11
 * @QQ 1151777592
 */
public interface BannerService {

    /**
     * 轮播图列表
     * @return
     */
    List<BannerVO> list();
}
