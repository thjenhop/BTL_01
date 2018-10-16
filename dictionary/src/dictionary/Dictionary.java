package dictionary;

import java.util.*;

public class Dictionary {
	protected ArrayList<Word> data = new ArrayList<Word>();
	protected ArrayList<String> data_word_target = new ArrayList<String>();
	
	public void addWord(Word w) {
		data.add(w);
	}
	public void addWord(String target, String meaning) {
		Word newWord = new Word(target,meaning);
		data.add(newWord);	
	}
	
	public void addWord(String target,String pronoun, String meaning) {
		Word newWord = new Word(target,pronoun, meaning);
		data.add(newWord);	
	}
}
