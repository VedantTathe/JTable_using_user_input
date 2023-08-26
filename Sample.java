import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class Sample extends JFrame implements ActionListener
{
	private JPanel p1,p2;
	private JTable tbl;
	private DefaultTableModel dtm;
	private JScrollPane sp;
	private Vector <String> cols;
	private Vector <Vector> rows;
	private JButton b;

	public Sample()
	{

		this.setLayout(null);

		p1 = new JPanel();
		p1.setBounds(20, 20, 450, 350);
		sp = new JScrollPane(p1);
		this.add(p1);
		p1.setLayout(new GridLayout(1,1));

		cols = new Vector<String>();
		rows = new Vector<Vector>();
		dtm = new DefaultTableModel(rows, cols);
		tbl = new JTable(dtm);
		sp = new JScrollPane(tbl);
		p1.add(sp);

		p2 = new JPanel();
		p2.setBounds(20, 410, 450, 35);
		this.add(p2);
		p2.setLayout(new GridLayout(1,2,10,0));

		b = new JButton("ADD COLUMN");
		b.addActionListener(this);
		p2.add(b);

		b = new JButton("INSERT DATA");
		b.addActionListener(this);
		p2.add(b);
		


		this.setVisible(true);
		this.setSize(500, 500);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		try{			
			Thread.sleep(500);
		}
		catch(Exception ex){}
		addCol();

		try{			
			Thread.sleep(500);
		}
		catch(Exception ex){}
		addRows();
	}

	public void addCol()
	{
		try
		{
			int col_count = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Number of columns you want to add: "));

			for(int i=1;i<=col_count;i++)
			{
			
				String col_name = JOptionPane.showInputDialog(this, "Enter Name of Column "+i);
				cols.add(col_name);

			}

			dtm.setDataVector(rows, cols);

		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}

	}

	public void addRows()
	{

		try
		{
		int col_count = dtm.getColumnCount();
		Vector<String> temp = new Vector<String>();

		for(int i=0;i<col_count;i++)
		{
			
			String data = JOptionPane.showInputDialog(this, "Enter Data for "+dtm.getColumnName(i));
			temp.add(data);

		}

		rows.add(temp);
		dtm.setDataVector(rows, cols);

		int result = JOptionPane.showConfirmDialog(this, "Do you want to insert another record?","Answer the Following", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION)
		{
			addRows();
		}

		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String text = e.getActionCommand();

		switch(text)
		{
			case "ADD COLUMN":
				addCol();
				break;
			case "INSERT DATA":
				
				addRows();
				
				break;

		}
	}

	public static void main(String[] args) {
		new Sample();
	}

}