package mainmenu;

import javax.swing.JFrame;
import javax.swing.JTable;

public class CreditsMenu {

	public CreditsMenu() {

		JFrame frame = new JFrame("Credits");
		frame.setSize(800, 700);
		frame.setLayout(null);
		frame.setVisible(true);

		String[] columnNames = { "Area", "" };
		Object[][] data = { { "Texturen", "" }, // "headline"
				{ "", "48x48 Block Tiles by Seizawa licensed CC BY 4.0: https://opengameart.org/content/48x48-block-tiles" } };

		JTable table = new JTable(data, columnNames);
		
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(600);
//		table.getSelectedRow()
		table.setBounds(20, 20, 700, 600);
		table.setVisible(true);
		frame.add(table);

	}

}
