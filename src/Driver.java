import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
		public static void main(String args[]) throws IOException{
			//Open the training images file
			ArrayList<String> imageNumbers = new ArrayList<String>();
			String filePath = "digitdata/trainingimages";
			Parser p = new Parser(filePath);
			imageNumbers = p.parseTrainingImages();
			
			//Open the training labels file
			ArrayList<Integer> textNumbers = new ArrayList<Integer>();
			filePath = "digitdata/traininglabels";
			p = new Parser(filePath);
			textNumbers = p.parseTrainingLabels();
			
			//Create a HashMap
			HashMap<Integer, String> trainingData = new HashMap<Integer,String>();
			for(String i : imageNumbers){
					System.out.println(i);
			}
			
			if(textNumbers.size() == imageNumbers.size()){
				for(int i=0;i<textNumbers.size();i++){
					trainingData.put(textNumbers.get(i), imageNumbers.get(i));
				}
				System.out.println("HashMap Created");
			}
			
			//Parse images
			
			//Associate labels to the strings in the Hashmap
		}
}
