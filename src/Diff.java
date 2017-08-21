
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//Compute inserted and deleted text data. 
public class Diff extends LCS{
	private String diffResult = "";
	private boolean changedFlag = false;
	
	//fields for calculating accuracy 
	public double changed = 0;//changed characters from an original file  
	public double inserted = 0;//unnecessary characters that inserted 
	public double deleted = 0;//deleted characters from an original file 
	
	public Diff(String original, String yours) {
		super(original, yours);
	}

	public void findDiff(){
		String original = this.getOriginal();
		String yours = this.getYours();
		String lcs = this.getLcsResult();
		int lcsLength = lcs.length();//length of original text
		int i = 0;//index of lcs
		int j = 0;//index of yours
		int k = 0;//index of original
		
		
		for(i = 0; i<lcsLength; i++){
			if(lcs.charAt(i) == original.charAt(i+k) && lcs.charAt(i) == yours.charAt(i+j)){
				copy(i);
				changedFlag = false;
			}else{
				if(lcs.charAt(i) != yours.charAt(i+j)){
					while(lcs.charAt(i) != yours.charAt(i+j)){
						inserted(i+j);
						j++;
						inserted++;
					}
					changedFlag = true;
				}else{
					while(lcs.charAt(i) != original.charAt(i+k)){
						deleted(i+k);
						k++;
						deleted++;
					}
					if(changedFlag == true){
						changedFlag = false;
						changed++;inserted--;deleted--;
					}
				}
				i--;
			}	
		}		
		
		//for checking last chrs
		String lastYours = getYours().substring(i+j).replace(" ", "");
		String lastOriginal = getOriginal().substring(i+k).replace(" ", "");
		checkLastChrs(lastYours, lastOriginal);
		
	}
	
	/**
	 * Mark unnecessary inserted characters
	 * @param index
	 */
	private void inserted(int index){
		if(getYours().charAt(index)==' '){//ignore blank
			return;
		}else{
			diffResult += "-"+getYours().charAt(index);
		}
	}
	
	/**
	 * Mark deleted characters that need to write on 
	 * @param index
	 */
	private void deleted(int index){
		if(getOriginal().charAt(index)==' '){//ignore blank
			return;
		}else{
			diffResult += "+"+getOriginal().charAt(index);
		}
	}
	
	/**
	 * Copy same character 
	 * @param index
	 */
	private void copy(int index){
		diffResult += getLcsResult().charAt(index);
	}
	
	private void checkLastChrs(String subYours, String subOriginal){
		if(subYours.equals(subOriginal)){//equal case
			return;
		}else{
			if(subYours.length()!=0){
				for(int i = 0; i<subYours.length(); i++){//inserted case
					diffResult +="-"+subYours.charAt(i);
					inserted++;
				}
				changedFlag = true;
			}if(subOriginal.length()!=0){
				for(int i = 0; i<subOriginal.length(); i++){//deleted case
					diffResult +="+"+subOriginal.charAt(i);
					deleted++;
				}
				if(changedFlag == true){
					changedFlag = false;
					changed++;inserted--;deleted--;
				}
			}
		}
	}
	public String getDiffResult(){
		return diffResult;
	}	
	
	/**
	 * Generate result text file 
	 */
	public void getResultTxt(){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter("Result.txt"));
			out.write(diffResult);
			out.close();
		}
		catch(IOException e){
			System.out.println("Exception");
		}
	}
	
	/**
	 * Calculate accuracy based on different characters of two text files  
	 * @return accuracy in % form
	 */
	public double getAccuracy(){
		double acc = ((getOriginal().length()-changed-deleted-inserted) / getOriginal().length()) *100;
		//System.out.println(getOriginal().length());
		//System.out.println(changed);
		//System.out.println(deleted);
		//System.out.println(inserted);
		return Math.floor(acc);
	}
	
}
