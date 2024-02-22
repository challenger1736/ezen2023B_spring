package ezenweb.model.dao;


import ezenweb.model.dto.MemberDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    //싱글톤
    private static MemberDao memberDao = new MemberDao();

    private MemberDao() {
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

    public static MemberDao getInstance(){ return  memberDao;}

    public boolean doPostSignup(MemberDto memberDto){
        try{
        String sql = "insert into member(id, pw, name, email, phone, img) values(?,?,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        } catch (Exception e){

        }
        return false;
    }


}
