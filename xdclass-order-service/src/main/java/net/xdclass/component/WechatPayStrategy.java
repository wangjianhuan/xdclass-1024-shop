package net.xdclass.component;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.PayInfoVO;
import org.springframework.stereotype.Service;

/**
 * @author WJH
 * @date 2021/7/26 下午11:12
 * @QQ 1151777592
 */
@Slf4j
@Service
public class WechatPayStrategy implements PayStrategy {

    @Override
    public String unifiedorder(PayInfoVO payInfoVO) {


        return null;
    }

    @Override
    public String refund(PayInfoVO payInfoVO) {
        return null;
    }

    @Override
    public String queryPaySuccess(PayInfoVO payInfoVO) {
        return null;
    }
}
