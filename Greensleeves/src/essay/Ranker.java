package essay;

import java.util.ArrayList;

public class Ranker {
	
	//Variable for the % of the WHOLE paragraph (i.e. What is the whole paragraph about)
	private Paragraph target;
	private FactEvaluator fe;
	private ArrayList<Integer> fitness;
	private String key;
	
	private int fitnessCal(Sentence s)
	{
		int fitness = 0;
		String sentence = s.getSentence();
		int sentenceNum = s.getSentenceNum();
		//add fitness 1 if it is the first sentence
		if(sentenceNum == 0)
			fitness ++;
		//add fitness 1 if it is the last sentence
		if(sentenceNum == this.target.getNumOfSents()-1)
			fitness ++;
		//Add 1 if the sentence contain the keyword of the paragraph
		if(this.fe.exist(this.key, s.getSentence()))
			fitness ++;
		fitness = fitness + this.fe.getNumOfAllFact(s.getSentence());
		fitness = fitness + sentence.length()/100;
		return fitness;
	}
	
	private ArrayList<Integer> getFitness()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < target.getNumOfSents(); i++)
		{
			result.add(fitnessCal(target.getSentence(i)));
		}
		return result;
	}
	
	public ArrayList<Sentence> getRankedSentences(Paragraph p, FactEvaluator fe)
	{
		this.fe = fe;
		this.target = p;
		ArrayList<Sentence> result = new ArrayList<Sentence>();
		this.key = this.fe.getKeyFact(target.getParagraph());
		ArrayList<Sentence> workingSet = new ArrayList<Sentence>();
		workingSet.addAll(target.getSentences());
		
		fitness = getFitness();
		
		//TESTING
		//System.out.println("TESTING IN getRank");
		//for(int i = 0; i<p.getNumOfSents();i++)
		//{
		//	System.out.println("Sentence "+i+": "+target.getSentenceStr(i)+" ("+fitness.get(i)+")");
		//}
		//END

		for(int i = 0;i < target.getNumOfSents();i++)
		{
			int maxFitness = 0;
			int maxIndex = 0;
			for(int j = 0; j < fitness.size(); j++)
			{
				if(fitness.get(j) > maxFitness)
				{
					maxIndex = j;
					maxFitness = fitness.get(j);
				}
			}
			result.add(workingSet.get(maxIndex));
			workingSet.remove(maxIndex);
			fitness.remove(maxIndex);
			//System.out.println("COUT:"+fitness.size() + "WS:"+workingSet.size());
		}
		
		//TESTING
		//System.out.println("TESTING IN getRank");
		//for(int i = 0; i<result.size();i++)
		//{
			//System.out.println("Sentence : "+result.get(i).getSentence());
		//}
		//END
		
		return result;
	}

}
