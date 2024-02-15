package book.dto;

import lombok.*;

// 입력 폼 전용 DTO
    // 관례적으로 모든 객체 필드는 직접 접근 권장하지 않는다.
    // private, 안전성 보장, 캡슐화 특징, setter getter 또는 생성자
    // DTO는 어노테이션 필요 읎다.
    // 간단한 생성자 와 toString 게터세터 메소드는 롬복으로 리팩터링 가능함.
    // @ 어노테이션 : 컴파일시 해당 클래스/함수/필드에 (미리)정보 주입.
@AllArgsConstructor // 컴파일시 풀 생성자 자동으로 만들어줌. (lombok)
@NoArgsConstructor // 컴파일시 빈 생성자 자동으로 만들어줌.(lombok)
@ToString // 컴파일시 toString() 자동으로 만들어줌. (lombok)
@Getter // (lombok)
@Setter // (lombok)
//@Builder // 쓰면 편하다 하셨는데 뭔지는 아직 안알려줌. (lombok)
public class ArticleForm {
    private Long id;
    // 1. 필드 // 우린 필드만 잘 하면됨.
    private String title; // 입력받은 제목 필드
    private String content; // 입력받은 내용 필드


}

// 2. 생성자




// 3. 메소드

// 게터 세터 있어야 빈 생성자 안먹음
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

//    @Override
//    public String toString() { // 객체내 필드 값을 호출하는 함수.
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }