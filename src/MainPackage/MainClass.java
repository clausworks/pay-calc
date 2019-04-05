/*
 * Name: Nicholas Brunet
 * Class: CIS 111 (Antelope Valley College)
 * Professor: Alec Winetrobe
 * 
 * Project: Lab 4a
 * Due: 3/15/19
 * 
 * Description: The user enters a number of days (the pay period). On the first
 * day, the user will receive $0.01, and the amount will double each subsequent
 * day. The program displays a table with the day number, the amount paid that
 * day, and the total amount paid at that date.
 */



package MainPackage;
import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		
		MainWindow mainWin = new MainWindow();
				
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);

	}

}
