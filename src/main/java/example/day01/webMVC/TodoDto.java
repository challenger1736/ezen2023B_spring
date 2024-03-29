package example.day01.webMVC;
// 데이터 이동 객체 :
// TodoDto 타입 vs Map.txt<String,String>
// List<TodoDto> vs List<Map.txt<String,String>>
// member+todo : todoMemberDto  vs Map.txt<String,String>
public class TodoDto { // java 그대로
    // 1. ㅍㄷ(Dto일 사용할 경우 db필드와 일치하고 추가적으로 추가가능)
    private int id;
    private String content;

    private String deadline ;
    private boolean state;
    // 2. 생성자

    public TodoDto() {
    }

    public TodoDto(int id, String content, String deadline, boolean state) {
        this.id = id;
        this.content = content;
        this.deadline = deadline;
        this.state = state;
    }
    // 3. 메소드


    @Override
    public String toString() {
        return "TodoDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", deadline='" + deadline + '\'' +
                ", state=" + state +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
