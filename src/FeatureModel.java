import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FeatureModel {
	
	private final int FEATURECOUNT = 3;
	
	private HashMap<Integer,Integer> lengthOfLetter;
	private HashMap<Integer,Integer> plusCount;
	private HashMap<Integer,Integer> hashCount;
	
	private ArrayList<Integer> textNumbers;
	private ArrayList<String> imageNumbers;
	
	private int h;
	private String image;
	
	int[] featureSet;
	
	public FeatureModel(ArrayList<Integer> textNumbers, ArrayList<String> imageNumbers,int h){
		//Training Constructor
		this.textNumbers = textNumbers;
		this.imageNumbers = imageNumbers;
		this.h = h;
		
		this.lengthOfLetter = new HashMap<Integer,Integer>();
		this.plusCount = new HashMap<Integer,Integer>();
		this.hashCount = new HashMap<Integer,Integer>();
	}
	
	public FeatureModel(String image) {
		//Testing Constructor
		this.lengthOfLetter = new HashMap<Integer,Integer>();
		this.plusCount = new HashMap<Integer,Integer>();
		this.hashCount = new HashMap<Integer,Integer>();
		this.image = image;
	}

	public void setFeatures() {
		setLengthOfLetter();
		setHashCount();
		setPlusCount();
	}
	
	public int[] getFeatures() {
		this.featureSet = new int[FEATURECOUNT];
		this.featureSet[0] = countLength(this.image);
		this.featureSet[1] = countHash(this.image);
		this.featureSet[2] = countPlus(this.image);
		return this.featureSet;
	}
	
	
	public HashMap<Integer,Integer> getLengthOfLetter() {
		return this.lengthOfLetter;
	}

	public void setLengthOfLetter() {
		for(int i=0; i<this.textNumbers.size();i++){
			if(this.textNumbers.get(i) == h){
				String t = this.imageNumbers.get(i);
				int s = countLength(t);
				if(this.lengthOfLetter.containsKey(s)){
					this.lengthOfLetter.put(s, this.lengthOfLetter.get(s)+1);
				}else{
					this.lengthOfLetter.put(s, 1);
				}
			}
		}
	}

	public int countLength(String t) {
		int length = 0;
		StringTokenizer st = new StringTokenizer(t, "\n");
		while(st.hasMoreTokens()){
			@SuppressWarnings("unused")
			String s = st.nextToken();
			length++;
		}
		return length;
	}


	public HashMap<Integer,Integer> getHashCount() {
		return this.hashCount;
	}

	public void setHashCount() {
		for(int i=0; i<this.textNumbers.size();i++){
			if(this.textNumbers.get(i) == h){
				String t = this.imageNumbers.get(i);
				int s = countHash(t);
				if(this.hashCount.containsKey(s)){
					this.hashCount.put(s, this.hashCount.get(s)+1);
				}else{
					this.hashCount.put(s, 1);
				}
			}
		}
	}

	public int countHash(String t) {
		int count = 0;
		StringTokenizer st = new StringTokenizer(t, "\n");
		while(st.hasMoreTokens()){
			
			String s = st.nextToken();
			char[] line = s.toCharArray();
			for(int i=0;i<line.length;i++){
				if(line[i] == '#'){
					count++;
				}
			}
		}
		return count;
	}
	
	public HashMap<Integer,Integer> getPlusCount() {
		return this.plusCount;
	}

	public void setPlusCount() {
		for(int i=0; i<this.textNumbers.size();i++){
			if(this.textNumbers.get(i) == h){
				String t = this.imageNumbers.get(i);
				int s = countPlus(t);
				if(this.plusCount.containsKey(s)){
					this.plusCount.put(s, this.plusCount.get(s)+1);
				}else{
					this.plusCount.put(s, 1);
				}
			}
		}
	}

	public int countPlus(String t) {
		int count = 0;
		StringTokenizer st = new StringTokenizer(t, "\n");
		while(st.hasMoreTokens()){
			String s = st.nextToken();
			char[] line = s.toCharArray();
			for(int i=0;i<line.length;i++){
				if(line[i] == '+'){
					count++;
				}
			}
		}
		return count;
	}
}
