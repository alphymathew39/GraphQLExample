package alp.example.GraphQLExample.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    private int id;
    private String name;
    private  String title;
    private String director;
    private String[] actors;

}
