package questionbank;

public abstract class Question implements IQuestion{
	public enum QuestionType{
		ParagraphHeading,
		TFNG, //True false not given
		MCQ,
		InfoIdentification,
		cloze,
	}
	private String question;
	private String[] questionSet;
	private String[] instructions;
	public QuestionType questionType;
	
	private int startingQuestion;
	private int lastQuesiton;
	private int numOfQuestions;
	private int ans = -1;
	
	public Question(){
		this.question = "";
		this.instructions = null;
		this.questionType = null;
		this.questionSet = null;
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
		return (char)(i + 65);
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
		return this.questionSet;
	}
	
	public void setQuestionSet(String[] questionSet){
		this.questionSet = questionSet;
	}
	
	public int getLastQuestion(){
		int lastQ = this.startingQuestion + this.numOfQuestions;
		this.lastQuesiton = lastQ;
		return this.lastQuesiton;
	}
	
	/**
	 * 
	 * @return The array index of the answer
	 */
	public int getAns(){
		return this.ans;
	}
	
	/**
	 * 
	 * @param ans Array index of answer
	 */
	public void setAns(int ans){
		this.ans = ans;
	}
	
	/*public static void shuffle(String[] s){
		
	}*/
}
