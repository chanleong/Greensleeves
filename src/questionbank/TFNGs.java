package questionbank;

import java.util.ArrayList;

public class TFNGs extends Question {
	
	private ArrayList<Pair<Integer, String>> tfngs = new ArrayList<Pair<Integer, String>>();
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub

	}
	
	public void addQuestionAnsPair(Pair<Integer, String> tfng){
		this.tfngs.add(tfng);
	}
	
	public ArrayList<Pair<Integer, String>> getQuestionAnsPair(){
		return this.tfngs;
	}

}
