package example.day08.boardTest.Controller;

import example.day08.boardTest.Model.Dao.BoardDao;
import example.day08.boardTest.Model.Dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// @RestController // 모두 다 데이터를 넘길 때
public class BoardController {

    @Autowired
    BoardDao boardDao;

    @PostMapping("/board/create")
    @ResponseBody
    // 1. 저장
    public boolean create(BoardDto boardDto) {
        System.out.println("BoardController.create");
        System.out.println("boardDto = " + boardDto);
        boolean result = boardDao.create(boardDto);
        return result;
    }

    @GetMapping("/board/read") // 만들고 API 로 리턴값들 테스트 가능.
    @ResponseBody
    // 2. 전체 호출
    public List<BoardDto> read(){
        System.out.println("BoardController.read");
        List<BoardDto> result = boardDao.read();
        return result;
    }

    @PostMapping("/board/update")
    @ResponseBody
    // 3. 수정
    public boolean update(BoardDto boardDto){
        System.out.println("BoardController.update");
        System.out.println("boardDto = " + boardDto);
        // 1. 패스워드 검증 요청
        if(boardDao.confirmPassword(boardDto.getBno(),boardDto.getBpw())){
            // 2. 수정 요청
            boolean result = boardDao.update(boardDto);
            System.out.println(boardDto);
            return result;
        }
        return false;
    }

    @GetMapping("/board/delete/{bno}/{bpw}")
    @ResponseBody
    // 4. 삭제
    public boolean delete(@PathVariable int bno, @PathVariable String bpw){
        System.out.println("BoardController.delete");
        System.out.println("bno = " + bno);
        // 1. 패스워드 검증 요청
        boolean result = boardDao.confirmPassword(bno,bpw);
        if(result){
            // 2. 삭제 요청
            result = boardDao.delete(bno);
        }
        return result;


    }
    // ======================== view Rest =========================== //
    // 1. HTML (STATIC) : http ://localhost/day08Board.html
    // 2. 뷰템플릿(머스테치, templates) : 컨트롤의 반환이 필요하다 (model)
    @GetMapping("board")
    public String boardIndex(){
        return "day08Board.html";
    }


}
