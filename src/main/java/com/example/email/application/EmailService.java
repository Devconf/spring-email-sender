package com.example.email.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender javaMailSender;

  private String setFrom = "devconf5296@gmail.com"; // 보내는 이메일

  String setTo = "wmf2fkrh@gmail.com"; // 받는 이메일

  String title = "메일테스트"; // 메일 제목(생략 가능)

  String content = "바보 멍충이~~"; // 메일 내용

  public void sendEmail() {
    try {
      String path = new ClassPathResource("매출액 업로드 첨부파일.xlsx").getURI().getPath().substring(1);

      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setFrom(this.setFrom);
      messageHelper.setTo(this.setTo);
      messageHelper.setSubject(this.title);
      messageHelper.setText(this.content);
      log.info(path);

      FileDataSource fds = new FileDataSource(path);

      String originalFileNm = "업로드 양식.xlsx"; // DB에 저장된 원본 파일명

      messageHelper.addAttachment(MimeUtility.encodeText(originalFileNm, "UTF-8", "B"), fds);

      javaMailSender.send(message);
    } catch (Exception e) {
      log.info(e.getMessage());
      log.info("메시지 전송 오류");
    }
  }
}
