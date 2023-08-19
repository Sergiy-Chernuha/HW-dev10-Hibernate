package ua.goit.entyties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planet")
@Data
@ToString
@NoArgsConstructor
public class Planet {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "toPlanet", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
    private Set<Ticket> arriveTicket = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "fromPlanet", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
    private Set<Ticket> leaveTicket = new HashSet<>();
}
