import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	public static void main(String args[]) throws IOException{
		TrainModel train = new TrainModel();
		
		//TODO Test a file
		
		//TODO Create a CSV report
		
		//Test an image
		ArrayList<Double> confidence = new ArrayList<Double>();
		String image = train.imageNumbers.get(1089);
		System.out.println(image);
		FeatureModel imageFeatures = new FeatureModel(image);
		int[] features = imageFeatures.getFeatures();
		System.out.println("Features of the Image");
		System.out.println("Length Count\t" + features[0]);
		System.out.println("Hash Count\t" + features[1]);
		System.out.println("Plus Count\t" + features[2]);
		
//		double ppp = train.classify.predictorPriorProbability(features,train.features);
		
		for(int i=0;i<10;i++){
			//Smoothening
			double a = train.priorProbability.get(i);
			double b = train.classify.getLikelyHood(i,train.features.get(i),features);
			double t = (Math.log(a) + b);
//			
//			The likelihood is proportional to the probability
//			of observing the data given the parameter estimates
//			and your model
//			if(t <= 1.0 && t >= 0.0){
//				confidence.add(i, t);
//			}
			confidence.add(i, t);
		}
		
		//Calculate Maximum Hypothesis.
		double maxConfidence = Double.NEGATIVE_INFINITY;
		int predictedDigit = 0;
		System.out.println("\nConfidence Levels ");
		for(int i=0;i<10;i++){
			System.out.println(i + " : " + confidence.get(i) + "\t");
			if(confidence.get(i) > maxConfidence){
				maxConfidence = confidence.get(i);
				predictedDigit = i;
			}
		}
		System.out.println("\n");
		System.out.print("ARG MAX = ( " + predictedDigit + " | {f1 = "+features[0] + ", f2 = "+features[1]+", f3 = "+features[2]+"}) = ");
		System.out.println(maxConfidence);
	}
}
