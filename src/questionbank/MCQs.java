package questionbank;

import java.util.ArrayList;

public class MCQs extends Question {
	
	private ArrayList<Pair<Integer, String[]>> mcqs = new ArrayList<Pair<Integer, String[]>>();
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub

	}
	
	public void addQuestionAnsPair(Pair<Integer, String[]> mcq){
		this.mcqs.add(mcq);
	}
	
	public ArrayList<Pair<Integer, String[]>> getQuestionAnsPair(){
		return this.mcqs;
	}

}
