package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor @AllArgsConstructor
@ToString @Getter @Setter
public class MemberDto {

    private int no;
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
//    private String img; // input type="text" 면 String 써라.

    private MultipartFile img; // 폼의 타입이 파일로 되어있으면, 멀티파트 타입으로 써라.( 멀티파트타입, 대용량을 뜻하는 것. )
    private String uuidFile; // uuid+file


}
