package example.day08.boardTest.Model.Dao;

import example.day08.boardTest.Model.Dto.BoardDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao(){
        try {
            //1.MYSQL 회사의 JDBC관련된 객체를 JVM에 로딩한다
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Driver 클래스(인터페이스 구현체)를 불러와줌

            //2.연동된 결과의 객체를 connetction 인터페이스에 대입한다
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day08", "root", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // 1. 저장
    public boolean create(BoardDto boardDto) {
        System.out.println("BoardDao.create");
        System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board(bcontent , bwriter, bpw) values (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBcontent());
            ps.setString(2,boardDto.getBwriter());
            ps.setString(3,boardDto.getBpw());
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 2. 전체 호출
    public List<BoardDto> read(){
        System.out.println("BoardDao.read");
        List<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select*from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new BoardDto(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        }catch (Exception e){

        }

        return list;
    }
    // 3. 수정
    public boolean update(BoardDto boardDto){
        System.out.println("BoardDao.update");
        System.out.println("boardDto = " + boardDto);
        try{
            String sql = "update board set bcontent = ? where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBcontent());
            ps.setInt(2, boardDto.getBno());
            ps.executeUpdate();
            return true;
        }catch (Exception e){

        }
        return false;
    }
    // 4. 삭제
    public boolean delete(int bno){
        System.out.println("BoardDao.delete");
        System.out.println("bno = " + bno);

        try{
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ps.executeUpdate();
            return true;
        }catch (Exception e){

        }
        return false;
    }

    // 5. 게시물 번호에 따른 입력받은 패스워드 검증 // Dao가 Dao에게 하지말기. // Controller 왔다갔다 패스워드 검증 + 삭제 수정
    public boolean confirmPassword(int bno, String bpw){
        try{
            String sql = "select*from board where bno = ? and bpw = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bno);
            ps.setString(2, bpw);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


}
