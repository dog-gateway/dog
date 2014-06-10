/**
 * 
 */
package it.polito.elite.dog.core.library.model.color;

/**
 * @author bonino
 * 
 */
public class RGBColor
{

	private int red;
	private int green;
	private int blue;

	/**
	 * 
	 */
	public RGBColor()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param red
	 * @param green
	 * @param blue
	 */
	public RGBColor(int red, int green, int blue)
	{
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * @return the red
	 */
	public int getRed()
	{
		return red;
	}

	/**
	 * @param red
	 *            the red to set
	 */
	public void setRed(int red)
	{
		this.red = red;
	}

	/**
	 * @return the green
	 */
	public int getGreen()
	{
		return green;
	}

	/**
	 * @param green
	 *            the green to set
	 */
	public void setGreen(int green)
	{
		this.green = green;
	}

	/**
	 * @return the blue
	 */
	public int getBlue()
	{
		return blue;
	}

	/**
	 * @param blue
	 *            the blue to set
	 */
	public void setBlue(int blue)
	{
		this.blue = blue;
	}

	public HSBColor toHSB()
	{
		double maxRGB = this.findMax(this.red, this.green, this.blue) / 255.0d;
		double minRGB = this.findMin(this.red, this.green, this.blue) / 255.0d;
		double delta = maxRGB - minRGB;
		
		double red = this.red / 255.0d;
		double green = this.green /255.0d;
		double blue = this.blue / 255.0d;

		double brightness = maxRGB;
		double saturation = maxRGB - minRGB / maxRGB;
		double hue = 65535;

		if (delta != 0)
		{
			if (maxRGB == red)
			{
				hue = 60 * ((green  -  blue ) /  delta) % 360;
			} else if (maxRGB == green)
			{
				hue = 60 * ((blue  - red ) /  delta) + 120;
			} else if (maxRGB == blue)
			{
				hue = 60 * ((red  - green) / delta) + 240;
			}
		}

		// 255 == 0 then the maximum sat and brightness are 254
		return new HSBColor((int)((hue/360)*65535), (int)(saturation*254), (int)(brightness*254));
	}

	private int findMax(int... values)
	{
		// init at the minum value
		int max = Integer.MIN_VALUE;

		// max search
		for (int value : values)
			if (max < value)
				max = value;

		// return the maximum
		return max;
	}

	private int findMin(int... values)
	{
		// init at the minum value
		int min = Integer.MAX_VALUE;

		// max search
		for (int value : values)
			if (min > value)
				min = value;

		// return the maximum
		return min;
	}
}
