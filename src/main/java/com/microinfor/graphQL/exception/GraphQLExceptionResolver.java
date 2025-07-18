package com.microinfor.graphQL.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof UsuarioNotFoundException) {
            System.out.println("Entrou no ExceptionResolver!");
            return GraphqlErrorBuilder.newError(env)
                    .message("Usuário não encontrado: " + ex.getMessage())
                    .extensions(Map.of(
                            "timestamp", java.time.LocalDateTime.now().toString(),
                            "status", 404,
                            "error", "Not Found",
                            "path", env.getExecutionStepInfo().getPath().toString()
                    ))
                    .build();
        }
        return null;
    }
}
