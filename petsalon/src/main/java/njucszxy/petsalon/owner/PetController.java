package njucszxy.petsalon.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    public PetController(PetRepository petRepository,OwnerRepository ownerRepository)
    {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @ModelAttribute("types")
    public Collection<PetType> getPetTypes()
    {
        return this.petRepository.findPetTypes();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") int ownerId)
    {
        return this.ownerRepository.findById(ownerId);
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner)
    {
        //System.out.println("here");
        Pet pet = new Pet();
        owner.addPet(pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result)
    {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
            result.rejectValue("name", "duplicate", "already exists");
        owner.addPet(pet);
        if (result.hasErrors())
        {
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            this.petRepository.save(pet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model)
    {
        Pet pet = this.petRepository.findById(petId);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model)
    {
        if (result.hasErrors())
        {
            pet.setOwner(owner);
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.addPet(pet);
            this.petRepository.save(pet);
            return "redirect:/owners/{ownerId}";
        }
    }
}
