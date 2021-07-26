package net.xdclass.component;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.VO.PayInfoVO;
import net.xdclass.config.PayUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WJH
 * @date 2021/7/26 下午11:10
 * @QQ 1151777592
 */
@Slf4j
@Service
public class AlipayStrategy implements PayStrategy {

    @Autowired
    private PayUrlConfig payUrlConfig;


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
