package guess.number2;

import java.util.Scanner;

public class GuessNumber_2 {

	public static void main(String[] args) {
		System.out.println("Pomyśl liczbę od 0 do 1000, a ja ją zgadnę w max. 10 próbach");
		int min = 0, max = 1000;
		int count = 0;
		//dodałam ilość ruchów

		Scanner sc = new Scanner(System.in);

		boolean flag = true;
		do {
			count++;
			int guess = (int) ((max - min) / 2) + min;
			System.out.println("Zgaduję: " + guess);
			System.out.println("Trafiłem? [T/N]");
			String answer = sc.nextLine();
			if (answer.equals("T")) {
				System.out.println("Wygrałem!");
				System.out.println("Ilość ruchów: " + count);
				sc.close();
				flag = false;
				System.exit(0);
			} else if (answer.equals("N")) {
				System.out.println("Za dużo? [T/N]");
				answer = sc.nextLine();
				if (answer.equals("T")) {
					max = guess;

				} else if (answer.equals("N")) {
					System.out.println("Za mało? [T/N]");
					answer = sc.nextLine();
					if (answer.equals("T")) {
						min = guess;
					} else if (answer.equals("N")) {
						System.out.println("Nie oszukuj!");
						count--;
					} else {
						System.out.println("Nie rozpoznaję komendy.");
						count--;
					}

				} else {
					System.out.println("Nie rozpoznaję komendy.");
					count--;
				}

			} else {
				System.out.println("Nie rozpoznaję komendy.");
				count--;

			}
		} while (flag);

	}

}
