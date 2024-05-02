import java.util.List;

public class Maladie {
    private final String diagnosis;
    private final List<String> symptoms;

    public Maladie(String diagnosis, List<String> symptoms) {
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }
}
