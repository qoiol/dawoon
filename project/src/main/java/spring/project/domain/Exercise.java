package spring.project.domain;

public class Exercise {
    private String name;
    private String type;
    private String trainerName;

    public Exercise(String name, String type, String trainerName) {
        this.name = name;
        this.type = type;
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
