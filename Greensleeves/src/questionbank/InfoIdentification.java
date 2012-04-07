package questionbank;

import java.awt.List;
import java.util.Arrays;
import java.util.Collection;

import rita.wordnet.RiWordnet;
import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

/**
 * Paragraph matching question
 * @author Raymond
 *
 */
public class InfoIdentification extends Question{

	private final String question = "Which paragraph contains following information?";
	private String[] questionSet;
	private String[] instructions = {"Write the correct letter, "};
	
	public Essay testEssay;
	
	
	public InfoIdentification(int startingQuestion, int numOfQuestion) {
		// TODO Auto-generated constructor stub
		
		super.setStartingQuestion(startingQuestion);
		super.setNumOfQuesitons(numOfQuestion);
		super.setQuestion(question);
		
		char start = super.getQuestionCharacter(startingQuestion -1);
		char end = super.getQuestionCharacter(super.getLastQuestion() -1);
		
		instructions[0] += start + "-" + end + " in boxes " + startingQuestion + "-" + super.getLastQuestion() + ".";
		
		super.setInstruction(instructions);
		
	}


	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
		int numOfParagraph = e.getNumOfParas();
		//RiWordnet wordnet = new RiWordnet(null);
		String[] c = null;
		for(int i = 0; i < numOfParagraph; i++){
			Paragraph p = e.getParagraph(i);
			c = p.getSentence(0).getTokenizedSent();
			
			
			
		}
		
		for(String s: c){
			System.out.println(s);
		}
		
		
	}


	@Override
	public void questionGen(Paragraph p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void questionGen(Sentence s) {
		// TODO Auto-generated method stub
		
	}

}
