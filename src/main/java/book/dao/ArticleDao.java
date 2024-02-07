package book.dao;

import book.dto.ArticleForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
@Slf4j
public class ArticleDao {
    //------------ JDBC DB 연동 ----------//
    // 1. DB연동 필요한 인터페이스(메모리x => 각 sql 회사별) 필드 선언
    private Connection conn;    // DB 연동 결과 객체를 연결, 기재된 SQL Statement 객체 반환
    private PreparedStatement ps; //작성된 sql을 가지고있고 실행 담당, 기재된 sql
    private ResultSet rs;       // select 여러개의 레코드를 호출하기 위한 연결

    public ArticleDao() {
        sqlconnection();

    }


    public void sqlconnection() {
        try{
            //1.MYSQL 회사의 JDBC관련된 객체를 JVM에 로딩한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Driver 클래스(인터페이스 구현체)를 불러와줌

            //2.연동된 결과의 객체를 connetction 인터페이스에 대입한다
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root", "1234");
        }catch(Exception e){
            System.out.println(e);
        }

    }
    //SQL 이벤트 //

    public boolean createArticle(ArticleForm form){
        System.out.println("ArticleDao.createArticle");
        System.out.println("form = " + form);
        try{
            String sql = "insert into article(title, content) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println(e);
            log.debug(form.toString()); // 개발용(디버그) 로그
            log.info(form.toString()); // 운영용(정보,배포) 로그
            log.warn(form.toString()); // (경고)로그
            log.error(form.toString()); // (에러)로그
        }
        return false;
    }

}
