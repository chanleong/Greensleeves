package essay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import questionbank.LibraryInitializer;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

public class FactEvaluator{
	
	public static enum type
	{
		LOCATION, PERSON, ORGANIZATION, DATE, MONEY, PERCENT, TIME
	}

	private static AbstractSequenceClassifier<CoreLabel> classifier = LibraryInitializer.CLASSIFIER;;
	
	//Library location for classifier
	String serializedClassifier = "lib/classifiers/english.muc.7class.distsim.crf.ser.gz";
	//classifier = 
	//Constructor
	public FactEvaluator()
	{
		
	}
	
	//Input parameter: type: LOCATION, PERSON, ORGANIZATION, TIME, MONEY, PERCENT, DATE
	//Output: Null if nothing is found for that fact.
	public static ArrayList<String> getFact(FactEvaluator.type t, String input)
	{
		String type = t.toString();
		String resultStr = classifier.classifyWithInlineXML(input);
		ArrayList<String> result = new ArrayList<String>();
		//System.out.println(resultStr);
		Pattern p = Pattern.compile("<"+type+">\\s*(.+?)\\s*</"+type+">");
	    Matcher m = p.matcher(resultStr);
	    while(m.find())
	    {
	    	result.add(m.group(1));
	    }
	    return result;
	}
	
	public static ArrayList<String> getAllFact(String input)
	{
		ArrayList<String> result = new ArrayList<String>();
		for(FactEvaluator.type t: FactEvaluator.type.values())
			result.addAll(getFact(t, input));
		return result;
	}
	
	/**
	 * 
	 * @param input
	 * @return A unique set of facts will be returned
	 */
	public static ArrayList<String> getAllUniqueFacts(String input){
		ArrayList<String> result = new ArrayList<String>();
		for(FactEvaluator.type t: FactEvaluator.type.values())
			result.addAll(getFact(t, input));
		return new ArrayList<String>(new HashSet<String>(result));
	}
	
	//Return number of fact for a specific type
	public static int getNumOfFact(FactEvaluator.type t, String s)
	{
		return getFact(t, s).size();
	}
	
	//Return number of fact for ALL types
	public static int getNumOfAllFact(String s)
	{
		int total = 0;
		for(FactEvaluator.type t: FactEvaluator.type.values())
			total = total + getFact(t, s).size();
		return total;
	}
	
	//Return the Fact that appeared most in the string 'input'
	public static String getKeyFact(String input)
	{
		ArrayList<String> factList = getAllFact(input);
		int count = 0;
		String result = null;
		for(int i = 0; i<factList.size() ; i++)
		{
			int occurence = Collections.frequency(factList, factList.get(i));
			if(occurence > count)
			{
				count = occurence;
				result = factList.get(i); 
			}	
		}
		return result;
	}
	
	//Check if the 'fact' exists in the 'input' sentence or not
	public static boolean exist(String fact, String input)
	{
		ArrayList<String> temp = getAllFact(input);
		if(temp.contains(fact))
			return true;
		return false;
	}

	
	public static ArrayList<Sentence> getSentenceAbout(String fact, Essay e)
	{
		ArrayList<Sentence> result = new ArrayList<Sentence>();
		for(int i = 0 ; i < e.getNumOfParas(); i++)
		{
			for(int j = 0 ; j < e.getParagraph(i).getNumOfSents(); j++)
			{
				if(exist(fact, e.getParagraph(i).getSentenceStr(j)))
					result.add(e.getParagraph(i).getSentence(j));
			}
		}
		return result;
	}
}
