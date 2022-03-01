package com.spark.cms.exception;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors){
        return errors.stream().map(this::getError).collect(Collectors.toList());
    }

    private GraphQLError getError(GraphQLError graphQLError) {
        if(graphQLError instanceof ExceptionWhileDataFetching){
            ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching) graphQLError;
            if((ex.getException() instanceof GraphQLError)){
                return (GraphQLError) ex.getException();
            }
        }
        return graphQLError;
    }
}
//    @Override
//    public List<GraphQLError> processErrors(List<GraphQLError> list) {
//        return list.stream().map(this::getNested).collect(Collectors.toList());
//    }
//
//    private GraphQLError getNested(GraphQLError error) {
//        if (error instanceof ExceptionWhileDataFetching) {
//            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
//            if (exceptionError.getException() instanceof GraphQLError) {
//                return (GraphQLError) exceptionError.getException();
//            } else if(exceptionError.getException() instanceof java.lang.reflect.UndeclaredThrowableException) {
//
//                return (GraphQLError) exceptionError.getException();
//            }
//        }
//        return error;
//    }
//
//    @Override
//    public List<GraphQLError> processErrors(List<GraphQLError> graphQLErrors) {
//        return graphQLErrors.stream().map(this::handleGraphQLError).collect(Collectors.toList());
//    }
//
//    private GraphQLError handleGraphQLError(GraphQLError error) {
//        ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
////        if (exceptionError.getException() instanceof GraphQLError) {
////            return (GraphQLError) exceptionError.getException();
////        }
//        if (error instanceof GraphQLException) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "GraphQLException as GraphQLError...", (GraphQLException) error);
//        } else if (error instanceof ValidationError){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ValidationError: " + error.getMessage());
//        }else if (exceptionError.getException() instanceof java.lang.reflect.UndeclaredThrowableException){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NotFoundError: " + exceptionError.getMessage());
//        } else {
//            log.error("Yet another GraphQLError...", error);
//            return error;
//        }
//    }

//}