package ezenweb.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuperDao {
    public Connection conn;
    public PreparedStatement ps;
    public ResultSet rs;
    public SuperDao(){
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
}
