class WordPair implements Comparable<WordPair> {
	String word, wordMeanings;

	WordPair(String word, String wordMeanings) {
		this.word = word;
		this.wordMeanings = wordMeanings;
	}

	public String getWord() {
		return word;
	}

	public String getMeaning() {
		return wordMeanings;
	}

	@Override
	public int compareTo(WordPair o) {
		return (this.word.compareTo(o.word) > 0) ? 1 : -1;
	}
}
