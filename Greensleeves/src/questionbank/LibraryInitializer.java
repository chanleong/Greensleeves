package questionbank;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import rita.wordnet.RiWordnet;
import simplenlg.lexicon.Lexicon;

/**
 * Initialize the libraries
 * @author Raymond
 *
 */
public class LibraryInitializer {
	public static RiWordnet WORDNET;
	public static LexicalizedParser LP;
	public static AbstractSequenceClassifier<CoreLabel> CLASSIFIER;
	private String serializedClassifier = "lib/classifiers/english.muc.7class.distsim.crf.ser.gz";
	
	public static Lexicon LEXICON;
	
	public LibraryInitializer(){
		WORDNET = new RiWordnet();
		LP = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		CLASSIFIER = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
		LEXICON = Lexicon.getDefaultLexicon();
	}
	
	
}
