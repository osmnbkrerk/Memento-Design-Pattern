package mementoodeviornek2;

public class GameImpl implements Game {
 
	private int highScore;
 
	@Override
	public MementoToUser saveGame() {
		return new Memento(highScore);
	}
 
	@Override
	public void loadGame(MementoToUser memento) {
		this.highScore = ((MementoToController) memento).getScore();
	}
 
	@Override
	public int getHighestScore() {
		return highScore;
	}
 
	@Override
	public void setHighestScore(int score) {
		this.highScore = score;
	}
}