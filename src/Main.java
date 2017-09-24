import java.io.IOException;
import java.io.PrintWriter;

import Common.FileReader;
import Common.Object;
import Deciders.RandomDecider;
import Interfaces.Decider;
import Trainers.TaggerTrainer;
import Trainers.TrainerResult;

public class Main {

	public static void main(String[] args) {
		try {
			// training
			TaggerTrainer taggerTrainer = new TaggerTrainer();
			FileReader reader = new FileReader("projections-no-norm.txt");
			
			String content = reader.getContent();
			taggerTrainer.setContent(content);
			TrainerResult result = taggerTrainer.getResult();

			System.out.println(result.toString()+"\n");

			// testing RandomDecider
			System.out.println("+----------------------------------------------------+");
			System.out.println("| Annotation using the RandomDecider                 |");
			System.out.println("+----------------------------------------------------+\n");
			
		    PrintWriter randomDeciderWriter = new PrintWriter("random_decider_annotation.txt", "UTF-8");
			Decider decider = new RandomDecider(result.getTaggerConfidence());

			String[] rows = reader.getContent().split("\n", -1);
			String[] cols;
			
			for(int i = 0; i < rows.length; i++) {
				cols = rows[i].split("\t", -1);
				
				if (cols[0].equals("")) {
					randomDeciderWriter.println("");
					continue;
				}

				int decision = decider.getPreferedTagger();
				randomDeciderWriter.println(cols[0]+"\t"+Object.tagsets[(decision-1)].toString()+":"+cols[decision]+"\t");
			}
			
			randomDeciderWriter.close();
			
			// testing StaticDecider
			System.out.println("+----------------------------------------------------+");
			System.out.println("| Annotation using the StaticDecider                 |");
			System.out.println("+----------------------------------------------------+\n");
			
		    PrintWriter staticDeciderWriter = new PrintWriter("static_decider_annotation.txt", "UTF-8");
			decider = new RandomDecider(result.getTaggerConfidence());

			rows = reader.getContent().split("\n", -1);
			
			for(int i = 0; i < rows.length; i++) {
				cols = rows[i].split("\t", -1);
				
				if (cols[0].equals("")) {
					staticDeciderWriter.println("");
					continue;
				}

				int decision = decider.getPreferedTagger();
				staticDeciderWriter.println(cols[0]+"\t"+Object.tagsets[(decision-1)].toString()+":"+cols[decision]+"\t");
			}
			
			staticDeciderWriter.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
