import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TrainModel {
	
	ArrayList<String> imageNumbers;
	ArrayList<Integer> textNumbers;
	HashMap<Integer, String> trainingData;
	final int HYPOTHESIS = 10;
	Classifier classify;
	ArrayList<Double> priorProbability;
	ArrayList<FeatureModel> features;
	
		public TrainModel() throws IOException{
			//Open the training images file.
			this.imageNumbers = new ArrayList<String>();
			String filePath = "digitdata/trainingimages.txt";
			Parser p = new Parser(filePath);
			this.imageNumbers = p.parseTrainingImages();
			
			
			//Open the training labels file.
			this.textNumbers = new ArrayList<Integer>();
			filePath = "digitdata/traininglabels.txt";
			p = new Parser(filePath);
			this.textNumbers = p.parseTrainingLabels();
			
			
			//Create a HashMap.
			this.trainingData = new HashMap<Integer,String>();
			if(this.textNumbers.size() == this.imageNumbers.size()){
				for(int i=0;i<this.textNumbers.size();i++){
					this.trainingData.put(this.textNumbers.get(i), this.imageNumbers.get(i));
//	            	System.out.println(this.textNumbers.get(i));  
//	            	System.out.println(this.imageNumbers.get(i));  
				}
				System.out.println("HashMap Created\n");
			}
			
			
			//Initialize Classifier.
			this.classify = new Classifier(this.HYPOTHESIS);
			this.priorProbability = new ArrayList<Double>();
			this.priorProbability = this.classify.setPriorProbability(this.textNumbers);
			
			
			//Normalization Check
			if(this.priorProbability.size() == this.HYPOTHESIS){
				System.out.println("Prior Probabilities Calculated\n");
			}
			
			
			//Realize the features.
			this.features = new ArrayList<FeatureModel>();
			for(int i=0;i<this.HYPOTHESIS;i++){
				FeatureModel f = new FeatureModel(this.textNumbers,this.imageNumbers,i);
				f.setFeatures();
				this.features.add(f);
			}
			
			
			//Display Feature Set
			for(int i=0; i<this.HYPOTHESIS ; i++){
				System.out.println("Feature Set for " + i);
				FeatureModel f = this.features.get(i);
				System.out.println("Length Count\t" + f.getLengthOfLetter());
				System.out.println("Hash Count\t" + f.getHashCount());
				System.out.println("Plus Count\t" + f.getPlusCount());
				System.out.println("\n");
			}
			
		}
}
