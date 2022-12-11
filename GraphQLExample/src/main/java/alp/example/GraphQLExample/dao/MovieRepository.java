package alp.example.GraphQLExample.dao;

import alp.example.GraphQLExample.Entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findByDirector(String director);
}
