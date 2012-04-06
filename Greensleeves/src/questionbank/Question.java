package questionbank;

public abstract class Question {
	
	public Question(){
		this.question = "";
		this.questionType = null;
	}	
	
	private enum QuestionType{
		factual,
		tfng, //True false not given
		paragraph,
		cloze,
	}
	private String question;
	private QuestionType questionType;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public QuestionType getQuestionType(){
		return this.questionType;
	}
	
	public void setQuestionType(QuestionType questionType){
		this.questionType = questionType;
	}
	
}
