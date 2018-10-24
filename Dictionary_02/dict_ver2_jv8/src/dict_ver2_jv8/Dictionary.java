package dict_ver2_jv8;

import java.util.ArrayList;

public class Dictionary {
	protected ArrayList<Word> data = new ArrayList<Word>();
	protected ArrayList<String> data_word_target = new ArrayList<String>();
	
	public ArrayList<Word> getData(){
		return data;
	}
	
	public void addWord(Word w) {
		data.add(w);
	}
	public void addWord(String target,String pronoun, String meaning) {
		Word newWord = new Word(target,pronoun, meaning);
		data.add(newWord);
		
	}
}
