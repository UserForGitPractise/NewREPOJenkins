package rest.utils.UserOperations;

import rest.pojos.Users;

import java.util.stream.Stream;

public class GetAllUsersList {
    public final static String[][] DEFAULT_USERS = new String[][]{
            {"george.bluth@reqres.in", "George", "Bluth"},
            {"janet.weaver@reqres.in", "Janet", "Weaver"},
            {"emma.wong@reqres.in", "Emma", "Wong"},
            {"eve.holt@reqres.in", "Eve", "Holt"},
            {"charles.morris@reqres.in", "Charles", "Morris"},
            {"tracey.ramos@reqres.in", "Tracey", "Ramos"}};

    public static Stream<Users> allUsersStream() {
        Users user1 = new Users(DEFAULT_USERS_ID[0], DEFAULT_USERS[0][0], DEFAULT_USERS[0][1], DEFAULT_USERS[0][2]);
        Users user2 = new Users(DEFAULT_USERS_ID[1], DEFAULT_USERS[1][0], DEFAULT_USERS[1][1], DEFAULT_USERS[1][2]);
        Users user3 = new Users(DEFAULT_USERS_ID[2], DEFAULT_USERS[2][0], DEFAULT_USERS[2][1], DEFAULT_USERS[2][2]);
        Users user4 = new Users(DEFAULT_USERS_ID[3], DEFAULT_USERS[3][0], DEFAULT_USERS[3][1], DEFAULT_USERS[3][2]);
        Users user5 = new Users(DEFAULT_USERS_ID[4], DEFAULT_USERS[4][0], DEFAULT_USERS[4][1], DEFAULT_USERS[4][2]);
        Users user6 = new Users(DEFAULT_USERS_ID[5], DEFAULT_USERS[5][0], DEFAULT_USERS[5][1], DEFAULT_USERS[5][2]);
        return Stream.of(user1, user2, user3, user4, user5, user6);
    }

    ;
    public final static int[] DEFAULT_USERS_ID = new int[]{1, 2, 3, 4, 5, 6,};

    public static String getAllUsersEmail(int index) {

        return DEFAULT_USERS[index][0];
    }

    public static String getAllUsersName(int index) {
        return DEFAULT_USERS[index][1];
    }

    public static String getAllUsersLastname(int index) {
        return DEFAULT_USERS[index][2];
    }

}

