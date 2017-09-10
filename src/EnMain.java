import java.util.List;

import Common.Blazegraph;
import Parsers.CoNLL.Parser;
import Parsers.CoNLL.Token;

public class EnMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Blazegraph bs = new Blazegraph();

			List<String> result;
			
			result = bs.queryAfrikaansUD("NOUN");
			System.out.println(result);
			result = bs.queryGermanSTTS("NN");
			System.out.println(result);
			result = bs.queryDanishEnglishPTB("JJ");
			System.out.println(result);
			result = bs.queryDanishEnglishPTB("NN");
			System.out.println(result);
			result = bs.queryDutchLassyShort("BW");
			System.out.println(result);
			result = bs.querySwedishSTagger("PM");
			System.out.println(result);
			
			/*Parser parser = new Parser("projections-no-norm.txt");
			
			String content = parser.getContent();
			String[] tokens = content.split("\n", -1);
			String[] pos;
			
			for(int i = 0; i < tokens.length-1; i++) {
				pos = tokens[i].split("\t", -1);
				
				if (pos[0].isEmpty()) {
					continue;
				}
				
				List<String> result = bs.queryGermanSTTS(pos[2]);
				System.out.println(result);
				System.out.println(i+"/"+tokens.length);
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
