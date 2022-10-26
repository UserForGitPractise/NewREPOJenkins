package rest.utils;

import rest.pojos.CreateUserRequest;

import java.util.stream.Stream;

public  class UserGeneratorDataProvider {
    public static Stream<CreateUserRequest> dpstreamgenerator(){

        return  Stream.of(UserGenerator.createSimpleUser(), UserGenerator.createComplexUser());
    }
}
