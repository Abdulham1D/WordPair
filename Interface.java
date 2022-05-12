import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Interface {
	public static void main(String[] args) throws Exception {

		File file = new File("src/dictionary.txt");
		DLL<WordPair> listOfWordPairObjs = new DLL<>();
		
		try (Scanner input = new Scanner(file)) {
			while (input.hasNext()) {
				String[] split = input.nextLine().split(" ", 2);
				WordPair wordPair = new WordPair(split[0], split[1]);
				String word = split[0];
				String meaning = split[1];

				listOfWordPairObjs.addToTail(wordPair);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Such file does not exist - Check the path");
		}

		Scanner userInput = new Scanner(System.in);
		String choice = "";
		int choiceNum = 0;

		do {
			printMenu();
			choice = userInput.next();
			if (!isNumeric(choice))
				choice = "0";

			if (!checker(Integer.parseInt(choice)))
				System.out.print("\n>>> Wrong choice... Try again!\n");

			else {
				choiceNum = Integer.parseInt(choice);
				if (choiceNum == 1) {

					System.out.print("Enter the word pair: ");
					Scanner insertedWordPair = new Scanner(System.in);
					String userIn = insertedWordPair.nextLine();
					String[] split = userIn.split(" ", 2);

					WordPair wordPair = new WordPair(split[0], split[1]);

					if (listOfWordPairObjs.find(wordPair.getWord()) != null) {
						System.out.println("The word is already in the Dictionary");

					} else {
						listOfWordPairObjs.insert(wordPair);
						System.out.println("The word pair is successfully inserted");
					}

				} else if (choiceNum == 2) {
					System.out.print("Enter the word: ");
					Scanner SearchedWordPair = new Scanner(System.in);
					String userIn = SearchedWordPair.next();
					if (listOfWordPairObjs.find(userIn) == null) {
						System.out.println("Such word does not exist");
					} else {
						System.out.println(listOfWordPairObjs.find(userIn).getWord() + " "
								+ listOfWordPairObjs.find(userIn).getMeaning());
					}

				} else if (choiceNum == 3) {
					System.out.print("Enter the word: ");
					Scanner deletedWordPair = new Scanner(System.in);
					String userIn = deletedWordPair.next();
					if (listOfWordPairObjs.find(userIn) != null) {
						listOfWordPairObjs.delete(userIn);
						System.out.println("The word pair is successfully deleted");
					} else {
						System.out.println("Such word does not exist");
					}
				} else if (choiceNum == 4) {
					System.out.print("Enter the word along with its new meanings: ");
					Scanner modifiedWord = new Scanner(System.in);
					String userIn = modifiedWord.nextLine();
					String[] split = userIn.split(" ", 2);

					WordPair wordPair = new WordPair(split[0], split[1]);

					if (listOfWordPairObjs.modifyWord(wordPair.getWord(), wordPair.getMeaning())) {
						System.out.println("The word meaning(s) has been updated");
					} else {
						System.out.println("Such word does not exist");
					}
				}

				else if (choiceNum == 5) {

					System.out.print("Enter the prefix: ");
					Scanner prefix = new Scanner(System.in);
					String userIn = prefix.next();
					if (listOfWordPairObjs.checkPrefix(userIn))
						System.out.println("Such word with this prefix does not exist");
					else {
						listOfWordPairObjs.printAll(userIn);
					}

				} else if (choiceNum == 6) {
					listOfWordPairObjs.printSorted();
					System.out.println(listOfWordPairObjs.myWords(listOfWordPairObjs));
				}

			}

		} while (choiceNum != 7);

		listOfWordPairObjs.writeToFile(listOfWordPairObjs, file);
		System.out.println("Size of the dictionary is: " + listOfWordPairObjs.getSize());
		System.out.println("Try Us Again, See You Soon");
	}

	public static void printMenu() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">>>>>>>>>>>>>>>>>>  WordPair Project  <<<<<<<<<<<<<<<<<<");
		System.out.println("Choices:");
		System.out.println("    1. Insert a new word with its meanings\n"
						+  "    2. Search for a word\n"
						+  "    3. Delete a word and its meanings\n"
						+  "    4. Modify the meanings of a word\n"
						+  "    5. Print all words with a given prefix and their meanings\n"
						+  "    6. Print the contents of the dictionary sorted in lexicographic order\n"
						+  "    7. Exit\n");
		System.out.print("Enter your choice: ");
	}

	private static boolean isNumeric(String num) {
		if (num == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(num);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean checker(int input) {
		if (input != 1 && input != 2 && input != 3 && input != 4 && input != 5 && input != 6 && input != 7)
			return false;
		return true;
	}
}
