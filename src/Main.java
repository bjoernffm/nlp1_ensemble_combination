import Common.Decider;
import Common.FileReader;
import Common.Object;
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
			
			// testing
			Decider decider = new Decider(result.getTaggerConfidence());

			String[] rows = reader.getContent().split("\n", -1);
			String[] cols;
			
			for(int i = 0; i < 5; i++) {
				cols = rows[i].split("\t", -1);

				int decision = decider.getPreferedTagger();
				System.out.print(cols[0]+"\t");
				System.out.print(cols[decision]+"\t");
				System.out.println(Object.tagsets[(decision-1)].toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
