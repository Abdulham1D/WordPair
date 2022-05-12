import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DLL<T> {

	class DLLNode<T> {
		private T info;
		DLLNode<T> next;
		private DLLNode<T> prev;

		private DLLNode() {
			next = null;
			prev = null;
		}

		public DLLNode(T e) {
			info = e;
			next = null;
			prev = null;
		}

		public DLLNode(T e, DLLNode<T> n, DLLNode<T> p) {
			info = e;
			next = n;
			prev = p;
		}
	}

	private DLLNode<T> head;
	private DLLNode<T> tail;

	public DLL() {
		head = tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void clear() {
		head = tail = null;
	}

	public T getFirst() {
		if (head != null)
			return head.info;
		else
			return null;
	}

	public T getLast() {
		if (tail != null)
			return tail.info;
		else
			return null;
	}

	public void addToHead(T e) {
		if (head != null) {
			head = new DLLNode<T>(e, head, null);
			head.next.prev = head;
		} else
			head = tail = new DLLNode<T>(e);
	}

	public void addToTail(T e) {
		if (tail != null) {
			tail = new DLLNode<T>(e, null, tail);
			tail.prev.next = tail;
		} else
			head = tail = new DLLNode<T>(e);
	}

	@Override
	public String toString() {
		if (head == null)
			return "[ ]";

		String str = "[ ";
		DLLNode<T> tmp = head;
		while (tmp != null) {
			str += tmp.info + "  ";
			tmp = tmp.next;
		}
		return str + "]";
	}

	public boolean contains(T e) {
		if (head == null)
			return false;

		DLLNode<T> tmp = head;
		while (tmp != null) {
			if (tmp.info.equals(e))
				return true;
			tmp = tmp.next;
		}

		return false;
	}

	public int getSize() {
		DLLNode<T> p = head;
		int count = 0;

		while (p != null) {
			p = p.next;
			count++;
		}
		return count;
	}

	public boolean insert(WordPair wordpair) {
		addToTail((T) wordpair);
		return true;

	}

	public WordPair find(String word) {
		DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;
		while (p != null) {
			if (p.info.word.equals(word)) {
				return p.info;
			}
			p = p.next;
		}
		return null;
	}

	public boolean delete(String word) {

		if (find(word) == null)
			return false;

		WordPair wordObj = find(word);

		if (((WordPair) head.info).getWord().equals(word)) {
			return deleteFromHead();
		} else if (((WordPair) tail.info).getWord().equals(word)) {
			return deleteFromTail();
		} else {
			return deleteFromMiddle(wordObj);
		}

	}

	private boolean deleteFromHead() {
		// WordPair e = (WordPair) head.info;
		if (head == tail)
			head = tail = null;
		else {
			head = head.next;
			head.prev = null;
		}
		return true;
	}

	private boolean deleteFromTail() {
		// WordPair e = (WordPair) tail.info;
		if (head == tail)
			head = tail = null;
		else {
			tail = tail.prev;
			tail.next = null;
		}
		return true;
	}

	private boolean deleteFromMiddle(WordPair wordObj) {

		DLLNode<T> p = head;
		while (!wordObj.getWord().equals(((WordPair) p.info).getWord()))
			p = p.next;

		p.prev.next = p.next;
		p.next.prev = p.prev;

		return true;
	}

	public boolean modifyWord(String word, String newMeanings) {

		if (find(word) != null) {

			DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;
			while (p != null) {
				if (p.info.word.equals(word)) {
					p.info.wordMeanings = newMeanings;
					return true;
				}
				p = p.next;
			}
		}

		return false;
	}

	public void printAll(String prefix) {
		DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;
		while (p != null) {
			if (p.info.getWord().indexOf(prefix) == 0) {
				System.out.println(p.info.getWord() + " " + p.info.getMeaning());
			}
			p = p.next;
		}

	}

	public boolean checkPrefix(String prefix) {
		DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;
		DLL<WordPair> prefixWords = new DLL<>();
		while (p != null) {
			if (p.info.getWord().indexOf(prefix) == 0)
				prefixWords.addToTail(p.info);
			p = p.next;
		}
		if (prefixWords.isEmpty())
			return true;
		return false;

	}

	public void printSorted() {
		DLLNode<WordPair> current = (DLL<T>.DLLNode<WordPair>) head;
		DLLNode<WordPair> index = null;
		WordPair temp;
		while (current.next != null) {
			index = current.next;
			while (index != null) {
				if (current.info.word.compareTo(index.info.word) > 0) {
					temp = current.info;
					current.info = index.info;
					index.info = temp;
				}
				index = index.next;
			}
			current = current.next;
		}
	}

	public DLL<String> myWords(DLL<WordPair> ls) {
		DLL<String> words = new DLL<>();

		DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;

		while (p != null) {
			words.addToTail(p.info.getWord() + " " + p.info.getMeaning());
			p = p.next;
		}

		return words;

	}

	public void writeToFile(DLL<WordPair> ls, File file) throws FileNotFoundException {
		DLLNode<WordPair> p = (DLL<T>.DLLNode<WordPair>) head;
		PrintWriter pw = new PrintWriter(file);
		while (p != null) {
			pw.println(p.info.getWord() + " " + p.info.getMeaning());
			p = p.next;
		}
		pw.close();
	}

}
