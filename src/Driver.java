import java.io.IOException;

public class Driver {
	public static void main(String args[]) throws IOException{
		
		TrainModel train = new TrainModel();
		
		
		//Test a file
		String imageFile = "digitdata/testimages.txt";
		String labelFile = "digitdata/testlabels.txt";
		
		TestModel test = new TestModel(train,imageFile,labelFile);
		test.testFile();
		
		for(int x: test.predictedNumbers){
			System.out.print(x);
		}
		System.out.println("\n");
		for(int x: test.textNumbers){
			System.out.print(x);
		}

		
		//Test an image
//		String image = test.imageNumbers.get(50);
//		test.testImage(image);
		
		

	}
}
