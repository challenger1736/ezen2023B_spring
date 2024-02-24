package test.testmvc1.test1model.test1dao;


import org.springframework.stereotype.Component;
import test.testmvc1.test1model.test1dto.Test1Dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class Test1Dao {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public Test1Dao() { // 정확하게 왜이렇게 바로 연결ㅇ 나하고 함수 하나를 더 만드는지는 모르겠음.
        sqlconnection();
    }

    public void sqlconnection() {
        try{
            //1.MYSQL 회사의 JDBC관련된 객체를 JVM에 로딩한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Driver 클래스(인터페이스 구현체)를 불러와줌
            //2.연동된 결과의 객체를 connetction 인터페이스에 대입한다
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testmvc1", "root", "1234");
        }catch(Exception e){
            System.out.println(e);
        }

    }


    public List<Test1Dto> contentsList(){
        List<Test1Dto> list = new ArrayList<>();
        try{
            String sql = "select*from board order by bno desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                // 1. 객체 만들기
                Test1Dto test1Dto = new Test1Dto();
                test1Dto.setBno(rs.getInt("bno"));
                test1Dto.setBtitle(rs.getString("btitle"));
                test1Dto.setBcontent(rs.getString("bcontent"));
                test1Dto.setBwriter(rs.getString("bwriter"));
                test1Dto.setBview(rs.getInt("bview"));
                test1Dto.setBpw(rs.getString("bpw"));
                // 2. 객체를 리스트에 넣기
                list.add(test1Dto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    public void contentcreate(Test1Dto dto){

    }
}


