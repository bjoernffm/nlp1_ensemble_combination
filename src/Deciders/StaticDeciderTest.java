package Deciders;

import static org.junit.Assert.*;

import org.junit.Test;

import Interfaces.Decider;

public class StaticDeciderTest {

	@Test
	public void testGetPreferedTagger1() {
		double[] values = {0.3, 0.4, 0.5};
		Decider decider = new StaticDecider(values);
		assertEquals(decider.getPreferedTagger(), 3);
	}

	@Test
	public void testGetPreferedTagger2() {
		double[] values = {0.5, 0.4, 0.5};
		Decider decider = new StaticDecider(values);
		assertEquals(decider.getPreferedTagger(), 1);
	}

	@Test
	public void testGetPreferedTagger3() {
		double[] values = {};
		Decider decider = new StaticDecider(values);
		assertEquals(decider.getPreferedTagger(), -1);
	}

}
