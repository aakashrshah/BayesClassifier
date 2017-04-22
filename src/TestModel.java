import java.io.IOException;
import java.util.ArrayList;

public class TestModel {
	TrainModel train;
	ArrayList<Integer> textNumbers;
	ArrayList<String> imageNumbers;
	ArrayList<Integer> predictedNumbers = new ArrayList<Integer>();
	
	public TestModel(TrainModel train, String imageFile, String labelFile) throws IOException {	
		this.train = train;
		
		this.imageNumbers = new ArrayList<String>();
		String filePath = imageFile;
		Parser p = new Parser(filePath);
		this.imageNumbers = p.parseTrainingImages();
		
		this.textNumbers = new ArrayList<Integer>();
		filePath = labelFile;
		p = new Parser(filePath);
		this.textNumbers = p.parseTrainingLabels();
		
		if(textNumbers.size() == imageNumbers.size()){
			System.out.println("Proceed for testing");
		}else{
			System.out.println("Cannot Test");
		}
		//TODO Create a CSV report
	}
	
	

	public void testImage(String image) {
		ArrayList<Double> confidence = new ArrayList<Double>();
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
		int predictedDigit = -1;
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



	public void testFile() throws IOException {

		for(int testcase = 0; testcase<imageNumbers.size() ; testcase++){
			ArrayList<Double> confidence = new ArrayList<Double>();
			System.out.println(imageNumbers.get(testcase));
			FeatureModel imageFeatures = new FeatureModel(imageNumbers.get(testcase));
			int[] features = imageFeatures.getFeatures();
			System.out.println("Features of the Image");
			System.out.println("Length Count\t" + features[0]);
			System.out.println("Hash Count\t" + features[1]);
			System.out.println("Plus Count\t" + features[2]);
			
			for(int i=0;i<10;i++){
				//Smoothening
				double a = train.priorProbability.get(i);
				double b = train.classify.getLikelyHood(i,train.features.get(i),features);
				double t = (Math.log(a) + b);
//				
//				The likelihood is proportional to the probability
//				of observing the data given the parameter estimates
//				and your model
//				if(t <= 1.0 && t >= 0.0){
//					confidence.add(i, t);
//				}
				confidence.add(i, t);
			}
			
			//Calculate Maximum Hypothesis.
			double maxConfidence = Double.NEGATIVE_INFINITY;
			int predictedDigit = -1;
			System.out.println("\nConfidence Levels ");
			for(int i=0;i<10;i++){
				System.out.println(i + " : " + confidence.get(i) + "\t");
				if(confidence.get(i) > maxConfidence){
					maxConfidence = confidence.get(i);
					predictedDigit = i;
				}
			}
			
			if(predictedDigit == -1){
				predictedDigit = 0;
			}
			System.out.println("\n");
			System.out.print("ARG MAX = ( " + predictedDigit + " | {f1 = "+features[0] + ", f2 = "+features[1]+", f3 = "+features[2]+"}) = ");
			System.out.println(maxConfidence);
			this.predictedNumbers.add(predictedDigit);
		}

		System.out.println(predictedNumbers.size());

		

	}
}
