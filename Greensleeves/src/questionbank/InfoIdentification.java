package questionbank;

import java.util.List;
import java.util.Random;

import rita.wordnet.RiWordnet;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

/**
 * Paragraph matching question
 * @author Raymond
 *
 */
public class InfoIdentification extends Question{

	private final String question = "Which paragraph contains following information?";
	private String[] questionSet;
	private String[] instructions = {"Write the correct letter, "};
	
	public Essay testEssay;
	
	
	public InfoIdentification(int startingQuestion, int numOfQuestion) {
		// TODO Auto-generated constructor stub
		
		super.setStartingQuestion(startingQuestion);
		super.setNumOfQuesitons(numOfQuestion);
		super.setQuestion(question);
		
		char start = super.getQuestionCharacter(startingQuestion -1);
		char end = super.getQuestionCharacter(super.getLastQuestion() -1);
		
		instructions[0] += start + "-" + end + " in boxes " + startingQuestion + "-" + super.getLastQuestion() + ".";
		
		super.setInstruction(instructions);
		
	}


	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
		int numOfParagraph = e.getNumOfParas();
		RiWordnet wordnet = new RiWordnet();
		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		Tree parse;
		
		String[] c = null;
		
		for(int i = 0; i < numOfParagraph; i++){
			String out = "";	
			
			Paragraph p = e.getParagraph(i);
			int numOfSent = p.getNumOfSents();
			Random r = new Random();
			int chosen = r.nextInt(numOfSent-1);
			
			Sentence s = p.getSentence(chosen);
			parse = lp.apply(s.getSentence());
			GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
			List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
			gs.getNodes();
			
			
			String tokens[] = s.getTokenizedSent();
			int size = tokens.length;
			int word = r.nextInt(size-1);
			
			String pos = wordnet.getBestPos(tokens[word]);
			//System.out.println(pos);
			//System.out.println(tokens[word]);
			if(tokens[word] != null && pos != null){
				String ss = wordnet.getAllSynonyms(tokens[word], pos)[0];
				tokens[word] = ss;
				
				for(int j = 0; j < tokens.length; j++){
					out += tokens[j] + " ";
				}
			}
			System.out.println(out + "\n");
			
		}
		
		/*for(String s: c){
			System.out.println(s);
		}*/
		
		
	}


	@Override
	public void questionGen(Paragraph p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void questionGen(Sentence s) {
		// TODO Auto-generated method stub
		
	}

}
