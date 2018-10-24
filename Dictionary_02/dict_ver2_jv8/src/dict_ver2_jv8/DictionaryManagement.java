package dict_ver2_jv8;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
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
	
	public boolean containTarget(String target) {
		insertArrayWord_target();
		return dict.data_word_target.contains(target);
	}
	
	
	
	public void insertFromFile() throws IOException {
		//BufferedReader input = new BufferedReader(new FileReader(file));
		FileInputStream fi = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fi, "utf-8");
        BufferedReader input = new BufferedReader(isr);
		int i;
		Word newWord = new Word();
		while ( (i = input.read()) != -1 ) {
			if( (char)i == '\n' ) {
				dict.data.add(newWord);
				newWord = new Word();
			}
			else {
				String x =input.readLine();
				if( (char)i == '@' ) {// la target + pronoun
					x = x.trim();
					String[] y = x.split("/", 2);
					newWord.setWord_target(y[0].trim());
					newWord.setWord_pronoun( y[1].substring(0, y[1].length() -1).trim());
				}
				else {
					if( newWord.getWord_explain() == "" ) newWord.setWord_explain( (char)i+ x);
					else newWord.setWord_explain(newWord.getWord_explain()+ "\n" + (char)i + x);
				}
			}
		}
		dict.data.add(newWord);
		System.out.println("done!");
		}
	
	public Word dictionaryLookup(String target) {
		for( int i = 0 ; i< dict.data.size(); i++) {
			if( dict.data.get(i).getWord_target().equals(target) ) {
				return dict.data.get(i);
			}
		}
		return null;
	}
	
	public String addData(String target, String pronoun, String explain) {
		insertArrayWord_target();
		
		if( dict.data_word_target.contains(target.toLowerCase()) ) {
			return "This word exists";
		}
		else {
			dict.addWord(target,pronoun, explain);
			return "Done!";
		}

	}
	
	public String insertData(String target, String pronoun, String explain) {
		insertArrayWord_target();
		for( int i = 0; i< dict.data.size(); i++) {
			if(dict.data.get(i).getWord_target().equals(target)) {
				dict.data.get(i).setWord_pronoun(pronoun);
				dict.data.get(i).setWord_explain(explain);
				return "Done!";
			}
		}
		return "This word does not exist";

	}
	
	public String deleteData(String target) {
		for ( int i = 0; i<dict.data.size(); i++) {
			if( dict.data.get(i).getWord_target().equals(target)) {
				dict.data.remove(i);
				return "Done!";
			}
		}
		return "This word does not exist";
	}
	
	public void exportToFile() throws IOException {
		//FileWriter output = new FileWriter(file);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
		for ( int i = 0; i< dict.data.size() ; i++) {
			output.write("@"+dict.data.get(i).getWord_target() + " /" +dict.data.get(i).getWord_pronoun() + "/");
			output.write("\n");
			output.write( dict.data.get(i).getWord_explain());
			if( i != dict.data.size()-1 ) output.write("\n\n"); // khong phai tu cuoi cung thi cach ra 1 dong
		}
		output.close();
		
	}

}

