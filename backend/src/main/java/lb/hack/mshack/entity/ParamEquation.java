package lb.hack.mshack.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "param")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamEquation extends BaseEntity {
    private String name;
    private String title;
    private String type;
    @Column(name = "equation_id")
    private Long equationId;
}
