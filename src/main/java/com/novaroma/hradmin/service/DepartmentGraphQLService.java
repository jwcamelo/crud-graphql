package com.novaroma.hradmin.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
@Service
public class DepartmentGraphQLService {
    @Value("classpath:employee.graphqls")
    private Resource resource;
    @Autowired
    private FetchAllDepartment fetchAllDepartment;
    @Autowired
    private FetchDepartmentById fetchDepartmentById;
    @Autowired
    private FetchSaveDepartment fetchSaveDepartment;
    @Autowired
    private FetchDeleteDepartment fetchDeleteDepartment;

    private GraphQL graphQL;

    @PostConstruct
    private void getSchema() throws IOException {
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(resource.getFile());
        RuntimeWiring runtimeWiring=RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring-> typeWiring
                        .dataFetcher("allDep",fetchAllDepartment)
                        .dataFetcher("depById",fetchDepartmentById))
                .type("Mutation", typeWiring-> typeWiring
                        .dataFetcher("saveDep", fetchSaveDepartment)
                        .dataFetcher("deleteDep", fetchDeleteDepartment)
                )
                .build();
        GraphQLSchema graphQLSchema=new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        graphQL=GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL(){
        return graphQL;
    }
}
