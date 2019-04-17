package njucszxy.petsalon.owner;

import lombok.Data;
import njucszxy.petsalon.info.BaseEntity;
import njucszxy.petsalon.service.Service;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType type;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "petID",fetch = FetchType.EAGER)
    private Set<Service> services = new LinkedHashSet<>();

    public PetType getType()
    {
        return this.type;
    }
    public void setType(PetType type)
    {
        this.type = type;
    }
    public Owner getOwner()
    {
        return this.owner;
    }
    protected void setOwner(Owner owner)
    {
        this.owner = owner;
    }
    public List<Service> getService()
    {
        if(this.services == null)
            this.services = new HashSet<>();
        List<Service> sortedServices = new ArrayList<>(this.services);
        PropertyComparator.sort(sortedServices, new MutableSortDefinition("date",false,false));
        return Collections.unmodifiableList(sortedServices);
    }

    public void addService(Service service)
    {
        if(this.services == null)
            this.services = new HashSet<>();
        this.services.add(service);
        service.setPetID(this.getID());
    }
    public String getName()
    {
        return this.name;
    }
}
