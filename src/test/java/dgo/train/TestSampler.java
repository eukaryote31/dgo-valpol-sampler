package dgo.train;

import java.io.File;

import org.junit.Test;

public class TestSampler {
	@Test
	public void testSampler() {
		Sampler samp = new SamplerImpl();

		// get sgf
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test.sgf").getFile());
		
		samp.sampleAll(file);
	}
}
