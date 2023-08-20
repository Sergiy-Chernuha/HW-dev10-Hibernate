package ua.goit.entyties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "planet")
@Data
@ToString
@NoArgsConstructor
public class Planet {
    @Id
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name = "name",nullable = false)
    private String name;
}
