package ezenweb.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService { // SMTP란? Simple Mail Tranfer Protocol의 약자로 메일 메세지관련 프로토콜

    // SMTP : 간이 우편 전송 프로토콜 (메일 전송)
        // - 자바에서 메일보내기(네이버 계정 / 2차 비밀번호)
        // 1. SMTP 라이브러리 필요. 디펜던시 받아오기(스프링 io)
        // 2. Dependencies : Java Mail Sender
            // implementation 'org.springframework.book:spring-boot-starter-mail'
        // 3. 이메일 전송
            // 1. 이메일 내용을 구성 => 구성 포맷 : MIME (SMTP 사용할때 사용되는 포맷)

        // 4. 보내는 사람 이메일 인증
            // application.properties


    /*

        자바               네이버            받는사람 도메인(회사 kakao)
             --smtp-->
     내 네이버 아이디가 있는지 인증
                                  --smtp-->
                                           받는 계정으로 보내버렸
                       보내는 사람 이메일
                       보내는 사람 비밀번호

                       git commit [X]
                       private 으로 깃을 해야함 mail 하려면,
     */



    // java(spring) 지원하는 smtp 객체 필요 (= javaMailSender)
    @Autowired
    private JavaMailSender smtp; // 다른 함수에서도 쓰려고.
    public void send( String toEmail , String subject, String content ){ // toEmail 보낼 사람 지정해주기 , subject 제목 , content 내용
        try{
            MimeMessage message = smtp.createMimeMessage();
            // 1. 메시지 구성
            // MimeMessageHelper(mime객체, 첨부파일여부, 인코딩타입("utf-8")); : 부가적인 내용들을 넣을 수 있다.
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true,"utf-8");
            // 2. 메시지 보내는 사람
            mimeMessageHelper.setFrom("challenge173@naver.com"); // 관리자 이메일(회사 이메일 느낌)
            // 3. 메시지 받는 사람
            mimeMessageHelper.setTo(toEmail); // 클라이언트(회원) 이메일 (매개변수)
            // 4. 메시지 제목
            mimeMessageHelper.setSubject(subject); // (매개변수)
            mimeMessageHelper.setText(content); // (매개변수)
            // 메일 전송
            smtp.send(message);
        }catch (Exception e){
            System.out.println("이메일 전송 실패 = " + e);
        }


    }
}
