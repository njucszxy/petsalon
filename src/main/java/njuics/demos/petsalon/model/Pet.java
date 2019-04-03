package njuics.demos.petsalon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pet {

    private @Id @GeneratedValue Long id;
    private String name;
    
    Pet(){
        this.name = "";
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
	Pet(String name) {
		this.name = name;
    }
    
    public String toString(){
        return "Pet: " + this.name;
    }
}
