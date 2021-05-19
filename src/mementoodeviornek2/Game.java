package mementoodeviornek2;

public interface Game {
 
	public abstract MementoToUser saveGame();
 
	public abstract void loadGame(MementoToUser memento);
 
	public abstract int getHighestScore();
	
	public abstract void setHighestScore(int score);
 
}