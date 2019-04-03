package njuics.demos.petsalon.model;

public class Pet {

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
