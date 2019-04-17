package njucszxy.petsalon.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Controller
public class OwnerController
{

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/owners/new")
    public String initCreationForm(String name)
    {
        Owner owner = new Owner();
        owner.setName(name);
        this.ownerRepository.save(owner);
        System.out.println(owner);
        return name;
        //Owner owner = new Owner();
        //model.put("owner", owner);
        //return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    /*
    @PostMapping("/owners/new")
    public void processCreationForm(String name)
    {
        Owner owner = new Owner();
        owner.setName(name);
        this.ownerRepository.save(owner);
        System.out.println(owner);
    }
    */
    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model)
    {
        model.put("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

        if (owner.getName() == null) {
            owner.setName("");
        }

        Collection<Owner> results = this.ownerRepository.findByName(owner.getName());
        if (results.isEmpty())
        {
            result.rejectValue("name", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1)
        {
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getID();
        } else {
            model.put("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model)
    {
        Owner owner = this.ownerRepository.findById(ownerId);
        model.addAttribute(owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId)
    {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setID(ownerId);
            this.ownerRepository.save(owner);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerRepository.findById(ownerId));
        return mav;
    }
}
