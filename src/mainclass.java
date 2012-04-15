
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
			paraphraser p = new paraphraser("The quick brown fox jumps over the lazy dog.");
			p.setChanges(true, false, false, false);
			System.err.println("paraphrase");
			System.out.println(p.paraphrase());
		
		}catch(Exception e){}
	}

}
