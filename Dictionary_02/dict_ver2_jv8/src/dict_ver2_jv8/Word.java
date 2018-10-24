package dict_ver2_jv8;

public class Word {
	protected String word_target; // tu moi
	protected String word_explain; // giai nghia
	protected String word_pronoun;
	
	public void setWord_target(String word_target) {
		this.word_target = word_target;
	}
	public void setWord_explain(String word_explain) {
		this.word_explain = word_explain;
	}
	public String getWord_target() {
		return word_target;
	}
	public String getWord_explain() {
		return word_explain;
	}
	public String getWord_pronoun() {
		return word_pronoun;
	}
	public void setWord_pronoun(String word_pronoun) {
		this.word_pronoun = word_pronoun;
	}
	public Word() {
		this.word_target="";
		this.word_explain="";
	}
	public Word(String word_target, String word_pronoun, String word_explain) {
		this.word_target = word_target;
		this.word_explain = word_explain;
		this.word_pronoun = word_pronoun;
	}
	
	
}
