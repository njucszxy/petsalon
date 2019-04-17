package njucszxy.petsalon.service;

import njucszxy.petsalon.owner.Pet;
import njucszxy.petsalon.owner.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ServiceController {
    private final ServiceRepository serviceRepository;
    private final PetRepository petRepository;

    public ServiceController(ServiceRepository serviceRepository,PetRepository petRepository)
    {
        this.serviceRepository = serviceRepository;
        this.petRepository = petRepository;
    }

    @ModelAttribute("service")
    public Service loadPetWithService(@PathVariable("petId") int petId, Map<String, Object> model)
    {
        Pet pet = this.petRepository.findById(petId);
        model.put("pet", pet);
        Service service = new Service();
        pet.addService(service);
        return service;
    }

    @GetMapping("/owners/*/pets/{petId}/services/new")
    public String initNewServiceForm(@PathVariable("petId") int petId, Map<String, Object> model)
    {
        return "pets/createOrUpdateServiceForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/services/new")
    public String processNewServiceForm(@Valid Service service, BindingResult result)
    {
        if (result.hasErrors()) {
            return "pets/createOrUpdateServiceForm";
        } else {
            this.serviceRepository.save(service);
            return "redirect:/owners/{ownerId}";
        }
    }
}

