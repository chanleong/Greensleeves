package essay;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;


public class Paragraph {
	private ArrayList<Sentence> sentences;
	private String paragraph;
	private int paragraphNum;
	
	public Paragraph(String paragraph, int paragraphNum){
		this.paragraph = paragraph;
		sentences = new ArrayList<Sentence>();
		
		try {
			InputStream modelIn = new FileInputStream("lib/model/en-sent.bin");
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			
			String _sentences[] = sentenceDetector.sentDetect(this.paragraph);
			
			for(int i = 0; i < _sentences.length; i++){
				sentences.add(new Sentence(_sentences[i], this.paragraphNum, i));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param paragraph A string of paragraph
	 */
	public Paragraph(String paragraph){
		this.paragraph = paragraph;
		sentences = new ArrayList<Sentence>();
		
		try {
			InputStream modelIn = new FileInputStream("lib/model/en-sent.bin");
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			
			String _sentences[] = sentenceDetector.sentDetect(this.paragraph);
			
			for(int i = 0; i < _sentences.length; i++){
				sentences.add(new Sentence(_sentences[i], this.paragraphNum, i));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return String of the paragraph
	 */
	@Deprecated
	public String getParagraph(){
		return this.paragraph;
	}
	
	@Override
	public String toString(){
		return this.paragraph;
	}
	
	/**
	 * 
	 * @return Set of sentences in a paragraph
	 */
	public ArrayList<Sentence> getSentences(){
		return this.sentences;
	}
	
	/**
	 * 
	 * @param i Index of the sentence
	 * @return Sentence class of the sentence
	 */
	public Sentence getSentence(int i){
		return this.sentences.get(i);
	}
	
	/**
	 * 
	 * @param i Index of the sentence
	 * @return the String of the sentence
	 */
	public String getSentenceStr(int i){
		return this.sentences.get(i).getSentence();
	}
	
	/**
	 * 
	 * @return Number of sentences in the paragraph
	 */
	public int getNumOfSents(){
		return this.sentences.size();
	}
}
