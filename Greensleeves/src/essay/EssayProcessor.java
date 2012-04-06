package essay;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;


public class EssayProcessor{
	String serializedClassifier = "lib/classifiers/english.muc.7class.distsim.crf.ser.gz";
	int Location, Time, Date, Percent, Person, Organization, Money;
	private Essay essay;
	private ArrayList<ArrayList<Sentence>> sentences;
	private ArrayList<ArrayList<Double>> Rating;
	private AbstractSequenceClassifier<CoreLabel> classifier;
	
	public EssayProcessor(Essay essay){
		
		this.essay = essay;
		this.sentences = new ArrayList<ArrayList<Sentence>>();
		this.Rating = new ArrayList<ArrayList<Double>>();
		
		this.classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		
		for(int i = 0; i < essay.getNumOfParas(); i++){
			ArrayList<Sentence> sents = essay.getParagraph(i).getSentences(); 
			this.sentences.add(sents);
			//System.out.println(essay.getParagraph(i).getSentenceStr(j)+"\n" +"\tParagrah " + i);
			paragOccur(i);
			sentenceRating(i);
		}
		
	}
	
	public void paragOccur(int i){
		Fact f = new Fact(essay.getParagraphStr(i), this.classifier);
		Time = f.getTime().size();
		Date = f.getDate().size();
		Percent = f.getPercent().size();
		Person = f.getPerson().size();
		Organization = f.getOrganization().size();
		Money = f.getMoney().size();
		Location = f.getLocation().size();
	}
	
	public void sentenceRating(int i){
		double total;
		double locationRate, timeRate, dateRate, percentRate, personRate, organizationRate, moneyRate, sum;
		Fact f;
		ArrayList<Double> temp = new ArrayList<Double>();
		total = Time + Date + Percent + Person + Organization + Money + Location;
		locationRate = Location/total ;//+ Math.random() + 0.1;
		timeRate = Time/total ;//+ Math.random() ;
		dateRate = Date/total ;//+ Math.random() ;
		percentRate = Percent/total;// + Math.random() ;
		personRate = Person/total ;//+ Math.random() + 0.1;
		organizationRate = Organization/total;// + Math.random() ;
		moneyRate = Money/total;// + Math.random() ;
		for (int j = 0; j < essay.getParagraph(i).getNumOfSents(); j++ ){
			sum = 0;
			f = new Fact(essay.getParagraph(i).getSentenceStr(j), this.classifier);
			if (f.getTime().size()!=0)
				sum = sum + timeRate;//*f.getTime().size();
			if (f.getDate().size()!=0)
				sum = sum + dateRate;//*f.getDate().size();
			if (f.getPercent().size()!=0)
				sum = sum + percentRate;//*f.getPercent().size();
			if (f.getPerson().size()!=0)
				sum = sum + personRate;//*f.getPerson().size();
			if (f.getOrganization().size()!=0)
				sum = sum + organizationRate;//*f.getOrganization().size();
			if (f.getMoney().size()!=0)
				sum = sum + moneyRate;//*f.getMoney().size();
			if (f.getLocation().size()!=0)
				sum = sum + locationRate;//*f.getLocation().size();
			temp.add(sum);
		}
		Rating.add(temp);
	}
	
	public ArrayList<ArrayList<Double>> getRating(){
		return Rating;
	}
}
