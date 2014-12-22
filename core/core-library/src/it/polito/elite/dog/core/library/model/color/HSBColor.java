/*
 * Dog - Core
 * 
 * Copyright (c) 2010-2013 Dario Bonino and Luigi De Russis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package it.polito.elite.dog.core.library.model.color;

/**
 * @author bonino
 * 
 */
public class HSBColor
{
	private int hue;
	private int saturation;
	private int brightness;

	/**
	 * 
	 */
	public HSBColor()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param hue
	 * @param saturation
	 * @param brightness
	 */
	public HSBColor(int hue, int saturation, int brightness)
	{
		super();
		this.hue = hue;
		this.saturation = saturation;
		this.brightness = brightness;
	}

	/**
	 * @return the hue
	 */
	public int getHue()
	{
		return hue;
	}

	/**
	 * @param hue
	 *            the hue to set
	 */
	public void setHue(int hue)
	{
		this.hue = hue;
	}

	/**
	 * @return the saturation
	 */
	public int getSaturation()
	{
		return saturation;
	}

	/**
	 * @param saturation
	 *            the saturation to set
	 */
	public void setSaturation(int saturation)
	{
		this.saturation = saturation;
	}

	/**
	 * @return the brightness
	 */
	public int getBrightness()
	{
		return brightness;
	}

	/**
	 * @param brightness
	 *            the brightness to set
	 */
	public void setBrightness(int brightness)
	{
		this.brightness = brightness;
	}

	public RGBColor toRGBColor()
	{
		// normalization
		double v = (double) this.brightness / 255.0d;
		double s = (double) this.saturation / 255.0d;
		double h = (double) this.hue * 360.0d / 65535.0d;

		// resulting values
		double r = 0;
		double g = 0;
		double b = 0;

		if (s != 0)
		{
			double i = Math.floor(h / 60);
			double f = h - i;
			double p = v * (1 - s);
			double q = v * (1 - s * f);
			double t = v * (1 - s * (1 - f));

			switch ((int) i)
			{
			case 0:
			{
				r = v;
				g = t;
				b = p;
				break;
			}
			case 1:
			{
				r = q;
				g = v;
				b = p;
				break;
			}
			case 2:
			{
				r = p;
				g = v;
				b = t;
				break;
			}
			case 3:
			{
				r = p;
				g = q;
				b = v;
				break;
			}
			case 4:
			{
				r = t;
				g = p;
				b = v;
				break;
			}
			default:
			{
				r = v;
				g = p;
				b = q;
				break;
			}
			}

		}

		// denormalize
		return new RGBColor((int) (r * 255), (int) (g * 255), (int) (b * 255));

	}
}
