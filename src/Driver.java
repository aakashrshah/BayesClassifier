import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	@SuppressWarnings("resource")
	public static void main(String args[]) throws IOException{
		TrainModel train = new TrainModel();
		
		//TODO Test a file
		
		//TODO Create a CSV report
		
		//Test a image
		ArrayList<Double> confidence = new ArrayList<Double>();
		String image = train.imageNumbers.get(4009);
		System.out.println(image);
		FeatureModel imageFeatures = new FeatureModel(image);
		int[] features = imageFeatures.getFeatures();
		double ppp = train.classify.predictorPriorProbability(features,train.features);
		
		for(int i=0;i<10;i++){
			//Smoothening
			double a = train.priorProbability.get(i);
			double b = train.classify.getLikelyHood(i,train.features.get(i),features);
			double t = ((a * b));
			
			if(t <= 1.0 && t >= 0.0){
				confidence.add(i, t);
			}
		}
		
		//Calculate Maximum Hypothesis.
		double maxConfidence = 0.0;
		int predictedDigit = 0;
		for(int i=0;i<10;i++){
			if(confidence.get(i) > maxConfidence){
				maxConfidence = confidence.get(i);
				predictedDigit = i;
			}
		}
		
		System.out.println(predictedDigit + "(" + maxConfidence*100 + " %)");
	}
}
