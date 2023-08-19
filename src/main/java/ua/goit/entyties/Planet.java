package ua.goit.entyties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "planet")
@Data
@ToString
public class Planet {
    @Id
    private String id;

    @Column(length = 500)
    private String name;
}
