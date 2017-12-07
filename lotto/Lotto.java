package lotto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int usersNumbersCount = 0;
		int howManyGuesses=0;
		List<Integer> usersNumbers = new ArrayList<>();
		System.out.println("UWAGA - podawaj liczby całkowite z zakresu 1-49");
		while (usersNumbersCount < 6) {
			System.out.println("Podaj liczę, na którą stawiasz:");
			String input = sc.nextLine();
			Integer tmpNumber = -1;
			try {
				tmpNumber = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Zły format danych");
				continue;
			}

			if (tmpNumber < 50 && tmpNumber > 0) {
				if (usersNumbers.contains(tmpNumber)) {
					System.out.println("hej, hej, tę liczbę już podałeś/łaś!");
				} else {
					usersNumbers.add(tmpNumber);
					usersNumbersCount++;
				}

			} else {
				System.out.println("Proszę podać liczbę z zakresu 1-49");
			}

		}
		sc.close();
		System.out.print("Twoje SZCZĘŚLIWE LICZBY to: ");
		Collections.sort(usersNumbers);
		for (Integer integer : usersNumbers) {
			System.out.print(integer + " ");
		}
		System.out.println();
		List<Integer> lottoLuckyNumbers = new ArrayList<>();
		//Testy:
//		lottoLuckyNumbers.add(1);
//		lottoLuckyNumbers.add(2);
//		lottoLuckyNumbers.add(3);
//		lottoLuckyNumbers.add(4);
//		lottoLuckyNumbers.add(5);
//		lottoLuckyNumbers.add(6);
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			Integer lottoNum = r.nextInt(49) + 1;
			if (lottoLuckyNumbers.contains(lottoNum)) {
				i--;
			} else {
				lottoLuckyNumbers.add(lottoNum);
			}
		}
		System.out.print("Wylosowane liczby to: ");
		Collections.sort(lottoLuckyNumbers);
		for (Integer integer : lottoLuckyNumbers) {
			System.out.print(integer + " ");
		}
		System.out.println();
		for (int i = 0; i < usersNumbers.size(); i++) {
			Integer tmpNumber = usersNumbers.get(i);
			if(lottoLuckyNumbers.contains(tmpNumber)){
				howManyGuesses++;
			}
		}
		if (howManyGuesses==3){
			System.out.println("Brawo! Udało Ci się trafić trójkę!");
		}
		else if (howManyGuesses==4) {
			System.out.println("Brawo! Udało Ci się trafić czwórkę!");
		}
		else if (howManyGuesses==5) {
			System.out.println("Brawo! Udało Ci się trafić piątkę!");
		}
		else if (howManyGuesses==6) {
			System.out.println("BRAWO SZÓSTKA JESTEŚ MILIONEREM! GRATULACJE!");
		}
		else {
			System.out.println("Niestety, nie masz nawet trójki. Może następnym razem pójdzie Ci lepiej.");
		}
	}

}