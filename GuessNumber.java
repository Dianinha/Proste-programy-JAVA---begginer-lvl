import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		Random r = new Random();
		int numberToGuess = r.nextInt(100)+1;
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int input = -1;
		while (flag) {
			System.out.println("Zgadnij liczbę:");
			try {
				input = sc.nextInt();
				if (input < numberToGuess) {
					System.out.println("Za mało!");
				} else if (input > numberToGuess) {
					System.out.println("Za dużo!");
				} else {
					System.out.println("Zgadłeś!");
					flag = false;
					System.exit(0);
				}

			} catch (InputMismatchException e) {
				System.out.println("To nie jest liczba");
				sc.next();
				continue;
			}

		}
		sc.close();
	}
}
