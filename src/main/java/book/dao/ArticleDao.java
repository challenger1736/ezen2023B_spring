package book.dao;

import book.dto.ArticleForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public ArticleForm createArticle(ArticleForm form){
        System.out.println("ArticleDao.createArticle");
        System.out.println("form = " + form);
        try{
            String sql = "insert into article(title, content) values(?, ?)";
            // pk 값 뺴오는 방법 ,
            // 1. 기재 할 때 자동으로 생성된 키 호출 선언
            // 2. ps.getGet
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                System.out.println("방금 자동으로 생성된 pk(id):" + rs.getLong(1));
                ArticleForm articleForm = new ArticleForm();
                articleForm.setId(rs.getLong(1));
                articleForm.setTitle(form.getTitle());
                articleForm.setContent(form.getContent());
                return articleForm;
            }
        }catch (Exception e){
            System.out.println(e);
            log.debug(form.toString()); // 개발용(디버그) 로그
            log.info(form.toString()); // 운영용(정보,배포) 로그
            log.warn(form.toString()); // (경고)로그
            log.error(form.toString()); // (에러)로그
        }
        return new ArticleForm();
    }

    // --------------------------------------------------------//
    // 2. 개별 글 조회 : 매개변수, 조회할게시물번호(id) 반환: 조회한게시물정보 1개(DTO)
    public ArticleForm show(Long id){
        try{
            String sql = "select * from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){ // 1개니까 next()는 한번 처리.
                // Dto를 만들자
                ArticleForm form = new ArticleForm(rs.getLong(1), rs.getString(2), rs.getString(3));
                return form;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return new ArticleForm(); // null보다 빈거 보내는게 안전함.
    }

    // ----------------------------------------------------------//
    // 3. 전체 글 조회 : 매개변수 X, 리턴타입 : ArrayList
    public List<ArticleForm> index(){
        List<ArticleForm> list = new ArrayList<>();
        try{
            String sql = "select*from article order by id desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                // 1. 객체 만들기
                ArticleForm form = new ArticleForm(
                        rs.getLong(1), rs.getString(2), rs.getString(3)
                );

                // 2. 객체를 리스트에 넣기
                list.add(form);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    // ------------------------------------------------------- //
    // 4. id를 해당하는 게시물 정보 호출 : 매개변수 = id , 리턴 = dto(form)
    public ArticleForm findById(long id){
        try{
            String sql = "select*from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){

                return new ArticleForm(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
                // 2. 객체를 리스트에 넣기

        }catch (Exception e){
            System.out.println(e);
        }
        return null; // 없거나 오류면 null로 하겠다.
    }

    public ArticleForm update(ArticleForm form){
        System.out.println("ArticleDao.update");
        System.out.println("form = " + form);
        try{
            String sql = "update article set title = ?, content = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,form.getTitle());
            ps.setString(2,form.getContent());
            ps.setLong(3,form.getId());
            int count = ps.executeUpdate();
            if(count==1){
//                ArticleForm articleForm = new ArticleForm();
//                articleForm.setId(form.getId());
//                articleForm.setTitle(form.getTitle());
//                articleForm.setContent(form.getContent());
//                return articleForm;
                return form; // 이거만 해도되는데 뭐했지 ㅋㅋ
            }
        }catch (Exception e){
            System.out.println(e);
            log.debug(form.toString()); // 개발용(디버그) 로그
            log.info(form.toString()); // 운영용(정보,배포) 로그
            log.warn(form.toString()); // (경고)로그
            log.error(form.toString()); // (에러)로그
        }
        return new ArticleForm();

    }

    // 6. 삭제 처리 , 매개변수 : 삭제할id , 리턴 : T/F
    public boolean delete(long id){
        try{
            String sql = "delete from article where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


}
