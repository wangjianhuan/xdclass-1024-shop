package net.xdclass.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author WJH
 * @date 2021/7/18 下午1:12
 * @QQ 1151777592
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BannerVO  {


    private Integer id;

    /**
     * 图片
     */
    private String img;

    /**
     * 跳转地址
     */
    private String url;

    /**
     * 权重
     */
    private Integer weight;


}