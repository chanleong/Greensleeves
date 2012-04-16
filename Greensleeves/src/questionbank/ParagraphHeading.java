package questionbank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

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

public class ParagraphHeading extends Question{
	
	
	
	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
		int numOfParas = e.getNumOfParas();
		int numOfSent;
		Random r = new Random();
		
		for(int i = 0; i < numOfParas; i++){
			Paragraph p = e.getParagraph(i);
			numOfSent = p.getNumOfSents();
			
			for(int j = 0; j < numOfSent; j++){
				Sentence s = p.getSentence(j);
				TreebankLanguagePack tlp = new PennTreebankLanguagePack();
			    GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
			    Tree parse = LibraryInitializer.LP.apply(s.toString());				
				GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
			    Collection<TypedDependency> c = gs.typedDependenciesCCprocessed(true);
			    Collection<TreeGraphNode> tn = gs.getNodes();
			    SemanticGraph dependency = new SemanticGraph(c, tn);
			    Iterator<TypedDependency> it = c.iterator();
				
			    SentenceProcessor.traverse(dependency.getFirstRoot(), dependency, 0, it);
				
			}
			int chosenIdx = chooseSent(SentenceProcessor.sents);	
			
			System.out.println(SentenceProcessor.sents.get(chosenIdx));
			SentenceProcessor.sents.clear();
			System.out.println("Paragraph: " + i);
		}
		
		
	}
	
	//A more weighted sentence will have a higher chance to be chosen as the core idea of the paragraph
	private int chooseSent(ArrayList<String> sents){
		int size = sents.size();
		int[] weight = new int[size];
		int totalLength = 0;
		int chosenIdx = 0;
		Random r = new Random();
		double rand = r.nextDouble();
		
		for(int j = 0; j < size; j++){
			weight[j] = sents.get(j).length();
			totalLength += weight[j];
		}
		
		for(int j = 0; j < size; j++){
			weight[j] = weight[j]/totalLength;
		}
		
		//A more weighted sentence will have a higher chance to be chosen
		for(int j = 0; j < size; j++){
			if(weight[j] > rand)
				chosenIdx = j;
		}
		
		return chosenIdx;
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
