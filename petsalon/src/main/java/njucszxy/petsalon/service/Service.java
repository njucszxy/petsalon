package njucszxy.petsalon.service;

import lombok.Data;
import njucszxy.petsalon.info.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "services")
public class Service extends BaseEntity {
    @Column(name = "service_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    @Column(name = "description")
    private String description;

    @Column(name = "petID")
    private Integer petID;

    @Column(name = "fee")
    private double fee;

    public Service()
    {
        this.date = LocalDate.now();
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getPetID()
    {
        return this.petID;
    }
    public void setPetID(Integer petID)
    {
        this.petID = petID;
    }
}
