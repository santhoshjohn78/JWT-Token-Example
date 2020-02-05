package ehss.example.jwt.microservicescore.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements java.io.Serializable {
    private static final long serialVersionUID = 1234567L;

    private String key;
    private String secret;


}
