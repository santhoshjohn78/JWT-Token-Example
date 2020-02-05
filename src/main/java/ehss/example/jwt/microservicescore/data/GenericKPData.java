package ehss.example.jwt.microservicescore.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class GenericKPData implements Serializable {

    String id;

    Long systemTimeStamp;

    String text;


}
