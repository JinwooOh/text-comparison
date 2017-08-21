/**
 * This is the class for figuring out the longest common sequence of two string 
 * It uses LCS table 
 */
import java.lang.*;
public class LCS {
	private String original;
	private String yours;
	private String lcsResult ="";
	
	public int[][] arr;
	public LCS(String original, String yours){
		this.original = normalize(original);
		this.yours = normalize(yours);
	}
	
	/**
	 * Generate LCS table and put numbers accordingly 
	 * @return LCS table
	 */
	public int[][] createArr(){
		arr = new int[yours.length()+1][original.length()+1];
		//set each first row and column 0
		for(int i = 1; i<= original.length(); i++){
			arr[0][i] = 0;
		}
		for(int i = 1; i<= yours.length(); i++){
			arr[i][0] = 0;
		}

		//put the numbers
		for(int i = 0; i<yours.length(); i++){
			for(int j = 0; j<original.length(); j++){
				if(original.charAt(j) == yours.charAt(i)){
					arr[i+1][j+1] = arr[i][j]+1;
				}else{
					if(arr[i+1][j] > arr[i][j+1]){
						arr[i+1][j+1] = arr[i+1][j];
					}else if(arr[i][j+1] > arr[i+1][j]){
						arr[i+1][j+1] = arr[i][j+1];
					}else{
						arr[i+1][j+1] = arr[i][j+1];
					}
				}
			}
		}
		return arr;
	}
	
	/**
	 *  Compare i's character of a string to j's character of another string
	 * @param i i's character of a string
	 * @param j j's character of a string
	 * @return return true if one string's i's character is equal to another string's j's character  
	 */
	public boolean isXYEqual(int i, int j){
		if(yours.charAt(i-1)==original.charAt(j-1)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Return LCS table 
	 * @return return LCS table 
	 */
	public int[][] getArr(){
		return arr;
	}
	
	/**
	 * Method for getting longest common sequence from LCS table
	 * It uses the helper method
	 *  
	 */
	public void getLcs(){
		getLcs(arr, yours.length(), original.length(), "");
	}
	
	/**
	 * Helper method for getLcs() 
	 * Use backtracking 
	 * @param arr LCS table 
	 * @param i length of a string 
	 * @param j length of other string
	 * @param result the longest common sequence of two string 
	 */
	private void getLcs(int[][] arr, int i, int j, String result){
		//base case
		if(i == 0 || j == 0){
			return;
		//recursive case	
		}else if(isXYEqual(i,j)){//case that are same
			getLcs(arr, i-1, j-1, result+yours.charAt(i-1));
			lcsResult+=yours.charAt(i-1);
		}else{//case that aren't same
			if(arr[i][j-1] > arr[i-1][j]){
				getLcs(arr, i, j-1, result);
			}else{
				getLcs(arr, i-1, j, result);
			}
		}
		
	}
	
	/**
	 * Normalize given text 
	 * Take off \n, \t and double space and tap.
	 * @param text string that want to normalize
 	 * @return normalized string
	 */
	public String normalize(String text){
		text = text.trim();
		text = text.replace("\n", " ");
		text = text.replace("\t", " ");
		while(text.contains("  ")){
			text = text.replace("  ", " ");
		}
		return text;
	}
	
	/**
	 * return the result of longest common sequence 
	 * @return LCS result in String 
	 */
	public String getLcsResult(){
		return lcsResult;
	}
	
	/**
	 * return string that want to compare with original string
	 * @return String that want to compare with  original string
	 */
	public String getYours(){
		return yours;
	}
	
	/**
	 * return string that want to compare with typed string
	 * @return String that want to compare with typed string 
	 */
	public String getOriginal(){
		return original;
	}
}
