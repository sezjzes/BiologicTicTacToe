import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Files;
/**
 * 
 * @author python
 * learns how to play a game needs a compete function and score function
 */
public abstract class Biom {
	ArrayList<Strand> strands;
	String filePath;
	/**
	 * creates new biom
	 * @param filePath where to save itself
	 * @param len number of inputs
	 * @param max max input
	 * @param min min input
	 */
	public Biom(String filePath, int len, int max, int min) {
		this.filePath = filePath;
		strands = new ArrayList<Strand>();
		strands.add(new Strand(5, len, max, min));
		try {
			Files.createDirectory(Paths.get(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * loads biom
	 * @param filePath where it is
	 * @throws IOException biom does not exist
	 */
	public Biom(String filePath) throws IOException
	{
		strands = new ArrayList<Strand>();
		this.filePath = filePath;
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			String path  = files[i].getCanonicalPath();
			if (path.charAt(filePath.length() + 1) != '.')
			{
			Strand newStrand =	new Strand(Paths.get(path));
			strands.add(newStrand);
			}
		}
	}
	/**
	 * saves the biom
	 */
	public void save()
	{
		File folder = new File(filePath);
		File[] files = folder.listFiles();
		try{
		for (int i = 0; i < files.length; i++)
		{
			files[i].delete();
		}}catch(NullPointerException e){}
		for(int i = 0; i < strands.size(); i++)
		{
			try {
				strands.get(i).save(Files.createFile(Paths.get((filePath + "/" + i))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * Teaches the biom with learning loop
	 */
	public void learn()
	{
		int steps = 0;
		while (true)
		{
			addStrand();
			if (strands.size() >= 100)
			{
				plague(10);
				steps = 0;
				save();
			}
		System.out.println(steps);
		if (steps > 10000)
			{
				save();
				steps = 0;
			}
		steps++;
		}
	}
	/**
	 * removes a large number AIs if to many are surviving 
	 * @param survivors number to leave alive
	 */
	private void plague(int survivors) {
		int[][] bests = {new int[survivors], new int[survivors]};
		for (int i = 0; i < bests[0].length; i++)
		{
				bests[0][i] = -1;
				bests[1][i] = Integer.MIN_VALUE;
		}
		for(int i = 0; i < strands.size(); i++)
		{
			int score = score(strands.get(i));
			System.out.println("score:" + score);
			int strandIndex = i;
			for(int j = 0; j < bests[0].length; j++)
			{
				if (score > bests[1][j])
				{
					int scoreTemp = bests[1][j];
					int strandIndexTemp = bests[0][j];
					bests[1][j] = score;
					bests[0][j] = strandIndex;
					score = scoreTemp;
					strandIndex = strandIndexTemp;
				}	
			}
		}

		for(int i = 0; i < strands.size(); i++)
		{
			boolean kill = true;
			for(int j = 0; j < bests[0].length; j++)
			{
				if ( bests[0][j] == i)
				{
					kill = false;
				}
			}
			if (kill)
			{
				strands.remove(i);
			}
		}
	}
	/**
	 * creates new strand has it compete with all the other strands
	 */
	private void addStrand() {
		Strand newStrand = strands.get((int)(Math.random()*strands.size())).Duplicate();
		boolean add = true;
		if (Math.random() > .1)
		{
			newStrand.addCodon();
		}
		else
		{
			newStrand.removeCodon();
		}
		loop1: for (int i = 0; i < strands.size(); i++)
		{
			String success = compete(newStrand, strands.get(i));
			if (success == "won")
			{
				strands.remove(i);
			}
			else if (success == "loss")
			{
				add = false;
				break loop1;
			}
		}
		if (add)
		{
			strands.add(newStrand);
		}
	}
	/**
	 * pits two strands against each other
	 * @param p1 the first strand
	 * @param p2 the second strand
	 * @return if p1 "won", "lost", or "tied"
	 */
	public abstract String compete(Strand p1, Strand p2);
	/**
	 * Returns a score for the strand
	 * @param p1 strand to score
	 * @return a score for the strand
	 */
	public abstract int score(Strand p1);
	
}
