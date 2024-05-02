import java.io.File;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

public class FileHandler {
	private File file;
	public FileHandler(String fileName)throws IOException{
		file = new File(fileName);
	    if (file.createNewFile()) {
	    	System.out.println("File created: " + file.getName());
	    } else {
	    	file.delete();
	    }
	}
	public void ecrireBase(List<Maladie> maladies)throws IOException {
		FileWriter writer = new FileWriter(this.file);
		
		String s = ":- use_module(library(ordsets)).\n";
		for(Maladie maladie : maladies) {
			for(String symptome : maladie.getSymptoms()) {
				s = s.concat("symptom_diagnosis_rule(");
				s = s.concat(symptome.replace(' ', '_').replace("'", "").replace("(", "").replace(")","").toLowerCase() + ",");
				s = s.concat(maladie.getDiagnosis().replace(' ', '_').replace("'", "").replace("(", "").replace(")","").toLowerCase()+").\n");
			}
		}
		writer.write(s);
		writer.write("find_diagnoses_for_symptoms([], []).\n");
		writer.write("find_diagnoses_for_symptoms([Symptom|Rest], Diagnoses) :-\n");
		writer.write("symptom_diagnosis_rule(Symptom, Diagnosis),\n");
		writer.write("find_diagnoses_for_symptoms(Rest, RestDiagnoses),\n");
		writer.write("ord_union([Diagnosis], RestDiagnoses, Diagnoses).\n");
		writer.write("get_diagnosis(Symptoms, Diagnosis) :-\n");
		writer.write("find_diagnoses_for_symptoms(Symptoms, Diagnoses),\n");
		writer.write("ord_subtract(Diagnoses, [], [Diagnosis]).\n");
		writer.close();
	}

}
