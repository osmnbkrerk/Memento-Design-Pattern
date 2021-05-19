package mementoodeviornek2;

public class Client {
    
	public static void main(String[] args) {
 
		Game game = new GameImpl(); // 1
		game.setHighestScore(200);  // 2
		System.out.println(game.getHighestScore());
		MementoToUser memento = game.saveGame(); //3
		game.setHighestScore(100); //4
		System.out.println(game.getHighestScore());
		game.loadGame(memento); //5
		System.out.println(game.getHighestScore());
	}
}
