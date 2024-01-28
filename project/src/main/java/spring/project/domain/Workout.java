package spring.project.domain;

public class Workout {
    private int workoutId;
    private String workoutType;
    private String workoutDifficulty;
    private String trainerId;
    private String trainerName;

    public Workout(int workoutId, String workoutType, String workoutDifficulty, String trainerId, String trainerName) {
        this.workoutId = workoutId;
        this.workoutType = workoutType;
        this.workoutDifficulty = workoutDifficulty;
        this.trainerId = trainerId;
        this.trainerName = trainerName;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutDifficulty() {
        return workoutDifficulty;
    }

    public void setWorkoutDifficulty(String workoutDifficulty) {
        this.workoutDifficulty = workoutDifficulty;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}
