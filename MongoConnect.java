import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;

public class MongoConnect {
	private final String connectionString = "mongodb://localhost:27017";
	private MongoCollection<Document> collection;
	private MongoDatabase database;
	private Document projection;
    public MongoConnect() throws MongoException{

        MongoClient mongoClient = MongoClients.create(new ConnectionString(connectionString));

        database = mongoClient.getDatabase("Systeme");

        collection = database.getCollection("Maladie");
        
    }
    public List<Maladie> getData(){
    	projection = new Document("Symptoms", 1).append("Diagnoses", 1).append("_id", 0);
    	List<Maladie> diagnosesList = new ArrayList<>();
        try (MongoCursor<Document> cursor = collection.find().projection(Projections.fields(projection)).iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();

                List<String> symptoms = document.getList("Symptoms", String.class);
                String diagnosis = document.getString("Diagnoses");

                Maladie currentDiagnosis = new Maladie(diagnosis, symptoms);
                diagnosesList.add(currentDiagnosis);
            }
        }
        return diagnosesList;
    }
    public void setData(String diagnoses, List<String> symptoms) throws MongoWriteException {
    	
        Document newDocument = new Document("Diagnoses", diagnoses).append("Symptoms", symptoms);

        collection.insertOne(newDocument);
        
    }
}
