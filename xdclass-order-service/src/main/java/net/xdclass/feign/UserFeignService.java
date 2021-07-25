package net.xdclass.feign;

import net.xdclass.utils.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author WJH
 * @date 2021/7/25 上午10:15
 * @QQ 1151777592
 */
@FeignClient(name = "xdclass-user-service")
public interface UserFeignService {

    /**
     * 查询用户地址 ，本身具有水平权限检验
     * @param addressId
     * @return
     */
    @GetMapping("/api/address/v1/find/{address_id}")
    JsonData detail(@PathVariable("address_id")long addressId);

}
