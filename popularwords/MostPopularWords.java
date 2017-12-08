package popularwords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MostPopularWords {

	public static void main(String[] args) {
		mostPopularWords("https://www.onet.pl/", "span.title");
		// mostPopularWords("https://www.wp.pl/");

	}

	static void mostPopularWords(String url, String htmlTg) {
		File file = createOrGetFile("popular_words.txt");
		List<String> popularWords = getPopularWords(file, url, htmlTg);
		File popFile = createOrGetFile("most_popular_words.txt");
		getTheMostPopularWord(popFile, file);

	}

	static File createOrGetFile(String pathname) {

		File file = new File(pathname);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

	static List<String> getPopularWords(File file, String url, String htmlTg) {
		Connection connect = Jsoup.connect(url);
		List<String> popularWords = new ArrayList<>();
		try {

			Document document = connect.get();
			Elements links = document.select(htmlTg);
			for (Element elem : links) {
				String tmp = elem.text();
				String[] tmpStrArr = tmp.split(" ");
				for (int i = 0; i < tmpStrArr.length; i++) {
					String tmpStr = tmpStrArr[i].replaceAll("\\,", "").replaceAll("\\;", "").replaceAll("\\:", "")
							.replaceAll("\\.", "").replaceAll("\\?", "").replaceAll("\\!", "").replaceAll("\"", "");
					if (tmpStr.length() > 3) {
						popularWords.add(tmpStr.trim());

					}

				}

				// System.out.println(elem.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.write(file.toPath(), popularWords, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return popularWords;
	}

	static void getTheMostPopularWord(File file, File previousFile) {

		List<String> popularWords = new ArrayList<>();
		try {
			Scanner sc = new Scanner(previousFile);
			while (sc.hasNextLine()) {
				popularWords.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Collections.sort(popularWords);
		// for (String string : popularWords) {
		// System.out.println(string);
		// }

		List<String> result = new ArrayList<>();

		List<String> popularWords2 = new ArrayList<>(popularWords);
		Map<String, Integer> wordMap = new HashMap<>();
		List<Integer> mostRepetitions = new ArrayList<>();
		int tmpCount = 1;
		for (int i = 0; i < popularWords2.size(); i++) {
			if (i < popularWords2.size() - 1) {
				String s = popularWords2.get(i);

				if (popularWords2.get(i).equals(popularWords2.get(i + 1))) {
					tmpCount++;
				} else {
					// System.out.println(s);
					wordMap.put(popularWords2.get(i), tmpCount);
					i = i + tmpCount - 1;
					mostRepetitions.add(tmpCount);
					tmpCount = 1;
					while (popularWords2.contains(s)) {
						popularWords2.remove(s);
					}
					i = -1;
				}
				if (popularWords2.size() == 0) {
					break;
				}
			}
			else{
				tmpCount = 1;
				wordMap.put(popularWords2.get(i), tmpCount);
			} 
				}

		Collections.sort(mostRepetitions);
		Collections.reverse(mostRepetitions);
		mostRepetitions = mostRepetitions.subList(0, 10);
		// for (Integer integer : mostRepetitions) {
		// System.out.println(integer);
		// }

		// for (Entry<String, Integer> entry : wordMap.entrySet()) {
		// System.out.println(entry.getKey() + "/" + entry.getValue());
		// }

		for (int j = 0; j < popularWords.size(); j++) {
			String word = popularWords.get(j);
			// System.out.println(word);
			int count = wordMap.get(word);
			// System.out.println(count);
			if (mostRepetitions.contains(count)) {
				result.add(popularWords.get(j));

			}
			while (popularWords.contains(word)) {
				popularWords.remove(word);
			}
			j = 0;
		}

		for (String s : result) {
			System.out.println(s);
		}
		try {
			Files.write(file.toPath(), result, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
