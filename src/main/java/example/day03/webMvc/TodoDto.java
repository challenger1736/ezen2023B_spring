package example.day03.webMvc;

import lombok.*;

// 데이터 이동 객체 :
// TodoDto 타입 vs Map<String,String>
// List<TodoDto> vs List<Map<String,String>>
// member+todo : todoMemberDto  vs Map<String,String>
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto { // java 그대로
    // 1. ㅍㄷ(Dto일 사용할 경우 db필드와 일치하고 추가적으로 추가가능)
    private int id;
    private String content;

    private String deadline ;
    private boolean state;

}
