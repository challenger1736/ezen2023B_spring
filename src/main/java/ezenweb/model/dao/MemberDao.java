package ezenweb.model.dao;


import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

@Component
public class MemberDao extends SuperDao{


    //싱글톤
    private static MemberDao memberDao = new MemberDao();


    public static MemberDao getInstance(){ return  memberDao;}

    public boolean doPostSignup(MemberDto memberDto){ // 회원가입
        try{
        String sql = "insert into member(id, pw, name, email, phone, img) values(?,?,?,?,?,?);";
        ps = conn.prepareStatement(sql);
        ps.setString(1,memberDto.getId());
        ps.setString(2,memberDto.getPw());
        ps.setString(3,memberDto.getName());
        ps.setString(4,memberDto.getEmail());
        ps.setString(5,memberDto.getPhone());
//        ps.setString(6,memberDto.getImg()); // String 아니고 Multipart로 바꿔서 이렇게안됨
        ps.setString(6,memberDto.getUuidFile()); // String 으로 바꿨음, Multipartfile의 바이트를 저장할 수는 있겠는데 용량이 큼


        int count = ps.executeUpdate();
        if(count == 1){ return  true; }
        } catch (Exception e){

        }
        return false;
    }

    //로그인 기능
    public boolean doPostLogin(LoginDto loginDto){
        try{
            String sql = "select * from member where id =? and pw =?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginDto.getId());
            ps.setString(2,loginDto.getPw());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    // 3. 회원 정보 요청
    public MemberDto doGetLoginInfo(String id){
        MemberDto memberDto = null;
        try{
            String sql = "select * from member where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                memberDto = new MemberDto(
                    rs.getInt(1),
                    rs.getString(2),
                    null, //
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    null, // 첨부파일 필드 빼고
                    rs.getString(7)
                );
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return memberDto;
    }

    public boolean doGetFindIdCheck( String id){
        try{
            String sql = "select * from member where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            rs= ps.executeQuery();
            if(rs.next()){return true;}; // true면 아이디 있음.
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
