package dictionary;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandLine {
	DictionaryManagement manager = new DictionaryManagement();
		
	public void run() throws IOException {
		System.out.println("1.Chức năng cơ bản");
		System.out.println("2.Chức năng nâng cao");
		Scanner input = new Scanner(System.in);
		System.out.print("Nhập lựa chọn : ");
		int choise = input.nextInt();
		switch(choise) {
			case 1: dictionaryBasic();break;
			case 2: dictionaryAdvanced();break;
		}
		
		System.out.print("Muốn xuất dữ liệu hiện tại ra file (y/n):");
		String x = input.next();
		if(x.equals("y")) manager.exportToFile();
	}
	
	public void showAllWord_advanced() {  // in tu - phien am - nghia
		System.out.print("\n");
		
		for( int i = 0 ; i < manager.dict.data.size(); i++)
		{
			System.out.println("từ thứ "+ (i + 1));
			System.out.println( "từ : " +manager.dict.data.get(i).getWord_target() + "\t/"+ manager.dict.data.get(i).getWord_pronoun()+"/" );
			System.out.println("nghĩa :\n" + manager.dict.data.get(i).getWord_explain());
			System.out.print("\n");
		}
	}
	public void dictionaryBasic() {
		Scanner input = new Scanner(System.in);
		int choise;
		do {
			System.out.println("1.Nhập từ bàn phím");
			System.out.println("2.Hiện tất cả từ");
			System.out.print("Nhập lựa chọn : ");
			choise = input.nextInt();
			switch(choise) {
				case 1: manager.insertFromCommandLine();break;
				case 2: showAllWord_basic();break;
			}
			System.out.print("Muốn tiếp tục(1_y/2_n) : ");
			choise= input.nextInt();
		}while ( choise == 1);
	}
	public void showAllWord_basic() {// chi in tu va nghia
		System.out.print("\n");
		
		for( int i = 0 ; i < manager.dict.data.size(); i++)
		{
			System.out.println("từ thứ "+ (i + 1));
			System.out.println( "từ : " +manager.dict.data.get(i).getWord_target() );
			System.out.println("nghĩa :\n" + manager.dict.data.get(i).getWord_explain());
			System.out.print("\n");
		}
	}
	
	public void dictionaryAdvanced() throws IOException {
		Scanner input = new Scanner(System.in);
		int choise;
		do {
			System.out.println("1.Lấy dữ liệu từ file(bắt buộc)");
			System.out.println("2.Hiện tất cả từ");
			System.out.println("3.Tìm kiếm từ");
			System.out.println("4.Tra nghĩa từ");
			System.out.println("5.Lựa chọn khác");
			System.out.print("Nhập lựa chọn : ");
			int choise_1 = input.nextInt();
			switch(choise_1) {
				case 1: manager.insertFromFile();  break;
				case 2: showAllWord_advanced();             break;
				case 3: dictionarySearch();        break;
				case 4: manager.dictionaryLookup();break;
				case 5:{
					System.out.println("1.Thêm từ");
					System.out.println("2.Sửa từ");
					System.out.println("3.Xóa từ");
					System.out.print("Nhập lựa chọn : ");
					int choise_2 = input.nextInt();
					switch(choise_2) {
					case 1: manager.addData();     break;
					case 2: manager.insertData();  break;
					case 3: manager.deleteData();  break;
					}
				}break;
			}
			System.out.print("Muốn tiếp tục(1_y/2_n) : ");
			choise= input.nextInt();
		}while ( choise == 1);
	}
	
	public void dictionarySearch() {
		boolean _continue = true;
		Scanner input = new Scanner(System.in);
		do {
			System.out.print("Từ cần tìm : ");
			String x = input.nextLine().toLowerCase();
			for ( int i = 0; i< manager.dict.data.size(); i++) {
				if ( manager.dict.data.get(i).word_target.length() >= x.length()) { // do dai target >= do dai tu can tim
					String word_split = splitString(manager.dict.data.get(i).word_target, x.length());
					if( word_split.equals(x)) {
						System.out.println(manager.dict.data.get(i).word_target);
					}
				}
			}
			System.out.println("Muốn tiếp tục tìm kiếm (1_y/2_n) : ");
			int y = input.nextInt();
			input.nextLine();
			if( y == 2) _continue = false;
		}while(_continue);
		System.out.print("Muốn tra nghĩa (1_y/2_n) : ");
		int z = input.nextInt();
		if( z == 1 ) manager.dictionaryLookup();
	}
	public static String splitString(String s, int n) { 
		String x = "";
		for ( int i = 0; i< n; i++) {
			x += s.charAt(i);
		}
		return x;
	}
}
