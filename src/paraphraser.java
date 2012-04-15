import java.io.IOException;

import de.linguatools.disco.DISCO;
import de.linguatools.disco.ReturnDataBN;
import de.linguatools.disco.ReturnDataCol;

public class paraphraser {
	DISCO disco;
	String workingSent;
	boolean changes[] = {false, false, false, false};
	
	public paraphraser(String workingSent) throws IOException{
		disco = new DISCO(parameters.disco.discoDir, false);
		this.workingSent = workingSent;
		
	}
	
	public void setChanges(boolean noun, boolean verb, boolean adj, boolean voice){
		changes[0] = noun;
		changes[1] = verb;
		changes[2] = adj;
		changes[3] = voice;
	}
	
	public String paraphrase() throws IOException{
		String inter[] = workingSent.split(" ");
		for (int i = 0; i < 4; i++){
			if ( i == 0 && changes[i] ){ //nouns
				tagparsing tp = new tagparsing(workingSent);
				Integer n[] = tp.getNouns();
				System.err.println("done parse nouns");
				for (Integer j : n)
					System.err.println(j.toString());
				for (Integer ni : n){
					String sw = disco.similarWords(inter[ni]).words[1];
					System.err.println(sw);
					inter[ni] = sw;
					
				}
			}else if ( i == 1 && changes[i] ){ //verbs
				
			}else if ( i == 2 && changes[i] ){ //adj
				
			}else if ( i == 3 && changes[i] ){ // voice
				
			}
		}
		String result = "";
		for (int i = 0; i < inter.length; i++)
			if (i != inter.length - 1)
				result += inter[i] + " ";
			else
				result += inter[i];
		return inter.toString();
	}
	
	
	
}



