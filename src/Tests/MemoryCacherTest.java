package Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Cachers.MemoryCacher;
import Interfaces.Cacher;

public class MemoryCacherTest {

	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("NamedIndividual");
		list.add("NamedIndividual");
		list.add("Tag");
		list.add("LingusticAnnotation");
		list.add("nn");
		
		Cacher cacher = new MemoryCacher();
		assertFalse(cacher.contains("EMPTY_NAMESPACE", "SOME_POS_TAG"));
		assertFalse(cacher.contains("STagger", "nn"));
		assertEquals(cacher.size(), 0);
		
		cacher.set("STagger", "nn", list);
		assertEquals(cacher.size(), 1);
		assertFalse(cacher.contains("EMPTY_NAMESPACE", "SOME_POS_TAG"));
		assertFalse(cacher.contains("STagger", "SOME_OTHER_POS_TAG"));
		assertTrue(cacher.contains("STagger", "nn"));
		
		cacher.set("STagger", "nn", list);
		assertEquals(cacher.size(), 1);
		assertFalse(cacher.contains("EMPTY_NAMESPACE", "SOME_POS_TAG"));
		assertFalse(cacher.contains("STagger", "SOME_OTHER_POS_TAG"));
		assertTrue(cacher.contains("STagger", "nn"));
		
		assertEquals(cacher.get("STagger", "nn"), list);
	}

}
