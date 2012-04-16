import java.io.IOException;
import java.util.ArrayList;

import de.linguatools.disco.DISCO;

import questionbank.*;
import essay.*;
public class Main {

	public static void display(ArrayList<String> s)
	{
		for(int i =0; i < s.size();i++)
			System.out.println(s.get(i));
		System.out.println();
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LibraryInitializer li = new LibraryInitializer();
		
		Essay e = new Essay("lib/text4.txt");
//		String discoDir = "C:\\Documents and Settings\\Raymond\\git\\Greensleeves\\Greensleeves\\lib\\wordbase";
//		DISCO disco = new DISCO(discoDir, false);
//		
//		Ranker r = new Ranker();
//		
//		for(int i = 0; i < e.getNumOfParas(); i++){
//			ArrayList<Sentence> ss = r.getRankedSentences(e.getParagraph(i));
//			System.out.println(ss);
//			/*for(int j = 0; i < ss.size(); j++){
//				//System.out.println(ss.get(j).getSentence());
//			}*/
//			//r.getRankedSentences(e.getParagraph(i));
//		}
		
		System.out.println("Find specific fact, e.g. LOCATION");
		ArrayList<String> temp = FactEvaluator.getFact(FactEvaluator.type.LOCATION, e.getEssayStr());
		display(temp);
		
		System.out.println("Find all fact, the 7 types");
		temp = FactEvaluator.getAllUniqueFacts(e.toString());
		display(temp);
		
		System.out.println("Evaluate the key word:");
		String str = FactEvaluator.getKeyFact(e.getEssayStr());
		System.out.println(str);
		System.out.println();
		
		System.out.println("Show sentence that consist of a fact in the whole essay");
		ArrayList<Sentence> temp2 = FactEvaluator.getSentenceAbout("Charles Honorton", e);
		for(int i = 0; i < temp2.size(); i++)
		{
			System.out.println(temp2.get(i).getSentence());
		}
		
		
//		MCQ mcq = new MCQ();
//		mcq.questionGen(e.getParagraph(1));
//		
//		System.out.println(mcq.getAnsPair().getLeft());
//		for(int i = 0; i < mcq.getAnsPair().getRight().length; i++){
//			System.out.println(mcq.getAnsPair().getRight()[i]);
//		}
//		System.out.println(mcq.getAns());
		
		
//		ParagraphHeading ph = new ParagraphHeading();		
//		ph.questionGen(e);		
//		InfoIdentification ii = new InfoIdentification(1, 8);	
//		//ii.testEssay = e;
//		System.out.println(ii.getQuestion());
//		System.out.println(ii.getInstructions()[0]);
//		ii.questionGen(e);
//		
//		ArrayList<Pair<Integer, String>> qa = ii.getQAset();
//		
//		for(int i = 0; i < qa.size(); i++){
//			System.out.println(qa.get(i));
//		}
		
//		String[] jj = ii.getQuestionSet();
//		
//		for(int i = 0; i < jj.length; i++){
//			System.out.println(jj[i]);
//		}
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
