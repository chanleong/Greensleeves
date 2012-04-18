package essay;

import com.itextpdf.text.*;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import questionbank.Question.QuestionType;

public class ExamPaper {
	private Essay[] essays;
	private Document doc;
	private String docName;
	
	private Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);
	private Font italicFont = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
	private Font instructFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC);
	private Font boldQuestionNum = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.ITALIC|Font.BOLD);
	
	/**
	 * An an exam paper only consist of 3 essays
	 */
	public ExamPaper(Essay[] essays, String docName){
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
		fillHeader(0, 1, 13);
		Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD|Font.UNDERLINE);
	    Font sectionFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		fillEssay(this.essays[0], 0, headingFont, sectionFont, false);
		fillQuestionHeading(0, QuestionType.TFNG, 13, 25);
		
		this.doc.close();
	}
	
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
	
	private void fillQuestionHeading(int essayNum, QuestionType qType, 
			int startQ, int endQ)
	{
		int realEssayNum = essayNum + 1;
		try{
			
			if(qType == QuestionType.InfoIdentification){
				
			}else if(qType == QuestionType.ParagraphHeading){
				
			}else if(qType == QuestionType.MCQ){
				
			}else if(qType == QuestionType.TFNG){
				Font tfngFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLDITALIC);
				
				addQuestionNum(startQ, endQ);
				
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
				
				trueInstruct.add(new Phrase("TRUE", tfngFont));
				trueInstruct.add(new Phrase("    if the statement agrees with the information", italicFont));
				trueInstruct.setIndentationLeft(20);
				
				falseInstruct.add(new Phrase("FALSE", tfngFont));
				falseInstruct.add(new Phrase("    if the statement contradicts the information", italicFont));
				falseInstruct.setIndentationLeft(20);
				
				ngInstruct.add(new Phrase("NOT GIVEN", tfngFont));
				ngInstruct.add(new Phrase("    if there is no information on this", italicFont));
				ngInstruct.setIndentationLeft(20);
				
				this.doc.add(instruction);
				this.doc.add(instruction2);
				this.doc.add(trueInstruct);
				this.doc.add(falseInstruct);
				this.doc.add(ngInstruct);
			}
			
		}catch(Exception e){
			
		}
	}
	
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
