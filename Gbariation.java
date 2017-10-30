import java.util.Random;


public class Gbariation {

	private enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	private static double TYPO_CHANCE = 0.20f;
	private static double SIDE_CHANCE = 0.60f;
	private static double SWAP_CHANCE = 0.10f;
	private static int NROW = 3;
	private static int NCOL = 10;
	private static int i = 0;
	private static int j = 0;
	
	private static char[][] keyboard = {"qwertyuiop".toCharArray(),"asdfghjkl,".toCharArray(),"zxcvbnm,,,".toCharArray()};
	
	private static char typo(char x) {
		Random r = new Random();
		//Horizontal
		if (r.nextDouble() <= SIDE_CHANCE) {
			if(r.nextDouble() <= 0.5) return randomizeChar(x, Direction.RIGHT);
			else return randomizeChar(x, Direction.LEFT);
		
		//vetical
		} else {
			if(r.nextDouble() <= 0.5) return randomizeChar(x, Direction.UP);
			else return randomizeChar(x, Direction.DOWN);
		}
	}
	
	private static char randomizeChar(char x, Direction dir) {

		findChar(x);
		
		switch(dir) {
		case UP:
			if (i > 0) return keyboard[i-1][j];
			break;
		case DOWN:
			if (i > NROW-1) return keyboard[i+1][j];
			break;
		case LEFT:
			if (j > 0) return keyboard[i][j-1];
			break;
		case RIGHT:
			if (j > NCOL-1) return keyboard[i][j+1];
			break;
		}
		return x;
	}
	
	private static void findChar(char x) {
		for (int i =0; i < NROW; ++i) {
			for (int j = 0; j < NCOL; ++j) {
				if(keyboard[i][j] == x) {
					Gbariation.i = i;
					Gbariation.j = j;
					return;
				}
			}
		}	
	}
	
	private static String swap(StringBuilder txt, int index) {
		if (index < txt.length() - 1) {
			char aux = txt.charAt(index);
			txt.setCharAt(index, txt.charAt(index+1));
			txt.setCharAt(index+1, aux);
		}
		
		return txt.toString();
	}
	public static String gbariate(String in) {
		Random r = new Random();
		StringBuilder txt = new StringBuilder(in.toLowerCase());
		for (int i = 0; i < txt.length(); ++i) {
			if (r.nextDouble() <= TYPO_CHANCE) txt.setCharAt(i, typo(txt.charAt(i)));
			if (r.nextDouble() <= SWAP_CHANCE) txt = new StringBuilder(swap(txt, i));
		}
		return txt.toString();
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(gbariate("teste olar olar"));

	}

}
