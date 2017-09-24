package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Deciders.RandomDecider;
import Interfaces.Decider;

public class RandomDeciderTest {

	@Test
	public void testGetPreferedTagger() {
		double[] result = {0, 0, 0, 0, 0, 0};
		double[] check = {0.1, 0.1, 0.3, 0.3, 0.1, 0.1};

		double[] likelyhoods = {0.1, 0.1, 0.3, 0.3, 0.1, 0.1};
		Decider decider = new RandomDecider(likelyhoods);
		
		for(int i = 0; i < 10000; i++) {
			result[(decider.getPreferedTagger()-1)]++;
		}

		result[0] = Math.round(result[0]/1000)/10.;
		result[1] = Math.round(result[1]/1000)/10.;
		result[2] = Math.round(result[2]/1000)/10.;
		result[3] = Math.round(result[3]/1000)/10.;
		result[4] = Math.round(result[4]/1000)/10.;
		result[5] = Math.round(result[5]/1000)/10.;

		assertTrue(check[0]==result[0]);
		assertTrue(check[1]==result[1]);
		assertTrue(check[2]==result[2]);
		assertTrue(check[3]==result[3]);
		assertTrue(check[4]==result[4]);
		assertTrue(check[5]==result[5]);

	}

}
