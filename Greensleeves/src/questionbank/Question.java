package questionbank;

public abstract class Question {
	public enum QuestionType{
		factual,
		tfng, //True false not given
		paragraph,
		cloze,
	}
	private String question;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String askQuesiton(String str){
		return "";		
	}
	
}
