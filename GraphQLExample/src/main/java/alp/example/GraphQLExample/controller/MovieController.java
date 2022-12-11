package alp.example.GraphQLExample.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import alp.example.GraphQLExample.Entity.Movie;
import alp.example.GraphQLExample.dao.MovieRepository;

import graphql.schema.DataFetcher;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository repository;

    @Value("classpath:movies.graphqls")
    private Resource schemaResource;

    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException {
        File schemaFile = schemaResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        DataFetcher<List<Movie>> fetcher1 = data -> {
            return (List<Movie>) repository.findAll();
        };

        DataFetcher<Movie> fetcher2 = data -> {
            return repository.findByDirector(data.getArgument("director"));
        };

        return RuntimeWiring.newRuntimeWiring().type("Query",
                        typeWriting -> typeWriting.dataFetcher("getAllMovie", fetcher1).dataFetcher("findMovie", fetcher2))
                .build();

    }

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody List<Movie> movies) {
        repository.saveAll(movies);
        return "record inserted " + movies.size();
    }

    @GetMapping("/findAllMovie")
    public List<Movie> getMovies() {
        return (List<Movie>) repository.findAll();
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @PostMapping("/getMovieByDirector")
    public ResponseEntity<Object> getMovieByDirector(@RequestBody String query) {
        ExecutionResult result = graphQL.execute(query);
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

}
