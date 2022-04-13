package com.hygge.vblog.common.util;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: hygge
 * @email: zeng164@outlook.com
 * @version: 1.0
 * @date: 2022/1/17 18:38
 * @description TODO    qq邮箱工具类
 */
@Slf4j
@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;


    /**
     * 发送文本邮件
     * @param to    收件人地址
     * @param from    发送人
     * @param subject   邮件主题
     * @param content   邮件内容
     * @param cc        抄送地址
     */
    public void sendSimpleMail(String to,String from, String subject, String content, String... cc){
        //构建一个邮箱对象
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件主题
        message.setSubject(subject);
        //设置邮件发送者
        message.setFrom(from);
        //设置邮件接收者
        message.setTo(to);
        //设置邮件发送日期
//        message.setSentDate(new Date());
        //设置邮件的正文
        message.setText(content);
        if (ArrayUtil.isNotEmpty(cc)) {
            message.setCc(cc);
        }
        try{
            mailSender.send(message);
            log.info("邮件发送成功");
        }catch (Exception e){
            log.error("发送邮件失败：" + to);
            log.error(e.getMessage());
        }

    }

    /**
     * 发送HTML邮件
     *
     * @param to      收件人地址
     * @param from    发送人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    public void sendHtmlMail(String to, String from, String subject, String content, String... cc) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        if (ArrayUtil.isNotEmpty(cc)) {
            helper.setCc(cc);
        }
        try{
            mailSender.send(message);
            log.info("邮件发送成功");
        }catch (Exception e){
            log.error("发送邮件失败：" + to);
            log.error(e.getMessage());
        }

    }




}
