package Deciders;

import Interfaces.Decider;

public class StaticDecider implements Decider {
	int bestTagger = -1;
	
	public StaticDecider(double[] likelyhoods)
	{
		double maxLikelyHood = 0;
		
		for(int i = 0; i < likelyhoods.length; i++) {
			if(likelyhoods[i] > maxLikelyHood) {
				maxLikelyHood = likelyhoods[i];
				this.bestTagger = i+1;
			}
		}
	}

	public int getPreferedTagger()
	{
		return this.bestTagger;
	}
}
