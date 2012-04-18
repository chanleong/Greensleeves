package essay;

public class ExamPaper {
	private Essay[] e;
	
	/**
	 * An an exam paper only consist of 3 essays
	 */
	public ExamPaper(){
		e = new Essay[3];
	}
	
	public Essay getEssay(int i){
		return this.e[i];
	}
}
