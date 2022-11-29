package rest.utils;

import rest.pojos.CreateUserRequest;
import rest.pojos.UpdateUserRequest;

import static rest.utils.JobTypes.*;
import static rest.utils.SpecificSkills.*;

public class UserGenerator {
    public static CreateUserRequest createDeveloper() {
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Ivan");
        user.setJob(DEVELOPER.toString());
        return user;
    }

    public static CreateUserRequest createQA() {
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Nick");
        user.setJob(QA.toString());
        return user;
    }

    public static CreateUserRequest createDevOps() {
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Alex");
        user.setJob(DEVOPS.toString());
        return user;
    }

    public static CreateUserRequest createManager() {
        CreateUserRequest user = new CreateUserRequest();
        user.setName("Nikolas");
        user.setJob(MANAGER.toString());
        return user;
    }

    public static UpdateUserRequest updateToDeveloper() {
        UpdateUserRequest updatedSimpleUser = new UpdateUserRequest();
        updatedSimpleUser.setName("Ivan");
        updatedSimpleUser.setJob(DEVELOPER.toString());
        return updatedSimpleUser;
    }

    public static UpdateUserRequest updateToQA() {
        UpdateUserRequest updatedComplexUser = new UpdateUserRequest();
        updatedComplexUser.setName("Nick");
        updatedComplexUser.setJob(QA.toString());
        return updatedComplexUser;
    }

    public static UpdateUserRequest updateToDevOps() {
        UpdateUserRequest updatedComplexUser = new UpdateUserRequest();
        updatedComplexUser.setName("Alex");
        updatedComplexUser.setJob(DEVOPS.toString());
        return updatedComplexUser;
    }

    public static UpdateUserRequest updateToManager() {
        UpdateUserRequest updatedComplexUser = new UpdateUserRequest();
        updatedComplexUser.setName("Nikolas");
        updatedComplexUser.setJob(MANAGER.toString());
        return updatedComplexUser;
    }

    public static UpdateUserRequest addSkillDeveloper() {
        UpdateUserRequest patchdUser = new UpdateUserRequest();
        patchdUser.setName("Ivan");
        patchdUser.setJob(DEVELOPER.addSkill(JAVA).addSkill(PYTHON).addSkill(SPRING_BOOT).toString());
        return patchdUser;
    }

    public static UpdateUserRequest addSkillQA() {
        UpdateUserRequest patchUser = new UpdateUserRequest();
        patchUser.setName("Nick");
        patchUser.setJob(QA.addSkill(SQL).addSkill(GIT).addSkill(JUNIT).toString());
        return patchUser;
    }

    public static UpdateUserRequest addSkillDevOps() {
        UpdateUserRequest patchUser = new UpdateUserRequest();
        patchUser.setName("Alex");
        patchUser.setJob(DEVOPS.addSkill(K8S).addSkill(JENKINS).addSkill(DOCKER).toString());
        return patchUser;
    }

    public static UpdateUserRequest addSkillManager() {
        UpdateUserRequest patchUser = new UpdateUserRequest();
        patchUser.setName("Nikolas");
        patchUser.setJob(MANAGER.addSkill(AGILE).addSkill(ALLURE).toString());
        return patchUser;
    }
}
