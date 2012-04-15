import java.io.IOException;
import de.linguatools.disco.DISCO;
import de.linguatools.disco.ReturnDataBN;

public class ChoiceGenerator {
	
	//Need a set of correct answer (in array format) and the number of these answers as input
	public static String[]  ChoiceGenerator(String[] options, int n ) throws IOException{
		// discoDir is the path to get the DISCO wordbase
		String discoDir = "Z:\\Wordbase";
		DISCO disco = new DISCO(discoDir, false);
		
		String[] ansSet;
		ansSet = new String[n*2];
		
		String[] wrongAns;
		wrongAns = new String[n];
		
		ReturnDataBN[] simResult;
		simResult = new ReturnDataBN[n];
		
		//Get similar words from DISCO
		for(int i = 0; i < n; i++){
			if(options[i] != null){
				simResult[i] = disco.similarWords( options[i]);
			}
		}
        
        int outputNo = 1; // the number for the similar word chose to use
        
        // To eliminate repeat answers with the true answer and the existing answer set
        for(int i = 0; i < n; i++){
        	if(simResult[i] != null){
        		
        		
        		for(int j = 0; j < n; j++){
        				if ( simResult[i].words[outputNo].equals(options[j]) ){
        					outputNo++;
        					j = 0;
        				}
        				
        				if ( simResult[i].words[outputNo].equals(wrongAns[j]) ){
        					outputNo++;
        					j = 0;
        				}
        				
        				
        			}		
			
        	wrongAns[i] = simResult[i].words[outputNo];
        	}
        	
        	outputNo = 1; 
        		
        }
        
        
        //Generate AnsSet with true and false answers
        for( int i = 0; i < n; i++){
        	ansSet[i] =  options[i];
        	ansSet[i + n] =  wrongAns[i];
        	
        }
        
        //add A - Z index to each answer
        int A = 65;
        for( int i = 0; i < n*2; i++){
        	ansSet[i] = (char)A + " " + ansSet[i];
        	A++;
        }
        
        //return the answer set
        return ansSet; 

	}
	
	public static void main(String[] args) throws IOException{
		
		String[] options;
		options = new String[5];
		
		String[] show;
		show = new String[10];
		
		options[0] = "China";
		options[1] = "America";
		options[2] = "UK";
		options[3] = "Japan";
		options[4] = "Germany";
		
		show = ChoiceGenerator(options, 5);
		
		for( int i = 0; i < 10; i++){
        	System.out.println(show[i]); 
        }
		
		
	}

}