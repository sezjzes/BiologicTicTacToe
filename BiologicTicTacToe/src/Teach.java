import java.io.IOException;


public class Teach {

	public static void main(String[] args) {
		try
		{
		TicTacToeBiom a = new TicTacToeBiom("/Users/python/Google Drive/PythonSzczesniakJosh/BiologicTicTacToe/BiologicTicTacToe/1");
		a.learn();
		}catch(IOException e){}
	}

}
