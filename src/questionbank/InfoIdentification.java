package questionbank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import rita.wordnet.RiWordnet;
import simplenlg.features.Feature;
import simplenlg.framework.NLGFactory;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeGraphNode;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

/**
 * Paragraph matching question, generated at Essay level
 * @author Raymond
 *
 */
public class InfoIdentification extends Question{
	
	private Essay e;
	private ArrayList<Pair<Integer, String>> questionAnsPair;
	
	private String[] _questionSet;
	private String[] instructions;
	
	public InfoIdentification(){};
	
	public InfoIdentification(Essay e, int essayNum){
		this.e = e;
		this.questionType = QuestionType.InfoIdentification;
		
		int numOfQs = e.getNumOfParas();
		super.setNumOfQuesitons(numOfQs);
		char start = Question.getQuestionCharacter(0);
		char end = Question.getQuestionCharacter(numOfQs);
		
		this.instructions = new String[4];
		
		this.instructions[0] = "Reading Passage " + essayNum + 
				" has " + numOfQs + " paragraphs labelled as " + start
				+ "-" + end;
		this.instructions[1] = "Which paragraphs contains the following information?";
		this.instructions[2] = "Write the correct letter " + start + "-" + end + " in boxes on your answer sheet.";
		this.instructions[3] = "NB You may use any letter more than once. ";
		
		questionAnsPair = new ArrayList<Pair<Integer, String>>();
		
		super.setInstruction(instructions);
		
	}
	
	public InfoIdentification(int startingQuestion, int numOfQuestion) {
		// TODO Auto-generated constructor stub
		
//		super();
//		
//		super.setStartingQuestion(startingQuestion);
//		super.setNumOfQuesitons(numOfQuestion);
//		super.setQuestion(question);
//		
//		char start = super.getQuestionCharacter(startingQuestion -1);
//		char end = super.getQuestionCharacter(super.getLastQuestion() -1);
//		
//		instructions[0] += start + "-" + end + " in boxes " + startingQuestion + "-" + super.getLastQuestion() + ".";
//		
//		questionAnsSet = new ArrayList<Pair<Integer, String>>();
//		
//		super.setInstruction(instructions);
		
	}

	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		int numOfParagraph = e.getNumOfParas();

		//SimpleNLG
		NLGFactory nlgFactory = new NLGFactory(LibraryInitializer.LEXICON);
		Realiser realiser = new Realiser(LibraryInitializer.LEXICON);
		SPhraseSpec sent;
		this._questionSet = new String[numOfParagraph];

		for(int i = 0; i < numOfParagraph; i++){
			String out = "";	

			Paragraph p = e.getParagraph(i);

			int numOfSent = p.getNumOfSents();
			Random r = new Random();
			int chosen = r.nextInt(numOfSent - 1);
			Sentence s = p.getSentence(chosen);

			try {
				String[] SAO = SentenceProcessor.extractSAO(p.toString());
				String[] component;
				StringBuffer question = new StringBuffer("");
				for(int j = 0; j < SAO.length; j++){
					component = SAO[j].split(",");
					String subj = component[0];
					String act = component[1];
					String obj = component[2];
					String tense = component[3];
					String neg = "";
					if(component.length == 5) neg = component[4];

					if(!obj.equals("")){
						sent = nlgFactory.createClause(subj, act, obj);
						if(!neg.equals("")) sent.setFeature(Feature.NEGATED, true);
					}else{
						sent = nlgFactory.createClause(subj, act);
						if(!neg.equals("")) sent.setFeature(Feature.NEGATED, true);
					}

					String output = realiser.realiseSentence(sent);
					question.append(output);
					question.append("#");

				}
				String[] questions = question.toString().split("#");
				chosen = r.nextInt(questions.length);
				this._questionSet[i] = questions[chosen];
				this.questionAnsPair.add(new Pair<Integer, String>(i, this._questionSet[i]));
				//System.out.println(this.questionSet[i]);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			/*parse = lp.apply(s.getSentence());

					GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
					Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed(true);
					Collection<TreeGraphNode> tgns = gs.getNodes();
					Iterator<TypedDependency> it = tdl.iterator();

					SemanticGraph dependencies = new SemanticGraph(tdl, tgns);
					SentenceProcessor.traverse(dependencies.getFirstRoot(), dependencies, 0, it);

					String tokens[] = s.getTokenizedSent();
					int size = tokens.length;
					int word = r.nextInt(size-1);

					String pos = wordnet.getBestPos(tokens[word]);
					//System.out.println(pos);
					//System.out.println(tokens[word]);
					if(tokens[word] != null && pos != null){
						try{

							String[] a = wordnet.getAllSynonyms(tokens[word], pos);

							if(a != null){
								String ss = wordnet.getAllSynonyms(tokens[word], pos)[0];
								tokens[word] = ss;
								System.out.println(tokens[word]);
							}


							for(int j = 0; j < tokens.length; j++){
								out += tokens[j] + " ";
							}
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}else{
						for(int j = 0; j < tokens.length; j++){
							out += tokens[j] + " ";
						}
					}
					System.out.println(out + "\n");*/

		}
		super.setQuestionSet(_questionSet);
		Collections.shuffle(questionAnsPair);
		//shuffle(this.questionAnsSet);

		/*for(String s: c){
					System.out.println(s);
				}*/
		
	}
	
	@Deprecated
	private void shuffle(ArrayList<Pair<Integer, String>> questionAnsSet){
		Random r = new Random();
		String tmpStr = "";
		String randStr = "";
		int tmpInt, randInt = 0;
		
		int size = questionAnsSet.size();
		
		for(int i = 0; i < size; i++){
			int rand1 = r.nextInt(size);
			tmpInt = questionAnsSet.get(i).getLeft();
			tmpStr = questionAnsSet.get(i).getRight();
			
			randInt = questionAnsSet.get(rand1).getLeft();
			randStr = questionAnsSet.get(rand1).getRight();
			
			questionAnsSet.get(i).setLeft(randInt);
			questionAnsSet.get(i).setRight(randStr);
			questionAnsSet.get(rand1).setLeft(tmpInt);
			questionAnsSet.get(rand1).setRight(tmpStr);
			
		}
	}
	
	public ArrayList<Pair<Integer, String>> getQAset(){
		return this.questionAnsPair;
	}

}
