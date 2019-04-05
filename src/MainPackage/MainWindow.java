package MainPackage;

// layout tools
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

// event handlers
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// swing components
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;



@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {
	private JLabel instructionLabel; 
	private JButton calcButton;
	private JTextField payPeriod;
	private JScrollPane tableScrollPane;
	private JTable payTable;
	private String[] colHeaders = {"Day no.", "Daily Pay", "Running Total"};
	private String[][] tableVals;

	
	MainWindow() {
		GridBagConstraints constraints = null;
		
		// initialize instructions label
		instructionLabel = new JLabel("Enter pay period (days):");
		
		// initialize calculate button
		calcButton = new JButton("Calculate Pay");
		calcButton.addActionListener(this);
		
		// initialize pay period text field to only read integer input
		payPeriod = new JTextField();
		payPeriod.setEditable(true);
		payPeriod.setText("");
		
		// set up empty table values
		tableVals = new String[5][3];
		for (int i = 0; i < tableVals.length; ++i) {
			for (int j = 0; j < tableVals[i].length; ++j) {
				tableVals[i][j] = "";
			}
		}
		
		// initialize table with default values
		payTable = new JTable(tableVals, colHeaders);
		payTable.setPreferredScrollableViewportSize(payTable.getPreferredSize());
		
		// create scroll pane with payTable
		tableScrollPane = new JScrollPane(payTable);
		
		
		// apply a GridBagLayout to MainWindow
		setLayout(new GridBagLayout());
		
		// label constraints; add to MainWindow
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 0.5;
//		constraints.weighty = 0.5;
		add(instructionLabel, constraints);
		
		// add text field
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 10, 10, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1.0;
//		constraints.weighty = 0.5;
		add(payPeriod, constraints);

		// add calculate pay button
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 10, 10, 10);
		constraints.fill = GridBagConstraints.EAST;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.weightx = 0.0;
//		constraints.weighty = 0.5;
		add(calcButton, constraints);
		
		// table scroll pane constraints; add to MainWindow
		constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		add(tableScrollPane, constraints);
		
//		setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		int payPeriodValue;
		int dayNum;
		double runningSum = 0; // total paid up until this point
		double dailyPay; // daily pay only
		GridBagConstraints constraints = null;
		
		// get value and ensure it is an integer
		try {
			// get integer value of text field
			payPeriodValue = Integer.parseInt(payPeriod.getText());
		} catch (Exception NumberFormatException) {
			JOptionPane.showMessageDialog(this, "Enter a positive, nonzero integer.");
			return;
		}
		
		// ensure value is positive
		if (payPeriodValue <= 0) {
			JOptionPane.showMessageDialog(this, "Please enter a positive number.");
		} else {
			
			// give tableVals a new length corresponding to the number of days
			tableVals = new String[payPeriodValue][3];
			
			// calculate values
			for (int i = 0; i < payPeriodValue; ++i) {
				dayNum = i + 1;
				dailyPay = (0.01) * Math.pow(2.0, (i));
				runningSum += dailyPay;
				
				
				tableVals[i][0] = String.format("Day #%d", dayNum);

				// use scientific notation for large numbers
				if (runningSum > 1.0e9) {
					tableVals[i][1] = String.format("$%.2e", dailyPay);
					tableVals[i][2] = String.format("$%.2e", runningSum);
				} else {
					tableVals[i][1] = String.format("$%,.2f", dailyPay);
					tableVals[i][2] = String.format("$%,.2f", runningSum);
				}
			}

			// give table new values
			remove(tableScrollPane);
			payTable = new JTable(tableVals, colHeaders);
			tableScrollPane = new JScrollPane(payTable);
//			tableScrollPane.setMaximumSize();
			
			// table data constraints; add to MainWindow
			constraints = new GridBagConstraints();
			constraints.insets = new Insets(10, 10, 10, 10);
			constraints.fill = GridBagConstraints.BOTH;
			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 2;
			constraints.weightx = 0.5;
			constraints.weighty = 0.5;
			add(tableScrollPane, constraints);
			
			// update MainWindow
			revalidate();
			repaint();
		}
		
	}
	
	
}
