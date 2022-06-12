package lb.hack.mshack.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equation")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquationEntity extends BaseEntity {
    private String name;
    private String equation;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "equation_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<ParamEquation> paramEquationList = new ArrayList<>();
}
