package essay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Essay {
	
	private ArrayList<Paragraph> paragraphs;
	private String header;
	private String content;
	
	/**
	 * 
	 * @param path Text file location
	 */
	public Essay(String path){
		paragraphs = new ArrayList<Paragraph>();
		File file = new File(path);
		
		
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String para;
			content = "";
			boolean isHeader = true; //The first line is a header
			
			int i = 0;
			while((para = br.readLine())!=null){
				content = content + para;
				if(isHeader){
					this.header = para;
					isHeader = false;
				}else{
					paragraphs.add(new Paragraph(para, i));
					i++;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return Header of the sentence, assuming first line is a header
	 */
	public String getHeader(){
		return this.header;
	}
	
	/**
	 * 
	 * @return Set of paragraphs
	 */
	public ArrayList<Paragraph> getParagraphs(){
		return this.paragraphs;
	}
	
	/**
	 * 
	 * @param i Index of paragraph
	 * @return String of the paragraph
	 */
	public String getParagraphStr(int i){
		return this.paragraphs.get(i).getParagraph();
	}
	
	/**
	 * 
	 * @param i Index of paragraph
	 * @return Paragraph class of the paragraph
	 */
	public Paragraph getParagraph(int i){
		return this.paragraphs.get(i);
	}
	
	/**
	 * 
	 * @return Number of paragraphs in the essay
	 */
	public int getNumOfParas(){
		return this.paragraphs.size();
	}
	
	@Deprecated
	public String getEssayStr(){
		return this.content;
	}
	
	@Override
	public String toString(){
		return this.content;
	}
}
