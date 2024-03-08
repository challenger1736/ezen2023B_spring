package ezenweb.model.dao;

import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int getBoardSize(int bcno, String keyString, String keywordString){
        try{
            System.out.println("bcno = " + bcno + ", keyString = " + keyString + ", keywordString = " + keywordString);
            String sql= "select count(*) from board b inner join member m on b.mno = m.no ";
            // ======================= 1. 만약에 카테고리 조건이 있으면 where 추가
            if(bcno>0){sql += " where b.bcno = "+bcno;}
            // 2. 만약 검색 있을 때
            if(!keywordString.isEmpty()){
                System.out.println("★검색 키워드가 존재");
                if(bcno>0){sql += " and ";} // 카테고리 있을때. and로 연결
                else{sql += " where ";} // 카테고리 없을 때 where로 연결
                sql += keyString+" like '%" + keywordString +"%'";
            }

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
    public List<BoardDto> doGetBoardViewList(int startRow, int pageBoardSize, int bcno, String keyString, String keywordString){
        BoardDto boardDto = null;
        List<BoardDto> list = new ArrayList<>();
        System.out.println("BoardDao.doGetBoardViewList");
        try{
//            System.out.println("startRow = " + startRow + ", pageBoardSize = " + pageBoardSize + ", bcno = " + bcno + ", keyString = " + keyString + ", keywordString = " + keywordString);
            String sql = "select * from board b inner join member m on b.mno = m.no ";
            if(bcno>0){sql+="where b.bcno="+bcno;};
            if(!keywordString.isEmpty()){
                System.out.println("★검색 키워드가 존재");
                if(bcno>0){sql += " and ";} // 카테고리 있을때. and로 연결
                else{sql += " where ";} // 카테고리 없을 때 where로 연결
                sql += keyString+" like '%" + keywordString +"%' ";
            }
            sql+=" order by b.bdate desc limit ?,?";
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
    // 3-2. 개별 글 출력시 조회수 증가
    public void boardViewIncrease(long bno){
        try{
            String sql = "update board set bview = bview+1 where bno = " +bno;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    // 3-1. 개별 글 출력 호출        /board/view.do GET -- 호출이니까 // 게시물 번호 필요

    public BoardDto doGetBoardView( long bno){
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
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardDao.doUpdateBoard");
        try{
            String sql = "update board set btitle=?, bcontent =?, bcno=?, bfile =? where bno =? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.setLong(3, boardDto.getBcno());
            ps.setString(4, boardDto.getBfile());
            ps.setLong(5, boardDto.getBno());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 5. 글 삭제 처리           /board/delete.do    DELETE      // 게시물 번호 필요
    public boolean doDeleteBoard(long bno){ // 얘는 쿼리스트링 입니다요잇 = @RequestParam
        System.out.println("BoardDao.doDeleteBoard");
        try{
            String sql = "delete from board where bno = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, bno);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean boardWriterAuth(long bno, String mid){
        try{
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno =? and m.id =?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,bno);
            ps.setString(2,mid);
            rs= ps.executeQuery();
            if(rs.next()){return true;}
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

    // 댓글 등록
    public boolean postReplyWrite( Map<String, String> map){
        System.out.println("BoardController.postReplyWrite");
        try {
            String sql = "insert into breply(brcontent, brindex, mno, bno) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,map.get("brcontent"));
            ps.setString(2,map.get("brindex"));
            ps.setString(3,map.get("mno"));
            ps.setString(4,map.get("bno"));
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // 댓글 출력
    public List<Map<String,String>> getReplyDo( int bno ){
        System.out.println("BoardController.getReplyDo");
        List<Map<String,String>> list = new ArrayList<>();
        try{
            // 상위 댓글 먼저 출력
            String sql = "select * from breply where brindex = 0 and bno ="+bno;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while ( rs.next()){
                // map vs dto
                Map<String,String> map = new HashMap<>();
                map.put("brno", rs.getString("brno"));
                map.put("brcontent", rs.getString("brcontent"));
                map.put("brdate", rs.getString("brdate"));
                map.put("mno", rs.getString("mno"));
                list.add(map);

                //
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // =======================머스테치는 컨트롤에서 뷰 템플렛을 반환======================== //

    // 1. 글쓰기 페이지 이동        /board/write GET -- 메소드가 달라야 같은 URL로 실행 가능함.

    // 2. 게시판 페이지 이동        /board GET      -- 페이지 전환 (A 태그) GET 방식 페이지는 GET라고 보면 됨,

    // 3. 게시판 상세 페이지 이동     /board/view   GET

    // 4. 글 수정 페이지 이동       /board/update GET

}
