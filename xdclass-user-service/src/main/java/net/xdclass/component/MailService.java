package net.xdclass.component;

/**
 * @author WJH
 * @class xdclass-1024-shop MailService
 * @date 2021/7/11 下午10:02
 * @QQ 1151777592
 */
public interface MailService {

    /**
     * 发送邮件
     * @param to
     * @param Subject
     * @param content
     */
    void sendMail(String to , String Subject, String content);
}
