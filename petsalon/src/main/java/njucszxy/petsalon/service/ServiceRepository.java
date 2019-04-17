package njucszxy.petsalon.service;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ServiceRepository extends Repository<Service,Integer> {
    void save(Service service) throws DataAccessException;
    List<Service> findByPetID(Integer petID);
}
