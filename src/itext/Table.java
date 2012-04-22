package itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class Table {
	PdfPTable table;
	public Table(){
		
			table = new PdfPTable(2);
		try{
			float[] w = {1f, 12f};
			table.setWidths(w);
			table.setWidthPercentage(105f);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Table(int col,float percent){
		table = new PdfPTable(col);
		try{
			float[] w = {1f, 12f};
			table.setWidths(w);
			table.setWidthPercentage(percent);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void add_cells(Paragraph para, char tag){
		Font font_section = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
		PdfPCell cell1 = new PdfPCell(new Phrase(tag+"",font_section));
		PdfPCell cell2 = new PdfPCell(para);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorder(Rectangle.NO_BORDER);
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setPaddingBottom(15);
		cell2.setPaddingRight(20);
		cell2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		table.addCell(cell1);
		table.addCell(cell2);
	}
	public void add_cells2(Phrase phr, int num){
		Font font_section = new Font(Font.FontFamily.UNDEFINED, 12, Font.BOLD);
		PdfPCell cell1 = new PdfPCell(new Phrase(num+"",font_section));
		PdfPCell cell2 = new PdfPCell(phr);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell1.setBorder(Rectangle.NO_BORDER);
		cell2.setBorder(Rectangle.NO_BORDER);
		cell2.setPaddingBottom(15);
		cell2.setPaddingRight(20);
		table.addCell(cell1);
		table.addCell(cell2);
	}
	
	public PdfPTable getTable(){
		return table;
	}
  public static void main(String[] args) {
    Document document = new Document();

    try {
      PdfWriter.getInstance(document,
          new FileOutputStream("Table2.pdf"));

      document.open();
      Table abc = new Table();
      Paragraph para = new Paragraph("1111111111111");
      abc.add_cells(para,'A');
      

      document.add(abc.getTable());

      document.close();

    } catch(Exception e){
      e.printStackTrace();
    }
  }
}

