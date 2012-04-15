
public class mainclass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		tagparsing tp = new tagparsing("The quick brown fox jumps over the lazy dog.");
		System.out.println(tp.getTypeOfWord("dog"));
		System.out.println(tp.getTypeOfWord("hello"));
		Integer[] nouns = tp.getNouns();
		for (Integer i : nouns)
			System.out.println(i.toString());
	}

}
