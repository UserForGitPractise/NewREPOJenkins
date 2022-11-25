package rest.utils.UserOperations;

public class GetAllUsersList {
    private final static String [][] DEFAULT_USERS = new String[][]{
            {"george.bluth@reqres.in", "George", "Bluth"},
            {"janet.weaver@reqres.in","Janet","Weaver"},
            {"emma.wong@reqres.in","Emma","Wong"},
            {"eve.holt@reqres.in","Eve","Holt"},
            {"charles.morris@reqres.in","Charles","Morris"},
            {"tracey.ramos@reqres.in","Tracey","Ramos"}};
    public static String getAllUsersEmail(int index){
        return DEFAULT_USERS[index][0];
    }
    public static String getAllUsersName(int index){
        return DEFAULT_USERS[index][1];
    }
    public static String getAllUsersLastname(int index){
        return DEFAULT_USERS[index][2];
    }
}
