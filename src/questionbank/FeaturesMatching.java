package questionbank;

import rita.wordnet.RiWordnet;
import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

public class FeaturesMatching extends Question {

	@Override
	public void questionGen() {
		// TODO Auto-generated method stub
		RiWordnet rw = LibraryInitializer.WORDNET;
		System.out.println(rw.getBestPos("Happy"));
	}

	@Override
	public void questionGen(Essay e) {
		// TODO Auto-generated method stub
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
