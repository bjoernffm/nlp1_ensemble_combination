import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Interfaces.Analyzer;
import Common.Blazegraph;
import Common.CountAnalyzer;
import Common.Decider;
import Parsers.CoNLL.Parser;
import Parsers.CoNLL.Token;

public class EnMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			double[] taggerConfidence = {0, 0, 0, 0, 0, 0, 0};
			
			Blazegraph bs = new Blazegraph();
			Analyzer analyzer = new CountAnalyzer();
			ArrayList<Map.Entry<String, Integer>> analyzerResult;
			
			Parser parser = new Parser("projections-no-norm.txt");
			
			String content = parser.getContent();
			String[] tokens = content.split("\n", -1);
			String[] pos;

			//for(int i = 0; i < tokens.length-1; i++) {
			for(int i = 0; i < 1000; i++) {
				pos = tokens[i].split("\t", -1);
				
				if (pos[0].isEmpty()) {
					continue;
				}
				
				List<String> tokenList = new ArrayList<String>();
				
				List<String> result01;
				result01 = bs.queryAfrikaansUD(pos[1]);
				//System.out.println(result01);
				tokenList.addAll(result01);

				List<String> result02;
				result02 = bs.queryGermanSTTS(pos[2]);
				//System.out.println(result02);
				tokenList.addAll(result02);

				List<String> result03;
				result03 = bs.queryDanishEnglishPTB(pos[3]);
				//System.out.println(result03);
				tokenList.addAll(result03);

				List<String> result04;
				result04 = bs.queryDanishEnglishPTB(pos[4]);
				//System.out.println(result04);
				tokenList.addAll(result04);

				List<String> result05;
				result05 = bs.queryDutchLassyShort(pos[5]);
				//System.out.println(result05);
				tokenList.addAll(result05);

				List<String> result06;
				result06 = bs.querySwedishSTagger(pos[6]);
				//System.out.println(result06);
				tokenList.addAll(result06);
				
				analyzerResult = analyzer.getMostSignificantTokens(tokenList);
				//System.out.print(pos[0]+" => ");
				//System.out.println(analyzerResult.get(0));
				
				// HashMap<String, int> hashmap = HashMap<String, int>();
				// hashmap.put("NN", hashmap.get("NN")+1); 
				// System.out.println(hashmap);

				if (result01.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[0] += 1;
					taggerConfidence[6] += 1;
				}
				if (result02.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[1] += 1;
					taggerConfidence[6] += 1;
				}
				if (result03.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[2] += 1;
					taggerConfidence[6] += 1;
				}
				if (result04.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[3] += 1;
					taggerConfidence[6] += 1;
				}
				if (result05.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[4] += 1;
					taggerConfidence[6] += 1;
				}
				if (result06.contains(analyzerResult.get(0).getKey())) {
					taggerConfidence[5] += 1;
					taggerConfidence[6] += 1;
				}
			}

			taggerConfidence[0] = taggerConfidence[0]/taggerConfidence[6];
			taggerConfidence[1] = taggerConfidence[1]/taggerConfidence[6];
			taggerConfidence[2] = taggerConfidence[2]/taggerConfidence[6];
			taggerConfidence[3] = taggerConfidence[3]/taggerConfidence[6];
			taggerConfidence[4] = taggerConfidence[4]/taggerConfidence[6];
			taggerConfidence[5] = taggerConfidence[5]/taggerConfidence[6];
			System.out.println(taggerConfidence[0]);
			System.out.println(taggerConfidence[1]);
			System.out.println(taggerConfidence[2]);
			System.out.println(taggerConfidence[3]);
			System.out.println(taggerConfidence[4]);
			System.out.println(taggerConfidence[5]);
			
			Decider decider = new Decider(taggerConfidence);
			
			String[] tagsets = {"Afrikaans", "Deutsch", "Danish", "English", "Dutch", "Swedish"};
			
			for(int i = 0; i < 10; i++) {
				pos = tokens[i].split("\t", -1);
				System.out.print(pos[0]+" ");
				int decision = decider.getPreferedTagger();
				System.out.print(pos[decision]+" - ");
				System.out.println(tagsets[(decision-1)]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
