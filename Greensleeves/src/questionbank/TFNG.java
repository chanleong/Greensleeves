package questionbank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import rita.wordnet.RiWordnet;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeGraphNode;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;
import essay.Essay;
import essay.FactEvaluator;
import essay.Paragraph;
import essay.Ranker;
import essay.Sentence;

/**
 * True fals not given
 * @author Raymond
 *
 */
public class TFNG extends Question{
	
	//0:fakse, 1:true, 2:Not Given
	private Pair<String, Integer> questionAnsSet;
	
	private boolean ans;
	private boolean posRestructure;
	private boolean posSub;
	
	public TFNG(){
		
	}
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
//		ArrayList<String> facts = FactEvaluator.getAllUniqueFacts(e.toString());
//		int size = facts.size();
//		boolean haveSent = false;
//		if(size > 0){
//			while(!haveSent){
//				Random r = new Random();
//				//Choose a random fact
//				int chosenFact = r.nextInt(size);
//				ArrayList<Sentence>sents = FactEvaluator.getSentenceAbout(facts.get(chosenFact), e);
//				haveSent = genSents(sents);
//			}
//			System.out.println(SentenceProcessor.sents);
//			
//		}else{
//			while(!haveSent){
//				Random r = new Random();
//				int chosenParagraph = r.nextInt(e.getNumOfParas());
//				Paragraph p = e.getParagraph(chosenParagraph);
//				Ranker ranker = new Ranker();
//				ArrayList<Sentence> sents = ranker.getRankedSentences(p);
//				haveSent = genSents(sents);
//			}
//			System.out.println(SentenceProcessor.sents);
//		}
		
		
	}

	@Override
	public void questionGen(Paragraph p) {
		// TODO Auto-generated method stub
//		Ranker r = new Ranker();
//		ArrayList<Sentence> sents = r.getRankedSentences(p);
//		
//		genSents(sents);
//		
//		System.out.println(SentenceProcessor.sents);
		
		
		
	}
	
	@Override
	public void questionGen(Sentence s) {
		// TODO Auto-generated method stub
		String tokens[] = s.getTokenizedSent();
		Random r = new Random();
		int pickedSent = -1;
		
		StringBuffer sb = new StringBuffer("");
		
		substitute(tokens);
		
		for(int i = 0; i < tokens.length; i++){
			sb.append(tokens[i]);
			sb.append(" ");
		}
		ArrayList<Sentence>test = new ArrayList<Sentence>();
		
		test.add(new Sentence(sb.toString()));
		
		System.out.println(test);
		
		genSents(test);
		
		pickedSent = r.nextInt(SentenceProcessor.sents.size());
		String sent = SentenceProcessor.sents.get(pickedSent).toString();
		
		this.ans = this.posRestructure && this.posSub;
		int _ans = (this.ans)? 1 : 0;
		this.questionAnsSet = new Pair<String, Integer>(sent, _ans);
		
		
		System.out.println(this.questionAnsSet);
		//System.out.println(sb.toString());
		
		
	}
	
	private void substitute(String[] tokens){
		Random r = new Random();
		boolean getTrue = r.nextBoolean();
		RiWordnet rw = LibraryInitializer.WORDNET;
		
		for(int i = 0; i < tokens.length; i++){
			boolean isVerb = rw.isVerb(tokens[i]);
			if(isVerb){
				if(getTrue){
					String[] syn = rw.getAllSynonyms(tokens[i], "v");
					if(syn != null){
						tokens[i] = syn[0];
						this.posSub = true;
					}
				}else{
					String[] syn = rw.getAllAntonyms(tokens[i], "v");
					if(syn != null){
						tokens[i] = syn[0];
						this.posSub = false;
					}
				}
			}
		}
	}
	
	private boolean genSents(ArrayList<Sentence> sents){
		int size = sents.size();
		Random r = new Random();
		boolean setNeg = r.nextBoolean();
		
		for(int i = 0; i < size; i++){
			TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		    Tree parse = LibraryInitializer.LP.apply(sents.get(i).toString());				
			GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		    Collection<TypedDependency> c = gs.typedDependenciesCCprocessed(true);
		    Collection<TreeGraphNode> tn = gs.getNodes();
		    SemanticGraph dependency = new SemanticGraph(c, tn);
		    Iterator<TypedDependency> it = c.iterator();
			
		    if(setNeg){
		    	SentenceProcessor.negatedTraverse(dependency.getFirstRoot(), dependency, 0, it);
		    	this.posRestructure = false;
		    }else{
		    	SentenceProcessor.traverse(dependency.getFirstRoot(), dependency, 0, it);
		    	this.posRestructure = true;
		    }
		    if(SentenceProcessor.sents != null) break;
		}
		if(SentenceProcessor.sents.size() != 0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @return The question and answer pair. Left is question, right is answer
	 */
	public Pair<String, Integer> getAnsPair(){
		return this.questionAnsSet;
	}

}
