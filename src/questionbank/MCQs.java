package questionbank;

import java.util.ArrayList;

public class MCQs extends Question {
	
	private ArrayList<String> instructions = new ArrayList<String>();
	private ArrayList<Pair<Integer, String[]>> mcqs = new ArrayList<Pair<Integer, String[]>>();
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub

	}
	
	public void addQuestionAnsPair(Pair<Integer, String[]> mcq){
		this.mcqs.add(mcq);
	}
	
	public void addInstruction(String instruct){
		this.instructions.add(instruct);
	}
	
	public ArrayList<Pair<Integer, String[]>> getQuestionAnsPair(){
		return this.mcqs;
	}
	
	@Override
	public String[] getInstructions(){
		String[] _instructions = instructions.toArray(new String[instructions.size()]);
		return _instructions;
	}

}
