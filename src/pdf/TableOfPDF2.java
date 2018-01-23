package pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 使用iText生成PDF文件 在PDF文件中创建表格
 */
public class TableOfPDF2 {

	public static void main(String[] args) {
		TableOfPDF2 p001 = new TableOfPDF2();

		String filename = "P0022.pdf";
		p001.createPDF(filename);
	}

	public void createPDF(String filename) {
		// step 1
		
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		//Document document = new Document(PageSize.A4);
		//Document document = new Document(rectPageSize, 65, 65, 60, 60);// 其余4个参数，设置了页面的左右上下4个边距
		Document document = new Document(rectPageSize.rotate());
		
		//document.setPageSize(rectPageSize.rotate());
		//document.newPage();
		//如果不设置 空白页无法单独显示
		//writer.setPageEmpty(false);
		
		// step 2
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filename));

			document.addTitle("ID.NET");
			document.addAuthor("dotuian");
			document.addSubject("This is the subject of the PDF file.");
			document.addKeywords("This is the keyword of the PDF file.");

			// step 3
			document.open();
			// step 4
			PdfPTable table = createTable1();
			document.add(table);

			table = createTable2();
			//table.setSpacingBefore(5);
			//table.setSpacingAfter(5);
			document.add(table);

			table = createTable6();
			document.add(table);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			// step 5
			document.close();
		}
	}

	/**
	 * Creates a table; widths are set with setWidths().
	 * 
	 * @return a PdfPTable
	 * @throws DocumentException
	 */
	public static PdfPTable createTable1() throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(288 / 5.23f);
		table.setWidths(new int[] { 2, 1, 1 });

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Table 1"));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		return table;
	}

	/**
	 * Creates a table; widths are set with setWidths().
	 * 
	 * @return a PdfPTable
	 * @throws DocumentException
	 */
	public static PdfPTable createTable2() throws DocumentException {
		PdfPTable table = new PdfPTable(3);
		table.setTotalWidth(288);
		table.setLockedWidth(true);
		table.setWidths(new float[] { 2, 1, 1 });
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Table 2"));
		cell.setColspan(3);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		return table;
	}

	public static PdfPTable createTable6() throws DocumentException {
		PdfPTable table = new PdfPTable(10);
		table.setTotalWidth(595);
		// table.setLockedWidth(true);

		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Table 6"));
		cell.setColspan(10);
		table.addCell(cell);

		for (int i = 1; i < 100; i++) {
			cell = new PdfPCell(new Phrase(String.valueOf(i)));
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
		}

		// cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		// cell.setRowspan(2);
		// table.addCell(cell);
		// table.addCell("row 1; cell 1");
		// table.addCell("row 1; cell 2");
		// table.addCell("row 2; cell 1");
		// table.addCell("row 2; cell 2");
		return table;
	}

}
