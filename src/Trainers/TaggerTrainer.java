package Trainers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Common.BlazegraphClient;
import Common.CountAnalyzer;
import Interfaces.Analyzer;

public class TaggerTrainer {
	String[] rows;
	
	public void setContent(String content)
	{
		this.rows = content.split("\n", -1);
	}
	
	public TrainerResult getResult() throws Exception
	{
		long timeStart = System.currentTimeMillis(); 
		double[] taggerConfidence = {0, 0, 0, 0, 0, 0, 0};
		
		BlazegraphClient bs = new BlazegraphClient();
		Analyzer analyzer = new CountAnalyzer();
		ArrayList<Map.Entry<String, Integer>> analyzerResult;
		String[] pos;

		for(int i = 0; i < this.rows.length-1; i++) {
			pos = this.rows[i].split("\t", -1);
			
			if (pos[0].isEmpty()) {
				continue;
			}
			
			List<String> tokenList = new ArrayList<String>();
			
			List<String> result01;
			result01 = bs.queryAfrikaansUD(pos[1].trim());
			//System.out.println(result01);
			tokenList.addAll(result01);

			List<String> result02;
			result02 = bs.queryGermanSTTS(pos[2].trim());
			//System.out.println(result02);
			tokenList.addAll(result02);

			List<String> result03;
			result03 = bs.queryDanishEnglishPTB(pos[3].trim());
			//System.out.println(result03);
			tokenList.addAll(result03);

			List<String> result04;
			result04 = bs.queryDanishEnglishPTB(pos[4].trim());
			//System.out.println(result04);
			tokenList.addAll(result04);

			List<String> result05;
			result05 = bs.queryDutchLassyShort(pos[5].trim());
			//System.out.println(result05);
			tokenList.addAll(result05);

			List<String> result06;
			result06 = bs.querySwedishSTagger(pos[6].trim());
			//System.out.println(result06);
			tokenList.addAll(result06);
			
			analyzerResult = analyzer.getMostSignificantTokens(tokenList);

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
		
		long timeEnd = System.currentTimeMillis(); 
		double duration = (timeEnd - timeStart);
		duration = duration/1000;
		
		double cacheUsageTotal = (double) this.rows.length;
		double cacheUsage = (double) bs.getCacher().size();
		cacheUsage = (cacheUsageTotal-cacheUsage)/cacheUsageTotal;
		
		TrainerResult tRes = new TrainerResult(taggerConfidence, cacheUsage, duration);
		
		return tRes;
	}
}
