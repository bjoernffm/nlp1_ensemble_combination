package Parsers.CoNLL;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import Common.FileReader;

public class Parser extends FileReader {

	public Parser(String filename) throws IOException {
		super(filename);
	}
	
	public List<Token> getTokenContainer()
	{
		String content = this.getContent();
		String[] tokens = content.split("\n", -1);
		String[] pos;
		
		List<Token> tokenList = new LinkedList<Token>();
		
		for(int i = 0; i < tokens.length-1; i++) {
			pos = tokens[i].split("\t", -1);
			
			if (pos[0].isEmpty()) {
				continue;
			}
			
			Token token = new Token(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5], pos[6]);
			tokenList.add(token);
		}
		
		content = null;
		tokens = null;
		pos = null;
		
		return tokenList;
	}

}
