package ehss.example.jwt.microservicescore.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements  java.io.Serializable{

    private static final long serialVersionUID = 12345L;

    private String token;


}
