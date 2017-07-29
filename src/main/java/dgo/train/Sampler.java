package dgo.train;

import java.io.File;
import java.util.List;
import java.util.Random;

import dgo.goban.Goban;

public interface Sampler {
	/**
	 * Randomly sample a training case from the given sgf file
	 * 
	 * @param rnd
	 *            The Random instance to use
	 * @param f
	 *            Sgf file to sample from
	 * @return A randomly sampled test case from the given sgf file
	 */
	public TrainingCase sample(Random rnd, File f);

	/**
	 * Randomly sample a specified number of training cases from the given sgf
	 * file
	 * 
	 * @param rnd
	 *            The Random instance to use
	 * @param f
	 *            Sgf file to sample from
	 * @param count
	 *            Number of cases to sample. Less may be returned if there are
	 *            less moves in the game.
	 * @return A list of test cases from the given sgf file
	 */
	public List<TrainingCase> sample(Random rnd, File f, int count);

	/**
	 * Sample all moves from given sgf file.
	 * 
	 * @param f
	 *            Sgf file to sample from
	 * @return A list of test cases from the given sgf file
	 */
	public List<TrainingCase> sampleAll(File f);
}
