package essay;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class Sentence {
	private String sentence;
	private int sentenceNum;
	private int paragraphNum;
	private String[] tokenizedSent;
	
	
	public Sentence(String sentence,int paragraphNum,int sentenceNum){
		this.sentence = sentence;
		this.sentenceNum = sentenceNum;
		this.paragraphNum = paragraphNum;
	}
	/**
	 * 
	 * @param sentence String of sentence
	 */
	public Sentence(String sentence){
		this.sentence = sentence;
	}
	
	@Deprecated
	public String getSentence(){
		return this.sentence;
	}
	
	@Override
	public String toString(){
		return this.sentence;
	}
	
	public void setSentence(String sentence){
		this.sentence = sentence;
	}
	
	public int getSentenceNum()
	{
		return this.sentenceNum;
	}
	
	/**
	 * 
	 * @return Tokenized sentence stirng, returns an array of string
	 */
	public String[] getTokenizedSent(){
		try{
			InputStream modelIn = new FileInputStream("lib/model/en-token.bin");		
			TokenizerModel model = new TokenizerModel(modelIn);
			TokenizerME tokenizer = new TokenizerME(model);
			this.tokenizedSent = tokenizer.tokenize(this.sentence);
		
			return this.tokenizedSent;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
