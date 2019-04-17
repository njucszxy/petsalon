package njucszxy.petsalon.info;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public boolean isNew()
    {
        return this.id == null;
    }

    public Integer getID()
    {
        return id;
    }
    public void setID(Integer id)
    {
        this.id = id;
    }
}
