package njucszxy.petsalon.owner;

import lombok.Data;
import njucszxy.petsalon.info.BaseEntity;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity {
    @Column(name = "name")
    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets;

    public List<Pet> getPets()
    {
        if(this.pets == null)
            this.pets = new HashSet<>();
        List<Pet> sortedPets = new ArrayList<>(this.pets);
        PropertyComparator.sort(sortedPets,new MutableSortDefinition("name",false,false));
        return Collections.unmodifiableList(sortedPets);
    }
    public void addPet(Pet pet)
    {
        if(pet.isNew())
        {
            if(this.pets == null)
                this.pets = new HashSet<>();
            this.pets.add(pet);
        }
        pet.setOwner(this);
    }
    public Pet getPet(String name)
    {
        return getPet(name,false);
    }
    public Pet getPet(String name,boolean ignoreNew)
    {
        name = name.toLowerCase();
        if(this.pets == null)
            this.pets = new HashSet<>();
        for(Pet pet:this.pets)
        {
            if(!ignoreNew || !pet.isNew())
            {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name))
                    return pet;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
