package wsb2023.pogorzelski.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mail {

    private String recipient;

    private String title;

    private String content;


}