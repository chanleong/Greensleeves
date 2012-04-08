package questionbank;

import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import rita.wordnet.RiWordnet;

public abstract class Question implements QuestionGenerator{
	private enum QuestionType{
		MatchingHeadings,
		tfng, //True false not given
		InfoIdentification,
		cloze,
	}
	private String question;
	private String[] quesitonSet;
	private String[] instructions;
	private static final char[] character =
									{
										'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
										'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
										'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
									}; 
	public QuestionType questionType;
	
	private int startingQuestion;
	private int lastQuesiton;
	private int numOfQuestions;
	
	public Question(){
		this.question = "";
		this.instructions = null;
		this.questionType = null;
		this.quesitonSet = null;
	}	
	
	
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public QuestionType getQuestionType(){
		return this.questionType;
	}
	
	public String[] getInstructions() {
		return this.instructions;
	}

	public void setInstruction(String[] instructions) {
		this.instructions = instructions;
	}
	
	public void setQuestionType(QuestionType questionType){
		this.questionType = questionType;
	}
	
	public static char getQuestionCharacter(int i){
		return character[i];
	}
	
	public int getNumOfQuestions(){
		return this.numOfQuestions;
	}
	
	public void setNumOfQuesitons(int numOfQs){
		this.numOfQuestions = numOfQs;
	}
	
	public int getStartingQuestion(){
		return this.startingQuestion;
	}
	
	public void setStartingQuestion(int startingQ){
		this.startingQuestion = startingQ;
	}
	
	public String[] getQuestionSet(){
		return this.quesitonSet;
	}
	
	public void setQuestionSet(String[] questionSet){
		this.quesitonSet = questionSet;
	}
	
	public int getLastQuestion(){
		int lastQ = this.startingQuestion + this.numOfQuestions;
		this.lastQuesiton = lastQ;
		return this.lastQuesiton;
	}
}
