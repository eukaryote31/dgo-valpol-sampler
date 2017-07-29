package dgo.train;

import java.awt.Point;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.toomasr.sgf4j.Sgf;
import com.toomasr.sgf4j.parser.Game;
import com.toomasr.sgf4j.parser.GameNode;

import dgo.goban.Goban;

public class SamplerImpl implements Sampler {

	@Override
	public TrainingCase sample(Random rnd, File f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainingCase> sample(Random rnd, File f, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainingCase> sampleAll(File f) {
		List<TrainingCase> ret = new ArrayList<>(200);
		try {
			Game game = Sgf.createFromPath(f.toPath());

			// parse komi
			double komi = Double.parseDouble(game.getProperty("KM"));

			// parse result
			String resstr = game.getProperty("RE");
			String[] pts = StringUtils.split(resstr, "+", 2);

			double absamt;

			if (pts[1].equals("R")) {
				// match resignation, so we dont know the final score
				// set absamt to something as a placeholder

				absamt = Double.NaN;
			} else {
				// parse the number

				absamt = Double.parseDouble(pts[1]);
				if (pts[0].equals("B")) {
					// black
				} else if (pts[0].equals("W")) {
					// white, we represent white wins as negative values
					absamt *= -1;
				} else {
					// invalid!
					return null;
				}

				// remove komi by biasing in black direction
				absamt += komi;
			}

			// iterate over moves
			GameNode node = game.getRootNode();
			while ((node = node.getNextNode()) != null) {
				byte player = Goban.BLACK;
				String move = node.getProperty("B", null);
				
				// white player
				if (move == null) {
					player = Goban.WHITE;
					move = node.getProperty("W", null);
				}
				
				// no white property either?
				// probably bad file
				if (move == null)
					return null;
				
				Point pos = parsePos(move);
				
				// TODO: finish
			}

		} catch (NumberFormatException e) {
			// a number was wrong somewhere!
			return null;
		}

		return ret;
	}
	
	private Point parsePos(String posstr) {
		// bad string length!
		if (posstr.length() != 2)
			return null;
		
		// shift position down to 0..18
		int x = posstr.charAt(0) - 'a';
		int y = posstr.charAt(1) - 'a';
		
		return new Point(x, y);
	}

}
