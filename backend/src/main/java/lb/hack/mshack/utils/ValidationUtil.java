package lb.hack.mshack.utils;


import lb.hack.mshack.entity.BaseEntity;
import lb.hack.mshack.error.IllegalRequestDataException;

public class ValidationUtil {
    public static void checkNew(BaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must be new (id = null");
        }
    }

    public static void assureIdConsistent(BaseEntity entity, long id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalRequestDataException(entity.getClass().getSimpleName() + " must has id=" + id);
        }
    }
}
