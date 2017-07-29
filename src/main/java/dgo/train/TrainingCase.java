package dgo.train;

import java.awt.Point;

import dgo.goban.Goban;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainingCase {
	private Goban state;
	
	private double komi;
	
	private double score;
	
	private byte player;
	
	private Point next;
}
