package homework01;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class HelloWorld {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name : ");
		String name = scanner.nextLine();
		System.out.println("Hello, "+name+"!");
		scanner.close();
		JOptionPane.showMessageDialog(null, "Hello World");

	}
}
