import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations.AnswerAnnotation;
import edu.stanford.nlp.ling.CoreLabel;

public class Fact{

	String s;
	String result;
	ArrayList<String> Location = new ArrayList<String>();
	ArrayList<String> Time = new ArrayList<String>();
	ArrayList<String> Person = new ArrayList<String>();
	ArrayList<String> Organization = new ArrayList<String>();
	ArrayList<String> Money = new ArrayList<String>();
	ArrayList<String> Percent = new ArrayList<String>();
	ArrayList<String> Date = new ArrayList<String>();
	
	
	//Library location for classifier
	String serializedClassifier = "lib/classifiers/english.muc.7class.distsim.crf.ser.gz";
	
	Fact(String input, AbstractSequenceClassifier<CoreLabel> classifier){
		this.s = input;
		result = classifier.classifyWithInlineXML(input);
		init(result);
	}
	
	//Constructor
	Fact(String input)
	{
		this.s = input;
		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		result = classifier.classifyWithInlineXML(this.s);
		init(result);
		
	}
	
	private void init(String result){
		System.out.println(result);
		//Location
		Pattern p = Pattern.compile("<LOCATION>\\s*(.+?)\\s*</LOCATION>");
	    Matcher m = p.matcher(result);
	    while(m.find())
	    {
	    	Location.add(m.group(1));
	    }
	    
	    //Time
		p = Pattern.compile("<TIME>\\s*(.+?)\\s*</TIME>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Time.add(m.group(1));
	    }
	    //Person
		p = Pattern.compile("<PERSON>\\s*(.+?)\\s*</PERSON>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Person.add(m.group(1));
	    }
	    //Organization
		p = Pattern.compile("<ORGANIZATION>\\s*(.+?)\\s*</ORGANIZATION>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Organization.add(m.group(1));
	    }
	    //Money
		p = Pattern.compile("<MONEY>\\s*(.+?)\\s*</MONEY>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Money.add(m.group(1));
	    }
	    //Percent
		p = Pattern.compile("<PERCENT>\\s*(.+?)\\s*</PERCENT>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Percent.add(m.group(1));
	    }
	    //Date
		p = Pattern.compile("<DATE>\\s*(.+?)\\s*</DATE>");
	    m = p.matcher(result);
	    while(m.find())
	    {
	    	Date.add(m.group(1));
	    }
	}
	
	//Define return functions
	ArrayList<String> getLocation()
	{
		return this.Location;
	}
	ArrayList<String> getTime()
	{
		return this.Time;
	}
	ArrayList<String> getOrganization()
	{
		return this.Organization;
	}
	ArrayList<String> getDate()
	{
		return this.Date;
	}
	ArrayList<String> getPercent()
	{
		return this.Percent;
	}
	ArrayList<String> getPerson()
	{
		return this.Person;
	}
	ArrayList<String> getMoney()
	{
		return this.Money;
	}
}
