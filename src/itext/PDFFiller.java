package itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import essay.Essay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import questionbank.*;
import questionbank.Question.QuestionType;

public class PDFFiller {
	private Essay[] essays;
	private Document doc;
	private String docName;
	private ArrayList<Question> questionList;
	
	//Common font
	private Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
	private Font italicFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
	private Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	private Font instructFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC);
	private Font boldQuestionNum = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC|Font.BOLD);
	private Font boldItalic = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLDITALIC);
	
	/**
	 * An an exam paper only consist of 3 essays
	 */
	public PDFFiller(Essay[] essays, String docName, ArrayList<Question> questionList){
		this.essays = essays; 
		this.docName = docName;
		this.doc = new Document(PageSize.A4, 60, 60, 60, 60);
		
	
		
		try {
			PdfWriter.getInstance(this.doc,
					new FileOutputStream(docName + ".pdf"));
			doc.open();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public PDFFiller(Essay[] essays, String docName){
		this.essays = essays; 
		this.docName = docName;
		this.doc = new Document(PageSize.A4, 60, 60, 60, 60);
		
	
		
		try {
			PdfWriter.getInstance(this.doc,
					new FileOutputStream(docName + ".pdf"));
			doc.open();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public Essay getEssay(int i){
		return this.essays[i];
	}
	
	public void generate(){
		//Passage 1
		fillHeader(0, 1, 13);
		Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD|Font.UNDERLINE);
	    Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		fillEssay(this.essays[0], 0, headingFont, sectionFont, false);
		this.doc.newPage();
		
		int size = this.questionList.size();
		for(int i = 0; i < size; i++){
			Question q = this.questionList.get(i);
			if(q instanceof InfoIdentification){
				InfoIdentification ii = (InfoIdentification)q;
				((InfoIdentification) q).getQuestionAnsPair();
			}
			
		}
		
		fillQuestionHeading(0, QuestionType.InfoIdentification, 1, 13);
		fillQuestionHeading(0, QuestionType.ParagraphHeading, 1, 13);
		fillQuestionHeading(0, QuestionType.TFNG, 1, 13);
		
		this.doc.close();
	}
	
	//Filling the header of the passage, 1. Essay number 2.Starting question 3.Ending question
	private void fillHeader(int essayNum, int startQ, int endQ){
		Font readingPassageFont = new Font(Font.FontFamily.HELVETICA, 17, Font.BOLD);
		
		
		int realEssayNum = essayNum + 1;
		
		try{
			Paragraph _readingPassageHeader = new Paragraph();
			_readingPassageHeader.add(new Phrase("READING PASSAGE " + realEssayNum, readingPassageFont));
			_readingPassageHeader.setSpacingAfter(10);
			
			this.doc.add(_readingPassageHeader);
			
			Paragraph instruction = new Paragraph();
			instruction.add(new Phrase("You should spend about 20 minutes on ", instructFont));
			instruction.add(new Phrase("Question "+ startQ +"-" + endQ+" ", boldQuestionNum));
			instruction.add(new Phrase("which are based on Reading Passage "+ realEssayNum  + " below.", instructFont));
			instruction.setSpacingAfter(30);
			
			this.doc.add(instruction);
			
			
		}catch(Exception de){
			de.printStackTrace();
		}
	}
	
	//Filling the essay inside
	private void fillEssay(Essay e, int essayNum, 
			Font titleFont, Font sectionFont, boolean showSection)
	{		
		String heading = e.getHeader().toUpperCase();
		int numOfParagraphs = e.getNumOfParas();
		
		 Paragraph _heading = new Paragraph();
		 _heading.add(new Phrase(heading, titleFont));
		 _heading.setLeading(25);
		 _heading.setSpacingAfter(30);
		 _heading.setAlignment(Element.ALIGN_CENTER);
		 
		 try {
			 doc.add(_heading);
		 } catch (DocumentException de) {
			 // TODO Auto-generated catch block
			 de.printStackTrace();
		 }
		
		for(int i = 0; i < numOfParagraphs; i++){
			newParagraph(e.getParagraphStr(i), sectionFont, i, showSection);
		}
	}
	
	//Fill in paragraph
	private void newParagraph(String paragraphStr, Font sectionFont, 
			int paragraphNum, boolean showSection)
	{
		Paragraph paragraph = new Paragraph();
		Phrase section;
		paragraph.add(paragraphStr);
		paragraph.setSpacingAfter(30);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		
		if(showSection) section = new Phrase("Section "+(char)(65+paragraphNum),sectionFont);
		else section = new Phrase(""+(char)(65+paragraphNum),sectionFont);
		
		try {
			this.doc.add(section);
			this.doc.add(paragraph);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Fill question heading
	private void fillQuestionHeading(int essayNum, QuestionType qType, 
			int startQ, int endQ)
	{
		int realEssayNum = essayNum + 1;
		try{
			addQuestionNum(startQ, endQ);
			
			if(qType == QuestionType.InfoIdentification){
				Paragraph[] instructions = new Paragraph[4];
				for(int i = 0; i < instructions.length; i++) instructions[i] = new Paragraph();
				
				
				int numOfParas = this.essays[essayNum].getNumOfParas();
				char startChar = Question.getQuestionCharacter(0);
				char endChar = Question.getQuestionCharacter(numOfParas-1);
				
				instructions[0].add(new Phrase("Reading Passage " + realEssayNum + " has " + numOfParas 
						+ " paragraphs labelled " ));
				instructions[0].add(new Phrase(startChar + "-" + endChar , boldFont));
				instructions[0].setSpacingAfter(10);
				
				instructions[1].add(new Phrase("Which paragraphs contains the following information?"));
				instructions[1].setSpacingAfter(10);
				
				instructions[2].add(new Phrase("Write the correct letter ", italicFont));
				instructions[2].add(new Phrase(startChar + "-" + endChar , boldItalic));
				instructions[2].add(new Phrase(" in boxes " + startQ + "-" + endQ + " on your answer sheet.", italicFont));
				
				
				instructions[3].add(new Phrase("NB ", boldItalic));
				instructions[3].add(new Phrase("You may use any letter more than once.", italicFont));
				instructions[3].setSpacingAfter(10);
				
				for(int i = 0; i < instructions.length; i++) this.doc.add(instructions[i]);
				
			}else if(qType == QuestionType.ParagraphHeading){
				Paragraph[] instructions = new Paragraph[3];
				for(int i = 0; i < instructions.length; i++) instructions[i] = new Paragraph();
				
				int numOfParas = this.essays[essayNum].getNumOfParas();
				char startChar = Question.getQuestionCharacter(0);
				char endChar = Question.getQuestionCharacter(numOfParas-1);
				
				instructions[0].add(new Phrase("Reading Passage " + realEssayNum + " has " + numOfParas 
						+ " sections, " ));
				instructions[0].add(new Phrase(startChar + "-" + endChar , boldFont));
				instructions[0].setSpacingAfter(10);
				
				instructions[1].add(new Phrase("Choose the correct heading for sections  ", italicFont));
				instructions[1].add(new Phrase(startChar + "-" + endChar, boldItalic));
				instructions[1].add(new Phrase(" from the list of headings below.", italicFont));
				instructions[1].setSpacingAfter(10);
				
				instructions[2].add(new Phrase("Write the correct number " + startQ + "-" + endQ + " in boxes "
						+ startQ + "-" + endQ +" on your answer sheet.", italicFont));
				instructions[2].setSpacingAfter(10);
				
				for(int i = 0; i < instructions.length; i++) this.doc.add(instructions[i]);
				
			}else if(qType == QuestionType.MCQ){
				
			}else if(qType == QuestionType.TFNG){
				
				Paragraph instruction = new Paragraph();
				Paragraph instruction2 = new Paragraph();
				Paragraph trueInstruct = new Paragraph();
				Paragraph falseInstruct = new Paragraph();
				Paragraph ngInstruct = new Paragraph();
				
				
				
				instruction.add(new Phrase("Do the following statement agree with the " +
						"information given in Reading Passage " + realEssayNum + "?"));
				instruction.setSpacingAfter(10);
				
				instruction2.add(new Phrase("In boxes " + startQ + "-" + endQ + " on your answer sheet, write", italicFont));
				instruction2.setSpacingAfter(10);
				
				trueInstruct.add(new Phrase("TRUE", boldItalic));
				trueInstruct.add(new Phrase("    if the statement agrees with the information", italicFont));
				trueInstruct.setIndentationLeft(20);
				
				falseInstruct.add(new Phrase("FALSE", boldItalic));
				falseInstruct.add(new Phrase("    if the statement contradicts the information", italicFont));
				falseInstruct.setIndentationLeft(20);
				
				ngInstruct.add(new Phrase("NOT GIVEN", boldItalic));
				ngInstruct.add(new Phrase("    if there is no information on this", italicFont));
				ngInstruct.setIndentationLeft(20);
				
				this.doc.add(instruction);
				this.doc.add(instruction2);
				this.doc.add(trueInstruct);
				this.doc.add(falseInstruct);
				this.doc.add(ngInstruct);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void fillInfoIdentification(int qNum, String info){
		Paragraph paragraph = new Paragraph();
		
		
	}
	
	//Add the "Question x-y" headings
	private void addQuestionNum(int startQ, int endQ){
		Paragraph questionNum = new Paragraph();
		questionNum.add(new Phrase("Question " + startQ + "-" + endQ, instructFont));
		questionNum.setSpacingAfter(10);
		try {
			this.doc.add(questionNum);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
