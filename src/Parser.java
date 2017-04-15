import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	File file;
	FileReader fr;
	BufferedReader br;
	
	Parser(String filePath) throws FileNotFoundException{
		this.file = new File(filePath);
		this.fr = new FileReader(this.file);
		this.br = new BufferedReader(this.fr);
	}

	public ArrayList<String> parseTrainingImages() throws IOException {
		
		String line =  null;
		ArrayList<String> numbers = new ArrayList<String>();
		boolean isNewDigit = false;
		String number = null;
		while ((line = this.br.readLine()) != null){
			
			 if((line.contains("+") || line.contains("#"))){
				 if(!isNewDigit){
					 number = new String();
					 isNewDigit = true;
					 number = line + "\n";
				 }else{
					 number += line + "\n";
				 }
				 
			 }else{
				 isNewDigit = false;
				 if( number!= null ){
					 numbers.add(number);
				 }
				 number = null;
			 }
		}

		br.close();
		return numbers;
	}

	public ArrayList<Integer> parseTrainingLabels() throws IOException {
		
		String line =  null;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		String number = null;
		while ((line = this.br.readLine()) != null){
			numbers.add(Integer.parseInt(line));
		}
		
		br.close();
		return numbers;
	}
}
