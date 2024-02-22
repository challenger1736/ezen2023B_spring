package example.day08.boardTest.Model.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {

    int bno;
    String bcontent;
    String bwriter;
    String bpw;
}
