import java.util.ArrayList;

public class Classifier {

	int HYPOTHESIS;
	ArrayList<Integer> countOfZ;
	ArrayList<Double> priorProbability;
	double predictorPriorProbability;
	
	public Classifier(int z){
		this.HYPOTHESIS = z;
		this.priorProbability = new ArrayList<Double>();
		this.countOfZ = new ArrayList<Integer>();
	}
	
	public ArrayList<Double> setPriorProbability(ArrayList<Integer> textNumbers){
		//TODO:Optimize this loop
		for(int i=0;i<this.HYPOTHESIS;i++){
			int count = 0;
			for(int j=0;j<textNumbers.size();j++){
				if(textNumbers.get(j) == i){
					count++;
				}
			}
			this.countOfZ.add(count);
			this.priorProbability.add(i, ((double) count/textNumbers.size()));
		}
		
		//Check Sum of Count
		int count = 0;
		for(int x : this.countOfZ){
			count += x;
		}
		if(count == textNumbers.size()){
			System.out.println("Success");
		}
		
		//Check for Partition.
		double sum = 0;
		for(double x : this.priorProbability){
			sum+= x;
		}
		if(sum == 1.0){
			System.out.println("Prior Probabilities form a partition.");
		}else{
			System.out.println(sum);
		}
		
		return priorProbability;
	}

	public Double getLikelyHood(int i, FeatureModel featureModel, int[] imageFeatures) {
		
		double ppCount = this.countOfZ.get(i);
		
		double f1,f2,f3;
		if(featureModel.getLengthOfLetter().containsKey(imageFeatures[0])){
			f1 = featureModel.getLengthOfLetter().get(imageFeatures[0])/ppCount;
		}else{
			f1 = 0.0;
		}
		
		if(featureModel.getHashCount().containsKey(imageFeatures[1])){
			f2 = featureModel.getHashCount().get(imageFeatures[1])/ppCount;
		}else{
			f2 = 0.0;
		}
		
		if(featureModel.getPlusCount().containsKey(imageFeatures[2])){
			f3 = featureModel.getPlusCount().get(imageFeatures[2])/ppCount;
		}else{
			f3 = 0.0;
		}
		
		//Naive Bayes Assmption for Conditional Independence
		return f1*f2*f3;
	}

	public double predictorPriorProbability(int[] imageFeatures, ArrayList<FeatureModel> featureModel) {
		double f1 = 0,f2 = 0,f3 = 0.0;
		double ppCount = 5000;
		for(int i=0;i<this.HYPOTHESIS;i++){
			FeatureModel fl = featureModel.get(i);
			if(fl.getLengthOfLetter().containsKey(imageFeatures[0])){
				f1 += fl.getLengthOfLetter().get(imageFeatures[0]);
			}else{
				f1 += 0.0;
			}
			
			if(fl.getHashCount().containsKey(imageFeatures[1])){
				f2 += fl.getHashCount().get(imageFeatures[1]);
			}else{
				f2 += 0.0;
			}
			
			if(fl.getPlusCount().containsKey(imageFeatures[2])){
				f3 += fl.getPlusCount().get(imageFeatures[2]);
			}else{
				f3 += 0.0;
			}
		}
		this.predictorPriorProbability = f1/ppCount * f2/ppCount * f3/ppCount;
		return this.predictorPriorProbability;
	}

}
