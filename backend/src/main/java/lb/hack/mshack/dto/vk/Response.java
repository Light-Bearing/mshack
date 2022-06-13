package lb.hack.mshack.dto.vk;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private final static long serialVersionUID = 5711450622635308L;
    private Integer count;
}
