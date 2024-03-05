package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao extends SuperDao {



    // 1. 글쓰기 처리 [ 글쓰기 성공했을때 자동 생성된 글 번호 반환, 실패시 0 ]
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);
        try{
            String sql = "insert into board(btitle, bcontent, bfile, bcno, mno) values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.setString(3,boardDto.getBfile());
            ps.setLong(4,boardDto.getBcno());
            ps.setLong(5,boardDto.getMno());
            int count = ps.executeUpdate();
            if(count==1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){return rs.getLong(1);}
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        return 0;
    }
    // 2-2. 전체 게시물 수 호출
    public int getBoardSize(){
        try{
            String sql= "select count(*) from board;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){return rs.getInt(1);};
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // 2-1. 전체 글 출력 호출       /board.do   GET -- 호출 이니까    페이징처리, 검색 기능
    public List<BoardDto> doGetBoardViewList(int startRow, int pageBoardSize){
        BoardDto boardDto = null;
        List<BoardDto> list = new ArrayList<>();
        System.out.println("BoardDao.doGetBoardViewList");
        try{
            String sql = "select * from board b inner join member m on b.mno = m.no order by b.bdate desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
            rs = ps.executeQuery();
            while(rs.next()){
                boardDto = new BoardDto(rs.getLong("bno"), rs.getString("btitle"),
                        rs.getString("bcontent"),rs.getString("bfile"),
                        rs.getInt("bview"), rs.getString("bdate"),
                        rs.getLong("mno"), rs.getLong("bcno"), null,
                        rs.getString("id"), rs.getString("img")
                );
                list.add(boardDto);
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    // 3. 개별 글 출력 호출        /board/view.do GET -- 호출이니까 // 게시물 번호 필요

    public BoardDto doGetBoardView( int bno){
        System.out.println("BoardDao.doGetBoardView");
        BoardDto boardDto = null;
        try{
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            rs =ps.executeQuery();
            if(rs.next()){
                boardDto = new BoardDto(rs.getLong("bno"), rs.getString("btitle"),
                        rs.getString("bcontent"),rs.getString("bfile"),
                        rs.getInt("bview"), rs.getString("bdate"),
                        rs.getLong("mno"), rs.getLong("bcno"), null ,
                        rs.getString("id"), rs.getString("img")
                        );
            }
        }catch (Exception e){}
        return boardDto;
    }

    // 4. 글 수정 처리           /board/update.do   PUT          // Dto 필요

    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요

    // =======================머스테치는 컨트롤에서 뷰 템플렛을 반환======================== //

    // 1. 글쓰기 페이지 이동        /board/write GET -- 메소드가 달라야 같은 URL로 실행 가능함.

    // 2. 게시판 페이지 이동        /board GET      -- 페이지 전환 (A 태그) GET 방식 페이지는 GET라고 보면 됨,

    // 3. 게시판 상세 페이지 이동     /board/view   GET

    // 4. 글 수정 페이지 이동       /board/update GET

}