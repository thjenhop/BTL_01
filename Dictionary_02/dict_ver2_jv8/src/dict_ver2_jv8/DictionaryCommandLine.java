package dict_ver2_jv8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandLine {
	protected DictionaryManagement manager = new DictionaryManagement();
	
	public DictionaryManagement getDictMNG(){
		return manager;
	}
	
	public void showAllWord() { // khong can dung
		System.out.print("\n");
		
		for( int i = 0 ; i < manager.dict.data.size(); i++)
		{
			System.out.println("từ thứ "+ (i + 1));
			//System.out.println(manager.dict.data_word_target.get(i));
			System.out.println( "từ : " +manager.dict.data.get(i).getWord_target() );
			System.out.println("phiên âm : "+ manager.dict.data.get(i).getWord_pronoun());
			System.out.println("nghĩa :" + manager.dict.data.get(i).getWord_explain());
			System.out.print("\n");
		}
	}
	
	public ArrayList<Word> dictionarySearch(String target) {
		ArrayList<Word> relativeWord = new ArrayList<Word>();
		for ( int i = 0 ; i< manager.dict.data.size(); i++) {
			if( manager.dict.data.get(i).getWord_target().length() >= target.length() ) { // tu muc tieu co do dai lon hon tu tim kiem
				String temp = splitString(manager.dict.data.get(i).getWord_target(), target.length());
				if( temp.equals(target) ) {
					relativeWord.add(manager.dict.data.get(i));
				}
			}
		}
		return relativeWord;
	}
	public static String splitString(String s, int n) { 
		String x = "";
		for ( int i = 0; i< n; i++) {
			x += s.charAt(i);
		}
		return x;
	}
}


