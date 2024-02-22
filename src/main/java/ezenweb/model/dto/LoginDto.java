package ezenweb.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LoginDto {
    private int no;
    private String id;
    private String pw;
}
