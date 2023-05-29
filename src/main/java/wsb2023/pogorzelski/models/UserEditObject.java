package wsb2023.pogorzelski.models;

import lombok.Data;

@Data
public class UserEditObject {
    String username;
    String password;
    String realName;
    String email;
    String newPassword;

}
