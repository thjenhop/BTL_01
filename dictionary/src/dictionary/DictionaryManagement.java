package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class DictionaryManagement {
	protected Dictionary dict = new Dictionary();
	protected String file = "dictionary.txt";
	
	public void insertArrayWord_target() {
		if( dict.data_word_target.size() != 0 ) {	
			dict.data_word_target.clear();
		}
		for ( int i = 0; i< dict.data.size(); i++) { // add het word_target vao 
			dict.data_word_target.add(dict.data.get(i).getWord_target()); 
		}
	}
	public void insertFromCommandLine() {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Nhập số từ : ");
		int numWord = input.nextInt();
		input.nextLine();
		for( int i = 0; i< numWord; i++) {
			System.out.println("Nhập từ thứ "+ (i+1) );
			System.out.print("Nhập từ : ");
			String target = input.nextLine().toLowerCase();
			
			System.out.print("Nhập nghĩa : ");
			String explain = input.nextLine().toLowerCase();
			dict.addWord(target, explain);
		}
	}
	
	
	
	public void insertFromFile() throws IOException {
		FileInputStream fi = new FileInputStream(file);
       	 	InputStreamReader isr = new InputStreamReader(fi, "utf-8");
        	BufferedReader input = new BufferedReader(isr);
		int i;
		Word newWord = new Word();
		while ( (i = input.read()) != -1 ) {
			if( i == 10 ) { // la ki tu carriage return or 13
				dict.data.add(newWord);
				newWord = new Word();
			}
			else {
				String x =input.readLine();
					if( (char)i == '@' ) {// la target + pronoun
						x = x.trim();
						String[] y = x.split(" /", 2);
						newWord.setWord_target(y[0].trim());
						newWord.setWord_pronoun( y[1].substring(0, y[1].length() -1).trim());
					}
					else {
						if( newWord.getWord_explain() == "" ) newWord.setWord_explain( (char)i+ x);
						else {
							String y = newWord.getWord_explain()+ "\n" + (char)i + x;
							newWord.setWord_explain(y);
						}
					}
			}

		}
		dict.data.add(newWord);
		System.out.println("done!");
		
	}
	
	public void dictionaryLookup() {
		Scanner input = new Scanner(System.in);
		System.out.print("Nhập từ muốn tìm kiếm : ");
		String s = input.nextLine(); 
		s= s.toLowerCase();
		for ( int i = 0; i< dict.data.size(); i++) {
			if(dict.data.get(i).getWord_target().equals(s) ) {
				System.out.println(dict.data.get(i).getWord_explain());
				return;
			}
		}
		System.out.println("Từ không tồn tại");
	}
	
	public void addData() {
		insertArrayWord_target();
		Scanner input = new Scanner(System.in);
		System.out.print("Nhập từ tiếng anh : ");
		
		String target = input.nextLine().toLowerCase();
		if( dict.data_word_target.contains(target) ) {
			System.out.println("Từ đã tồn tại !");
		}
		else {
			System.out.print("Nhập nghĩa : ");
			String explain = input.nextLine().toLowerCase();
			dict.addWord(target, explain);
		}

	}
	
	public void insertData() {
		insertArrayWord_target();
		Scanner input = new Scanner(System.in);
		System.out.print("Nhập từ tiếng anh cần sửa : ");
		String target = input.nextLine().toLowerCase();
		for ( int i = 0; i< dict.data.size();i++) {
			if(dict.data.get(i).getWord_target().equals(target)) {
				System.out.print("Nhập phiên âm :");
				String pronoun = input.nextLine().toLowerCase();
				dict.data.get(i).setWord_pronoun(pronoun);
				System.out.print("Nhập nghĩa : ");
				String explain = input.nextLine().toLowerCase();
				dict.data.get(i).setWord_explain(explain);
				return;
			}
			else {
				System.out.println("Từ không tồn tại");
			}
		}

	}
	
	public void deleteData() {
		System.out.print("Nhập từ cần xóa : ");
		Scanner input = new Scanner(System.in);
		String target = input.nextLine().toLowerCase();
		for( int i = 0; i< dict.data.size();i++) {
			if ( dict.data.get(i).getWord_target().equals(target) ) {
				dict.data.remove(i);
				input.close();
				return;
			}
			else {
				System.out.println("Từ không tồn tại!");
			}
		}

	}
	
	public void exportToFile() throws IOException {
		FileWriter output = new FileWriter(file);
		for ( int i = 0; i< dict.data.size() ; i++) {
			output.write("@"+dict.data.get(i).getWord_target() + "/" + dict.data.get(i).getWord_pronoun() +"/");
			output.write("\n");
			output.write(dict.data.get(i).getWord_explain());
			if( i != dict.data.size()-1 ) output.write("\n\n"); // khong phai tu cuoi cung thi cach ra 1 dong
		}
		output.close();
		
	}
}
