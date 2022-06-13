package lb.hack.mshack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedUser {
    private Long id;
    private Boolean checked;
}
