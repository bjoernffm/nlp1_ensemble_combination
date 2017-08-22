package Parsers.CoNLL;

public class Token {

	String token;
	String afrikaans;
	String german;
	String danish;
	String english;
	String dutch;
	String swedish;
	
	public Token(String token, String afrikaans, String german, String danish, String english, String dutch, String swedish)
	{
		this.token = token;
		this.afrikaans = afrikaans;
		this.german = german;
		this.danish = danish;
		this.english = english;
		this.dutch = dutch;
		this.swedish = swedish;
	}
	
	public String toString()
	{
		return "["+this.token+", "+this.afrikaans+", "+this.german+", "+this.danish+", "+this.english+", "+this.dutch+", "+this.swedish+"]";
	}
}
