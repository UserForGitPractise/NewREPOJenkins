package rest.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public enum JobTypes {
    DEVELOPER(new ArrayList<>()),
    QA(new ArrayList<>()),
    DEVOPS(new ArrayList<>()),
    MANAGER(new ArrayList<>());
    private final List<SpecificSkills> skills;

    JobTypes(List<SpecificSkills> skills) {
        this.skills = skills;
    }

    public JobTypes addSkill(SpecificSkills newSkill) {
        this.skills.add(newSkill);
        return this;
    }

    private String enumarateSkills(List<SpecificSkills> skills) {
        StringJoiner skill = new StringJoiner(", ");
        for (SpecificSkills element :
                skills) {
            skill.add(element.name());
        }
        return skill.toString();
    }

    @Override
    public String toString() {
        return this.name() + "(" + enumarateSkills(this.skills) + ")";
    }
}
