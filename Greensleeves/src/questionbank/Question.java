package questionbank;

public abstract class Question {
	private enum QuestionType{
		factual,
		tfng, //True false not given
		paragraph,
		cloze,
	}
	private String question;
	private String[] instructions;
	private final char[] character =
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
	
	public char getQuestionCharacter(int i){
		return this.character[i];
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
	
	public int getLastQuestion(){
		int lastQ = this.startingQuestion + this.numOfQuestions;
		this.lastQuesiton = lastQ;
		return this.lastQuesiton;
	}
	
}
