package ua.goit.entyties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "client")
@Data
@ToString
public class Client {
    @Id
    private Integer id;

    @Column(length = 200)
    private String name;
}
