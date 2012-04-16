package questionbank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import simplenlg.framework.NLGFactory;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;
import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

public class MCQ extends Question{
	
	private int ans;
	private Pair<String, String[]> ansPair;
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void questionGen(Paragraph p) {
		// TODO Auto-generated method stub
		try {
			String[] relns = SentenceProcessor.extractSAO(p.toString());
			String[] component = null;
			String[] choices = new String[4];
			
			ArrayList<String> chosenReln; //Chosen relationship, which always has object in the relationship
			
			int[] chosenArray = new int[relns.length];
			int objCount = 0;
			
			for(int i = 0; i < relns.length; i++){
				chosenArray[i] = -1; //Init chosen array
				component = relns[i].split(",");
				String obj = component[2];
				if(!obj.equals("")){
					chosenArray[i] = i;
					objCount++;
				}
			}
			
			//If object returned is smaller than 4
			if(objCount >= 4){
				NLGFactory nlgFactory = new NLGFactory(LibraryInitializer.LEXICON);
				Realiser realiser = new Realiser(LibraryInitializer.LEXICON);
				SPhraseSpec sent;
				chosenReln = new ArrayList<String>();

				ArrayList<String> sents = new ArrayList<String>();
				ArrayList<String> objs = new ArrayList<String>();
				
				//Choose an relation that have object
				for(int i = 0; i < chosenArray.length; i++){
					if(chosenArray[i] != -1){
						//The chosen array element
						int idx = chosenArray[i];
						chosenReln.add(relns[idx]);
					}					
				}
				
				//Generate sentence from the relationship
				for(int i = 0; i < chosenReln.size(); i++){
					component = chosenReln.get(i).split(",");
					String subj = component[0];
					String action = component[1];
					String obj = component[2];
					sent = nlgFactory.createClause(subj, action);
					
					String out = realiser.realiseSentence(sent);
					sents.add(out.substring(0, out.length()-1) + ":");
					//System.out.println(out.substring(0, out.length()-1) + ":");
					objs.add(obj);
					//System.out.println("Object :" + obj);
				}
				
				//Randomly choose 1 relns
				Random r = new Random();
				int _pickedSent = r.nextInt(sents.size());
				
				String pickedSent = sents.get(_pickedSent);
				String pickedObj = objs.get(_pickedSent);
				objs.remove(_pickedSent);
				
				Collections.shuffle(objs); //Make the remaining choice random
				choices[0] = pickedObj;
				
				
				//Randomly choose other 3 choices that is different from the picked one
				for(int i = 1; i <= 3; i++){
					choices[i] = objs.get(i-1);
				}
				//Shuffle the choices
				shuffle(choices);
				
				this.ansPair = new Pair<String, String[]>(pickedSent, choices);
				
				
				
			}else{

				ArrayList<Pair<Double, String>> conceptPair = SentenceProcessor.extractConcept(p.getParagraph());
				//System.out.println("Which of the following correctly describe the paragraph " + "" + " ? (Choose the best one)");
				
				String instruction = "Which of the following correctly describe the paragraph " + "" + " ? (Choose the best one)";
				
				//The first one is of the highest relevancy, mark it as answer
				choices[0] = conceptPair.get(0).getRight();
				conceptPair.remove(0);
				this.ans = 0; //Choice 0 is answer
				
				Collections.shuffle(conceptPair); //Randomly pick some concepts
				
				if(conceptPair.size() > 4){
					for(int i = 1; i <= 3; i++){
						choices[i] = conceptPair.get(i).getRight();
					}
				}
				
				//Shuffle the answer
				shuffle(choices);
				
				this.ansPair = new Pair<String, String[]>(instruction, choices);
			}
			//SentenceProcessor.extractConcept(p.getParagraph());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void shuffle(String[] choices){
		Random r = new Random();
		String tmp = "";
		int tmpAns = this.ans;
		
		for(int i = 0; i < choices.length; i++){
			int rand1 = r.nextInt(choices.length);
			tmp = choices[i];
			choices[i] = choices[rand1];
			choices[rand1] = tmp;
			
			if((tmpAns == i)||(tmpAns == rand1)){
				if(tmpAns == rand1) tmpAns = i;
				else if(tmpAns == i) tmpAns = rand1;
			}
		}
		
		this.ans = tmpAns;
		super.setAns(this.ans);
	}
	
	public Pair<String, String[]> getAnsPair(){
		return this.ansPair;
	}
	
	@Override
	public void questionGen(Sentence s) {
		// TODO Auto-generated method stub
		
	}

}
