package questionbank;

import java.util.ArrayList;

import essay.Essay;
import essay.FactEvaluator;
import essay.Paragraph;
import essay.Ranker;
import essay.Sentence;

public class SevenTypes extends Question {

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
		Ranker ranker = new Ranker();
		ArrayList<Sentence> rankedSent = ranker.getRankedSentences(p);
		FactEvaluator.type targetType; // type to generate questions
		Integer maxTypeOccurance = 0;
		for (FactEvaluator.type factType : FactEvaluator.type.values()){
			Integer tempTypeCount = 0;
			for (Sentence evalSent : rankedSent){
				tempTypeCount += FactEvaluator.getNumOfFact(factType, evalSent.toString()) 
			}
			if (tempTypeCount > maxTypeOccurance){
				maxTypeOccurance = tempTypeCount;
				targetType = factType;
			}
		}
		ArrayList<String> factChoices = 
			FactEvaluator.getFact(targetType, )
		
		
		
	}

	@Override
	public void questionGen(Sentence s) {
		// TODO Auto-generated method stub

	}

}
