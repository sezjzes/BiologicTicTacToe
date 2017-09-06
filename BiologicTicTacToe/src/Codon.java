import java.util.Arrays;

/**
 * 
 * @author Josh Szczesniak
 * A codon is an evaluatory unit it takes info and outputs a number it can be saved to text recreated
 */
public class Codon 
{
	private double[] vars;
	private double coeficient;
	private double max;
	private double min;
	private int len;
	private int inMax;
	private int inMin;
	/**
	 * creates a codon
	 * @param len number of inputs
	 * @param max max possible value of inputs
	 * @param min min possible value of inputs
	 */
	public Codon(int len, int max, int min) 
	{
		setInMin(min);
		setInMax(max);
		this.setLen(len);
		vars = new double[len];
		for (int j = 0; j < vars.length; j++) {
			vars[j] = 2 * Math.random() - 1;
		}

		coeficient = 2*Math.random() - 1;
		int [] big = new int[len];
		for (int i = 0; i < big.length; i++)
		{
			big[i] = inMax;
		}
		double extremeB = getValue(big);
		int [] small = new int[len];
		for (int i = 0; i < small.length; i++)
		{
			small[i] = inMin;
		}
		double extremeS = getValue(small);
		if (extremeB > extremeS)
		{
			setMax(extremeB);
			setMin(extremeS);
		}
		else
		{
			setMin(extremeB);
			setMax(extremeS);
		}
	}
	/**
	 * creates codon from string
	 * @param info string
	 */
	public Codon(String info)
	{
		String[] inf = info.split("_");
		vars = fromString(inf[0]);
		min = Double.parseDouble(inf[1]);
		max = Double.parseDouble(inf[2]);
		coeficient = Double.parseDouble(inf[3]);
	}
	/**
	 * creates a double array from a string
	 * @param string the string to use
	 * @return the double array
	 */
	private double[] fromString(String string) {
	    String[] strings = string.replace("[", "").replace("]", "").split(", ");
	    double[] result = new double[strings.length];
	    for (int i = 0; i < result.length; i++) {
	      result[i] = Double.parseDouble(strings[i]);
	    }
	    return result;
	}
	
	/**
	 * evaluates data
	 * @param values data
	 * @return a double answer to the evaluation
	 */
	public double getValue(int[] values)
	{
		int ans = 1;
		for (int i = 0; i < values.length; i++)
		{
			ans *= Math.pow(values[i], vars[i]) ;
		}
		return coeficient * ans;
	}
	/**
	 * returns an approximate max what happens with the max input
	 * @return an approximate max
	 */
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * returns an approximate min what happens with the max input
	 * @return an approximate min
	 */
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}	
	/**
	 * converts info into a string
	 * @return the info in string form
	 */
	public String toString()
	{
		return "" + Arrays.toString(vars) + "_" + min + "_" + max + "_" + coeficient;
	}
	/**
	 * get the length of the codon 
	 * @return length of the codon 
	 */
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	/**
	 * get the max input form the codon
	 * @return max input
	 */
	public int getInMax() {
		return inMax;
	}
	public void setInMax(int inMax) {
		this.inMax = inMax;
	}
	/**
	 get the min input form the codon
	 * @return min input
	 */
	public int getInMin() {
		return inMin;
	}
	public void setInMin(int inMin) {
		this.inMin = inMin;
	}
}

