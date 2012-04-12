package questionbank;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.dom4j.Document;
import org.dom4j.Element;

import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

import com.alchemyapi.api.AlchemyAPI;

import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.trees.EnglishGrammaticalRelations;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.semgraph.SemanticGraph;

public class SentenceProcessor {
	static Stack<String> subj = new Stack<String>();
	static Stack<String> verb = new Stack<String>();
	static Stack<String> obj = new Stack<String>();

	public static void traverse(IndexedWord iw, SemanticGraph dependency,
			int depth, Iterator<TypedDependency> tdl) {
		if (depth == 0) {
			subj.clear();
			verb.clear();
			obj.clear();
			System.out.println(iw);
		}
		if (!tdl.hasNext()) {
			return;
		}

		String s = "->";

		for (int i = 0; i < depth; i++) {
			s += "->";
		}

		List<IndexedWord> cl = dependency.getChildList(iw);
		// System.out.println(s+ cl.toString());
		IndexedWord tmp = new IndexedWord();

		for (int i = 0; i < cl.size(); i++) {
			tmp = cl.get(i);
			String str = tmp.toString();
			s += str + " ";

			int nodeIdx = tmp.index();
			IndexedWord tmpNode = dependency.getNodeByIndex(nodeIdx);

			/*
			 * if(dependency.hasChildWithReln(tmpNode,
			 * EnglishGrammaticalRelations.ADJECTIVAL_MODIFIER)){
			 * System.out.println(dependency.getChildrenWithReln(tmpNode,
			 * EnglishGrammaticalRelations.ADJECTIVAL_MODIFIER)); }
			 */
			if (tdl.hasNext()) {
				Lexicon lexicon = Lexicon.getDefaultLexicon();
				NLGFactory nlgFactory = new NLGFactory(lexicon);
				Realiser realiser = new Realiser(lexicon);

				SPhraseSpec p = nlgFactory.createClause();

				TypedDependency td = (TypedDependency) tdl.next();
				GrammaticalRelation gr = td.reln();
				int depIdx = td.dep().index();
				int govIdx = td.gov().index();

				if (gr.equals(EnglishGrammaticalRelations.DIRECT_OBJECT)) {
					IndexedWord depIw = dependency.getNodeByIndex(depIdx);
					IndexedWord govIw = dependency.getNodeByIndex(govIdx);
					List<IndexedWord> dep = dependency.getChildrenWithReln(
							depIw,
							EnglishGrammaticalRelations.ADJECTIVAL_MODIFIER);
					if (dep.size() > 0) {
						p.addModifier(dep.get(0).value());
					}

					// p.setSubject("Bell");
					p.setVerb(td.gov().value());

					p.setObject(td.dep().value());

				} else if (gr
						.equals(EnglishGrammaticalRelations.NOMINAL_SUBJECT)) {
					IndexedWord depIw = dependency.getNodeByIndex(depIdx);
					p.setSubject(depIw.value());
				}

				String _str = realiser.realiseSentence(p);
				System.out.println(_str);

			}

			// System.out.println(td);

			// System.out.println(s);
			// System.out.println(s);

			traverse(tmp, dependency, depth + 1, tdl);
		}

	}

	/**
	 * 
	 * @param sentence A string of sentence
	 * @return An array with Subject Action and Object, Tense and whether is negated. separated by comma
	 * @throws Exception
	 */
	public static String[] extractSAO(String sentence) throws Exception 
	{
		AlchemyAPI alchemyObj = AlchemyAPI
				.GetInstanceFromFile("lib/api_key.txt");

		org.w3c.dom.Document w3cdoc = alchemyObj.TextGetRelations(sentence);
		Document doc = XMLReader.docConverter(w3cdoc);
		List nodeList = doc.selectNodes("//results/relations/relation");
		StringBuffer _SAO = new StringBuffer("");
		ArrayList<String> SAO = new ArrayList<String>();
		
		if(nodeList != null){
			for(Iterator i = nodeList.iterator(); i.hasNext();){
				Element e = (Element)i.next();
				_SAO.append(XMLReader.getRelation(Features.SUBJECT, e));
				_SAO.append(",");
				_SAO.append(XMLReader.getRelation(Features.ACTION, e));
				_SAO.append(",");
				_SAO.append(XMLReader.getRelation(Features.OBJECT, e));
				_SAO.append(",");
				_SAO.append(XMLReader.getVerbFeature(Features.TENSE, e));
				_SAO.append(",");
				_SAO.append(XMLReader.getVerbFeature(Features.NEGATED, e));
				SAO.add(_SAO.toString());
				_SAO = new StringBuffer("");
				
			}
		}
		
		String[] _SAOArr = new String[SAO.size()];
		
		for(int i = 0; i < _SAOArr.length; i++){
			_SAOArr[i] = SAO.get(i);
		}
		
		return _SAOArr;
	}
}
