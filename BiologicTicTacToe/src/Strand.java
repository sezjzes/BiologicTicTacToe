import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
/**
 * 
 * @author python
 *an AI unit is savable and mutateable
 */
public class Strand {
	private ArrayList<Codon> codons;
	private int inMax;
	private int inMin;
	private int len;
	private double max;
	private double min;
	private double difference;
	/**
	 * creates a new strand
	 * @param number number of starting codons
	 * @param len  length of input
	 * @param max max input
	 * @param min min input.
	 */
	public Strand(int number, int len, int max, int min) {
		this.len = len;
		inMax = max;
		inMin = min;
		codons = new ArrayList<Codon>();
		for (int i = 0; i < number; i++) addCodon();
		setExtreems();
	}
	/**
	 * creates a strand from a list of codons
	 * @param codons list of codons
	 */
	public Strand(ArrayList<Codon> codons)
	{
		this.codons = codons;
		len = codons.get(0).getLen();
		inMax = codons.get(0).getInMax();
		inMin = codons.get(0).getInMin();
		setExtreems();
	}
	/**
	 * creates a strand form a file
	 * @param filePath path to get the file form
	 */
	public Strand(Path filePath)
	{
		codons = new ArrayList<Codon>();
		try (BufferedReader reader = Files.newBufferedReader(filePath)) {
		String[] codonStrings = reader.readLine().split("\\[");
		    for(String line: codonStrings)
		    {
		    	if (line.length() >0)
		        codons.add(new Codon(line));
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		for (int i = 0; i < codons.size(); i++)
		{
			if (codons.get(i).toString().charAt(1) == ']')
			{
				codons.remove(i);
				i--;
			}
		}
		
		len = codons.get(0).getLen();
		inMax = codons.get(0).getInMax();
		inMin = codons.get(0).getInMin();
		setExtreems();
	}
	/**
	 * set approximate maxs and mins for string
	 */
	public void setExtreems()
	{
		setMax(0);
		setMin(0);
		for (int j = 0; j < codons.size(); j++) 
		{
			Codon i = codons.get(j);
			setMax(getMax() + i.getMax());
			setMin(getMin() + i.getMin());
		}
		setDifference(getMax() - getMin());
	}
	/**
	 * turns input into a double
	 * @param info input
	 * @return
	 */
	public double eval(int[] info)
	{
		double ans = 0;
		for(Codon i: codons)
		{
			if (i.toString().charAt(1) == ']')
			{
				//codons.remove(i);
			}
			else
			{
			ans += i.getValue(info);
			}
		}
		return ans;
	}
	/**
	 * saves to given file
	 * @param filePath path of file
	 */
	public void save(Path filePath)
	{
		try (BufferedWriter writer= Files.newBufferedWriter(filePath)) {
		    for (Codon i: codons)
		    {
		    	writer.write(i.toString());
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		    
		}
	}
	/**
	 * creates identical strand
	 * @return new strand
	 */
	public Strand Duplicate()
	{
		return new Strand(codons);
	}
	/**
	 * adds a new codon
	 */
	public void addCodon() {
		codons.add(new Codon(len, inMax, inMin));
	}
	/**
	 * removes a random codon
	 */
	public void removeCodon()
	{
		codons.remove((int)(Math.random()*codons.size()));
	}
	/**
	 * determines a move based on input and the move options
	 * @param info input
	 * @param options number of possible moves
	 * @return number of move chosen
	 */
	public int move(int[] info, int options)
	{
		double num = eval(info);
		return Math.abs(((int)(((num - getMin())/ getDifference() * options))) % options);
	}
	/**
	 * get approximate max 
	 * @return approximate max 
	 */
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * get approximate min 
	 * @return approximate min 
	 */
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * get difference between min and max
	 * @return get difference between min and max
	 */
	public double getDifference() {
		return difference;
	}
	public void setDifference(double difference) {
		this.difference = difference;
	}
}
