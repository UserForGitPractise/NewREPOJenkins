package utils;

import pojos.CreateUserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public  class UserGeneratorDataProvider {
    public static Stream<CreateUserRequest> dpstreamgenerator(){

        return  Stream.of(UserGenerator.createSimpleUser(), UserGenerator.createComplexUser());
    }
}
