package mementoodeviornek2;

public class Memento implements MementoToController, MementoToUser {
 
	private int score;
 
	public Memento(int highScore) {
		this.score = highScore;
	}
 
	@Override
	public int getScore() {
		return score;
	}
 
}