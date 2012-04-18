import questionbank.*;


public class mainclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		tagparsing tp = new tagparsing("The quick brown fox jumps over the lazy dog.");
		System.out.println(tp.getTypeOfWord("dog"));
		System.out.println(tp.getTypeOfWord("hello"));
		Integer[] nouns = tp.getNouns();
		for (Integer i : nouns)
			System.out.println(i.toString());
			
		*/
		try{
			Paraphraser p = new Paraphraser("While emissions from new cars are far less harmful than they used to be, city streets and motorways are becoming more crowded than ever, often with older trucks, buses and taxis which emit excessive levels of smoke and fumes.");
			p.setChanges(false, true, false, false, 0.7);
			System.err.println("paraphrase");
			System.out.println(p.paraphrase());
		
		}catch(Exception e){}
	}

}
