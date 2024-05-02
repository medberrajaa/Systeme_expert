import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

public class prolog {
	private String plFile;
	prolog(String plFile) throws Exception{
		MongoConnect connector = new MongoConnect();
		this.plFile = plFile;
		FileHandler f = new FileHandler(this.plFile);
		f.ecrireBase(connector.getData());
	}
	public String getResults(String[] symptoms) throws Exception{
		Query q1 = new Query("consult('" + this.plFile + "').");
		if (!q1.hasSolution()) {
			throw new Exception();
		}
		q1 = new Query("get_diagnosis(["+String.join(",", symptoms)+"], Diagnosis).");
		if (q1.hasSolution()) {
			String Solution = "";
			while (q1.hasMoreSolutions()) {
				Map<String, Term> solution = q1.nextSolution();
				Term resultX = solution.get("Diagnosis");
				Solution = Solution.concat(resultX + "\n");
			}
			return Solution;
		} else {
			return "No solution found for diagnosis query.";
		}
		
	}
}
