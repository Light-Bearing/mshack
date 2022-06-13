package lb.hack.mshack.dto.vk;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Channel {
    private int id;
    private String name;
    /**
     * тип канала
     */
    private String type;
    /**
     * охват, вовлеченность
     */
    private Integer involvement;
    /**
     * Внутренний Id
     */
    private String innerId;
    /**
     * рост канала
     */
    private boolean up;
    /**
     * падение канала
     */
    private boolean down;

    /**
     * линк на страницу
     */
    private String link;

}
