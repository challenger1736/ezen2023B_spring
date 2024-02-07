package example.day03.webMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// 데이터 접근 객체 : db에 접근하는 비즈니스 로직(sql)이 있는 역할
@Component // 빈 생성을 위해서 // AutoWired와 연계됨.
@Slf4j
public class TodoDao { // java 그대로
    // 1. 필드
    private Connection conn; // DB 연동 인터페이스
    private PreparedStatement ps; // SQL 실행, 매개변수 인터페이스
    private ResultSet rs;   // SQL 실행 결과를 연동, 호출해주는 인터페이스친구
    public TodoDao(){
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb", "root","1234");
            System.out.println("DB Connection Success");
        }catch (Exception e){
            log.error(e.toString());
        }
    }

    // 2. 생성자



    // 3. 메소드

    // 할 일 등록 함수
    public boolean doPost(TodoDto todoDto){
        try{
            String sql = "insert into todo(content,deadline) values(?,?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, todoDto.getContent());
            ps.setString(2, todoDto.getDeadline());

            int count = ps.executeUpdate();
            if(count == 1){return true;}


        }
        catch (Exception e){
            log.error(e.toString());
        }
        return false;
    }

    // 할 일 목록 출력 함수
    public ArrayList<TodoDto> doGet(){
        try{
            // 1. SQL 작성
            String sql = "select * from todo";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            // 4. SQL 실행
            rs = ps.executeQuery();
            // 5. SQL 실행 결과
            ArrayList<TodoDto> list = new ArrayList<>();
            while (rs.next()){
                //next() 레코드 이동
                //레코드 1개당 dto  1개
                TodoDto todoDto = new TodoDto();
                todoDto.setId(rs.getInt("id"));
                todoDto.setContent(rs.getString("content"));
                todoDto.setDeadline(rs.getString("deadline"));
                todoDto.setState(rs.getBoolean("state"));
                list.add(todoDto);
            }
            return list;

        }
        catch (Exception e){
            log.error(e.toString());
        }
        return null;
    }

    public boolean doPut(TodoDto todoDto){
        try{
            // 1. SQL 작성
            String sql = "update todo set state = ? where id =?";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setBoolean(1,todoDto.isState());
            ps.setInt(2, todoDto.getId());
            // 4. SQL 실행
            int count = ps.executeUpdate(); // update 실패해도 오류발생 X, sql 실행후 반영된 레코드수 반환
            // 5. SQL 실행 결과
            if(count==1){return true;}
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return false;
    }

    public boolean doDelete(int id){
        try{
            // 1. SQL 작성
            String sql = "delete from todo where id =?";
            // 2. SQL 기재
            ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 정의
            ps.setInt(1, id);
            // 4. SQL 실행
            int count = ps.executeUpdate(); // update 실패해도 오류발생 X, sql 실행후 반영된 레코드수 반환
            // 5. SQL 실행 결과
            if(count==1){return true;}
        }
        catch (Exception e){
            log.error(e.toString());
        }
        return false;
    }
}
