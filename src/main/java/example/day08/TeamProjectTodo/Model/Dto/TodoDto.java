package example.day08.TeamProjectTodo.Model.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class TodoDto {

    private int id;
    private String content;
    private boolean tcondition;
    private String tdate;


}
