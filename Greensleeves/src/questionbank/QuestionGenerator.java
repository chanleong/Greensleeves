package questionbank;

import essay.Essay;
import essay.Paragraph;
import essay.Sentence;

public interface QuestionGenerator {
	public void questionGen();
	public void questionGen(Essay e);
	public void questionGen(Paragraph p);
	public void questionGen(Sentence s);
}
