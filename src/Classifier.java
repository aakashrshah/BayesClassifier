import java.util.ArrayList;
import java.lang.Math;

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
		for(int i=0;i< this.HYPOTHESIS; i++){
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
			System.out.print(x + "\t");
			count += x;
		}
		
		
		if(count == textNumbers.size()){
			System.out.println("= " + count);
		}else{
			System.out.println("= Does not sum up");
		}
		
		
		for(int i=0; i < this.HYPOTHESIS ; i++){
			System.out.print("P( " + i + " )\t");
		}
		System.out.println("");
		
		
		//Check for Partition.
		double sum = 0;
		for(double x : this.priorProbability){
			System.out.print(x + "\t");
			sum+= x;
		}
		
		if(sum == 1.0){
			System.out.println("Prior Probabilities form a partition.");
		}else{
			System.out.println("= " + sum);
		}
		
		return priorProbability;
	}

	public Double getLikelyHood(int i, FeatureModel featureModel, int[] imageFeatures) {
		
		double ppCount = this.countOfZ.get(i);
		
		double f1,f2,f3;
		if(featureModel.getLengthOfLetter().containsKey(imageFeatures[0])){
			f1 = (featureModel.getLengthOfLetter().get(imageFeatures[0])+1)/(ppCount+2);
		}else{
			f1 = 0.0;
		}
		
		if(featureModel.getHashCount().containsKey(imageFeatures[1])){
			f2 = (featureModel.getHashCount().get(imageFeatures[1]))/(ppCount+2);
		}else{
			f2 = 0.0;
		}
		
		if(featureModel.getPlusCount().containsKey(imageFeatures[2])){
			f3 = (featureModel.getPlusCount().get(imageFeatures[2])+1)/(ppCount+2);
		}else{
			f3 = 0.0;
		}
		
		//Naive Bayes Assmption for Conditional Independence
		System.out.println("\nMaximum LikelyHood Estimate with Smoothening");
		System.out.println("P( f1 = " + imageFeatures[0] + " | "+i+" ) = " + f1);
		System.out.println("P( f2 = " + imageFeatures[1] + " | "+i+" ) = " + f2);
		System.out.println("P( f3 = " + imageFeatures[2] + " | "+i+" ) = " + f3);
		
		System.out.print("Applying Naive Bayes Independence Assumption\t");
		System.out.print(Math.log(f1*f2*f3) + "\n");
		return Math.log(f1*f2*f3);
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
