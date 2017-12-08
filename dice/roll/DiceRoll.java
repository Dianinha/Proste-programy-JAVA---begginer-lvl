package dice.roll;

import java.util.Random;

public class DiceRoll {

	public static void main(String[] args) {
		// diceRoll("2D6+20");
		diceRoll("++d34");
	}

	static int diceRoll(String code) {

		char[] codeChars = code.toCharArray();
		int indexOfD = indexOfD(codeChars);
		int indexOfPlusOrMinus = indexOfPlusOrMinus(codeChars);
		int numberOfRolls = decodeNumberOfRolls(indexOfD, code);
		int diceType = decodeDiceType(indexOfD, indexOfPlusOrMinus, code);
		int numberToAdd = decodeNumberToAdd(indexOfPlusOrMinus, code);

		Random r = new Random();
		int finalresult = 0;
		try {
			for (int i = 0; i < numberOfRolls; i++) {
				int result = r.nextInt(diceType) + 1;
				System.out.println("Rzut kostką D" + diceType + ": " + result);
				finalresult += result;
			}
			if (code.contains("-")) {
				finalresult -= numberToAdd;
			} else {
				finalresult += numberToAdd;
			}

			System.out.println("Finalny wynik to: " + finalresult);
		} catch (Exception e) {
			System.out.println("Ups... Coś poszło nie tak. Sprawdź czy wpisany kod kości jest prawidłowy!");
		}

		return finalresult;
	}

	static int indexOfD(char[] codeChars) {
		int countOfDs = 0;
		int indexOfD = 0;
		for (int i = 0; i < codeChars.length; i++) {
			if (codeChars[i] == 'D') {
				countOfDs++;
				indexOfD = i;
			}
		}
		if (countOfDs == 0) {
			System.out.println("Nieprawidłowy kod. Brak litery \"D\"");
		} else if (countOfDs > 1) {
			System.out.println("Nieprawidłowy kod. Za dużo liter \"D\".");
		}

		return indexOfD;
	}

	static int indexOfPlusOrMinus(char[] codeChars) {
		int countOfPluses = 0;
		int indexOfPlusOrMinus = -1;
		for (int i = 0; i < codeChars.length; i++) {
			if (codeChars[i] == '+') {
				countOfPluses++;
				indexOfPlusOrMinus = i;
			} else if (codeChars[i] == '-') {
				countOfPluses++;
				indexOfPlusOrMinus = i;
			}
		}
		if (countOfPluses == 0) {
			indexOfPlusOrMinus = codeChars.length;
		} else if (countOfPluses > 1) {
			System.out.println("Nieprawidłowy kod. Za dużo znaków \"+\"");
		}
		return indexOfPlusOrMinus;
	}

	static int decodeNumberOfRolls(int indexOfD, String code) {
		String howManyRolls = code.substring(0, indexOfD);
		int numberOfRolls = 1;
		try {
			if (indexOfD != 0) {
				numberOfRolls = Integer.parseInt(howManyRolls);
			}
		} catch (Exception e) {
			System.out.println("Wprowadzono złą liczbę rzutów. Mogą to być dodatnie liczby całkowite.");
		}
		if (numberOfRolls < 0) {
			System.out.println("Podano liczbę ujemną. Proszę podaj liczbę dodatnią!");
		}

		return numberOfRolls;
	}

	static int decodeDiceType(int indexOfD, int indexOfPlus, String code) {

		int diceType = 0;
		try {
			String whatIsTheDiceType = code.substring(indexOfD + 1, indexOfPlus);
			diceType = Integer.parseInt(whatIsTheDiceType);
		} catch (Exception e) {
			System.out.println("Podany kod kości jest nieprawidłowy.");
		}
		if (diceType != 3 && diceType != 4 && diceType != 6 && diceType != 8 && diceType != 10 && diceType != 12
				&& diceType != 20 && diceType != 100) {
			System.out.println("Nie ma takiego rodzaju kości. Dozwolone kości to: D3, D4, D6, D8, D10, D12, D20, D100");
		}

		return diceType;
	}

	static int decodeNumberToAdd(int indexOfPlus, String code) {
		String howMuchTooAdd = "";
		int numberToAdd = 0;
		if (indexOfPlus != code.length()) {
			try {
				howMuchTooAdd = code.substring(indexOfPlus + 1, code.length());
				numberToAdd = Integer.parseInt(howMuchTooAdd);
			} catch (Exception e) {
				System.out.println("Wartość do dodania nieprawidłowa.");
			}
		}
		return numberToAdd;
	}
}
