import itext.PDFFiller;

import java.io.IOException;
import java.util.ArrayList;

import de.linguatools.disco.*;

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
		//Loading library for the programme
		LibraryInitializer li = new LibraryInitializer();
		
		//Load essay
		Essay e = new Essay("lib/test1.txt");
		Essay[] es = new Essay[1];
		es[0] = e;
		
		//Generate exam questions
		ExamGenerator eg = new ExamGenerator(es);
		
		//Start a new thread for ExamGenerator
		Thread t = new Thread(eg);
		t.start();
		
		
		//Waiting for the ExamGenerator thread to finish its generation
		try {
			t.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Generation finished, ready to get the Question list
		//Here is an example loop
		
		for(int i = 0; i < eg.getQuestionList().size(); i++){
			Question q = eg.getQuestionList().get(i);
			
			//If Question q is an instance of InfoIdentification object
			if(q instanceof InfoIdentification){
				InfoIdentification ii = (InfoIdentification)q;
				
				//Getting Question and Answer pair,
				//If the passage have 8 paragraphs, the array list size will be 8,
				//each Pair<Integer, String> contains an answer and question
				//Integer here indicates the correct paragraph and String here is the paragraph description
				ArrayList<Pair<Integer, String>> QAPair = ii.getQuestionAnsPair();
				
				
			}else if(q instanceof ParagraphHeading){
				//Getting Question and Answer pair, similar to InfoIdentification
				//If the passage have 8 paragraphs, the array list size will be 8,
				//each Pair<Integer, String> contains an answer and question
				//Integer here indicates the correct paragraph and String here is the paragraph description
				ParagraphHeading ph = (ParagraphHeading)q;
				ArrayList<Pair<Integer, String>> QAPair = ph.getQuestionAnsPair();
				
				
			}else if(q instanceof MCQ){
				MCQ mcq = (MCQ)q;
				
				//For MCQ, it would only return 1 question, unlike InfoIdentification and ParagraphHeading
				//The return type is Pair<Integer, String[]>, Integer here again is the answer,
				//String[] is a set of MC choices
				Pair<Integer, String[]> QAPair = mcq.getQuestionAnsPair();
				
				//Instruct here would be the question itself
				//It is a length 1 array
				//e.g.
				//Which of the following paragraph contains information X? <-- Instruction
				//
				//(The following shows the Pair<Integer, String[]> structure)
				//[1,  	<---Answer is 1, that means Choice B
				//		(Below is a set of choices represented by String[])
				//		Paragraph A
				//		Paragraph B
				//		Paragraph C
				//		Paragraph D
				//]
				String[] instruct = mcq.getInstructions();
			}else if(q instanceof TFNG){
				TFNG tfng = (TFNG)q;
				
				//For True false not given type, similar to MCQ, it would just return 1 question
				//But the structure is simpler
				//Integer represents the answer again, 0:False, 1:True, 2:Not give (But you may ignore 2 here as I'm not going to implement NG)
				
				Pair<Integer, String> QAPair = tfng.getQuestionAnsPair();
				
			}
			
			System.out.println(q.questionType);
		}
//		System.out.println(eg.getQuestionList().size());
		
//		es[0] = e;
//		
		
		//PDFFiller class, you need to feed the question list to constructor
		PDFFiller ep = new PDFFiller(es, "IELTS", eg.getQuestionList());
		ep.generate();
		
		
//		TFNG tfng = new TFNG(e.getParagraph(1).getSentence(0));
//		tfng.questionGen();
//		
//		//System.out.println(e.getParagraph(1).getSentenceStr(0));
//		tfng.questionGen(e.getParagraph(0).getSentence(0));
		
//		String discoDir = "C:\\Documents and Settings\\Raymond\\git\\Greensleeves\\Greensleeves\\lib\\wordbase";
//		DISCO disco = new DISCO(discoDir, false);
		
//		ReturnDataCol[] s = disco.collocations("powerful computer");
//		
//		for(int i = 0; i < s.length; i++){
//			System.out.println(s[i].word);
//		}
		
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
		
//		System.out.println("Find specific fact, e.g. LOCATION");
//		ArrayList<String> temp = FactEvaluator.getFact(FactEvaluator.type.LOCATION, e.getEssayStr());
//		display(temp);
//		
//		System.out.println("Find all fact, the 7 types");
//		temp = FactEvaluator.getAllUniqueFacts(e.toString());
//		display(temp);
//		
//		System.out.println("Evaluate the key word:");
//		String str = FactEvaluator.getKeyFact(e.getEssayStr());
//		System.out.println(str);
//		System.out.println();
//		
//		System.out.println("Show sentence that consist of a fact in the whole essay");
//		ArrayList<Sentence> temp2 = FactEvaluator.getSentenceAbout("Sirius", e);
//		for(int i = 0; i < temp2.size(); i++)
//		{
//			System.out.println(temp2.get(i).getSentence());
//		}
		
		
//		MCQ mcq = new MCQ(e.getParagraph(1));
//		mcq.questionGen();
//		
//		System.out.println(mcq.getInstructions()[0]);
//		for(int i = 0; i < mcq.getAnsPair().getRight().length; i++){
//			System.out.println(mcq.getAnsPair().getRight()[i]);
//		}
//		System.out.println(mcq.getAns());
		
		
	
//		InfoIdentification ii = new InfoIdentification(e, 0);	
//		ParagraphHeading ph = new ParagraphHeading(e, 0);
//		ph.questionGen();
//		System.out.println(ph.getNumOfQuestions());
//		for(int i = 0; i < ii.getInstructions().length; i++){
//			System.out.println(ii.getInstructions()[i]);
//		}
		
		
		//ii.testEssay = e;
		//System.out.println(ii.getQuestion());
		//System.out.println(ii.getInstructions()[0]);
//		ii.questionGen();
//		
//		ArrayList<Pair<Integer, String>> qa = ii.getQuestionAnsPair();
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
