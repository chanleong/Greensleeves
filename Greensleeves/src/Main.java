import java.util.ArrayList;

import questionbank.FeaturesMatching;
import questionbank.InfoIdentification;
import questionbank.LibraryInitializer;
import essay.Essay;
import essay.EssayProcessor;
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryInitializer li = new LibraryInitializer();
		
		Essay e = new Essay("lib/test.txt");
		EssayProcessor ep = new EssayProcessor(e);
		
		ArrayList<ArrayList<Double>> output;
		InfoIdentification ii = new InfoIdentification(1, 5);
		FeaturesMatching fm = new FeaturesMatching();
		
		fm.questionGen();
		
		//ii.testEssay = e;
		System.out.println(ii.getQuestion());
		System.out.println(ii.getInstructions()[0]);
		ii.questionGen(e);
		String[] jj = ii.getQuestionSet();
		
		for(int i = 0; i < jj.length; i++){
			System.out.println(jj[i]);
		}
		/*for(int i = 0; i < e.getNumOfParas(); i++){
			for(int j = 0; j < e.getParagraph(i).getNumOfSents(); j++){
				System.out.println(e.getParagraph(i).getSentenceStr(j)+"\n" +"\tParagrah " + i);
			}
		}*/
		/*output = ep.getRating();
		for (int i =0; i < output.size(); i++){
			System.out.println("Paragraph "+i);
			for (int j = 0; j < output.get(i).size(); j++)
				System.out.println(output.get(i).get(j));
	
		}*/
	}
}
