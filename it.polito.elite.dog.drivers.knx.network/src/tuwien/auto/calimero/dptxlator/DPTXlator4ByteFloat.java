/*
 * (c) Dario Bonino, e-Lite research group, http://elite.polito.it 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed 
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License.
 */
package tuwien.auto.calimero.dptxlator;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import tuwien.auto.calimero.exception.KNXFormatException;
import tuwien.auto.calimero.exception.KNXIllegalArgumentException;
import tuwien.auto.calimero.log.LogLevel;

/**
 * Translator for KNX DPTs with main number 14, type 4-byte float.
 * 
 * The KNX data type width is 4 bytes.
 * 
 * This type is a two byte floating format with a maximum usable range of
 * {@link Float}.NEGATIVE_INFINITY to +{@link Float}.POSITIVE_INIFINITY.
 * 
 * This translator will check and enforce DPT specific limits in all methods
 * working with java values (e.g. setValue(float)).
 * 
 * Data methods for KNX data (e.g. DPTXlator.setData(byte[]) accept all data
 * within the maximum usable range.
 * 
 * In value methods expecting a string type, the value is a float type
 * representation.
 * 
 * The default return value after creation is 0.0.
 * 
 * @author <a href="mailto:dario.bonino@polito.it">Dario Bonino </a>
 * 
 */
public class DPTXlator4ByteFloat extends DPTXlator
{
	
	public static final DPT DPT_ACCELERATION = new DPT("14.000", "Acceleration", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.METRES_PER_SQUARE_SECOND.toString());
	public static final DPT DPT_ACCELERATION_ANGULAR = new DPT("14.001", "Angular Acceleration", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.RADIAN.divide(SI.SECOND.pow(2)).toString());
	public static final DPT DPT_ACTIVATION_ENERGY = new DPT("14.002", "Activation Energy", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.JOULE.divide(SI.MOLE).toString());
	public static final DPT DPT_ACTIVITY = new DPT("14.003", "Activity (Radioactive)", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.SECOND.pow(-1).toString());
	public static final DPT DPT_MOL = new DPT("14.004", "Amount of substance (moles)", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.MOLE.toString());
	public static final DPT DPT_AMPLITUDE = new DPT("14.005", "Amplitude", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, "");
	public static final DPT DPT_ANGLERAD = new DPT("14.006", "Angle in radians", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.RADIAN.toString());
	public static final DPT DPT_ANGLEDEG = new DPT("14.007", "Angle in degrees", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, NonSI.DEGREE_ANGLE.toString());
	public static final DPT DPT_ANGULAR_MOMENTUM = new DPT("14.008", "Angular momentum",
			"" + Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.JOULE.times(SI.SECOND).toString());
	public static final DPT DPT_ANGULAR_VELOCITY = new DPT("14.009", "Angular Velocity",
			"" + Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.RADIAN.divide(SI.SECOND).toString());
	public static final DPT DPT_AREA = new DPT("14.010", "Area", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.SQUARE_METRE.toString());
	public static final DPT DPT_CAPACITANCE = new DPT("14.011", "Capacitance", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.FARAD.toString());
	public static final DPT DPT_CHARGEDENSITY_SURFACE = new DPT("14.012", "Charge density over a surface", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.COULOMB.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_CHARGEDENSITY_VOLUME = new DPT("14.013", "Charge density over a volume", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.COULOMB.divide(SI.CUBIC_METRE).toString());
	public static final DPT DPT_COMPRESSIBILITY = new DPT("14.014", "Compressibility", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.SQUARE_METRE.divide(SI.NEWTON).toString());
	public static final DPT DPT_CONDUCTANCE = new DPT("14.015", "Conductance", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.SIEMENS.toString());
	public static final DPT DPT_ELECTRICAL_CONDUCTIVITY = new DPT("14.016", "Electrical Conductivity", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.SIEMENS.divide(SI.METER).toString());
	public static final DPT DPT_DENSITY = new DPT("14.017", "Density", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.KILOGRAM.divide(SI.CUBIC_METRE).toString());
	public static final DPT DPT_ELECTRIC_CHARGE = new DPT("14.018", "Electric Charge", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.COULOMB.toString());
	public static final DPT DPT_ELECTRIC_CURRENT = new DPT("14.019", "Electric Current",
			"" + Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.AMPERE.toString());
	public static final DPT DPT_ELECTRIC_CURRENT_MILLIS = new DPT("14.019m", "Electric Current",
			"" + Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.MILLI(SI.AMPERE).toString());
	public static final DPT DPT_ELECTRIC_CURRENT_DENSITY = new DPT("14.020", "Electric Current Density", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.AMPERE.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_ELECTRIC_DIPOLE_MOMENT = new DPT("14.021", "Electric Dipole Moment", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.COULOMB.times(SI.METER).toString());
	public static final DPT DPT_ELECTRIC_DISPLACEMENT = new DPT("14.022", "Electric Displacement", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.COULOMB.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_ELECTRIC_FIELDSTRENGHT = new DPT("14.023", "Electric Field Strenght", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.VOLT.divide(SI.METER).toString());
	public static final DPT DPT_ELECTRIC_FLUX = new DPT("14.024", "Electric Flux", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.VOLT.times(SI.METER).toString());
	public static final DPT DPT_ELECTRIC_FLUX_DENSITY = new DPT("14.025", "Electric Flux density", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.VOLT.times(SI.METER).divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_ELECTRIC_POLARIZATION = new DPT("14.026", "Electric Polarization", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.COULOMB.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_ELECTRIC_POTENTIAL = new DPT("14.027", "Electric Potential", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.VOLT.toString());
	public static final DPT DPT_ELECTRIC_POTENTIAL_MILLIS = new DPT("14.027m", "Electric Potential", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.MILLI(SI.VOLT).toString());
	public static final DPT DPT_ELECTRIC_POTENTIAL_DIFFERENCE = new DPT("14.028", "Electric Potential Difference", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.VOLT.toString());
	public static final DPT DPT_ELECTRIC_POTENTIAL_DIFFERENCE_MILLIS = new DPT("14.028m", "Electric Potential Difference", ""
			+ Float.NEGATIVE_INFINITY, "" + Float.POSITIVE_INFINITY, SI.MILLI(SI.VOLT).toString());
	public static final DPT DPT_ELECTROMAGNETIC_MOMENT = new DPT("14.029", "Electromagnetic Moment", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.AMPERE.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_ELECTROMOTIVE_FORCE = new DPT("14.030", "Electromotive Force", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.VOLT.toString());
	public static final DPT DPT_ENERGY = new DPT("14.031", "Energy", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.JOULE.toString());
	public static final DPT DPT_FORCE = new DPT("14.032", "Force", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.NEWTON.toString());
	public static final DPT DPT_FREQUENCY = new DPT("14.033", "Frequency", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.HERTZ.toString());
	public static final DPT DPT_ANGULAR_FREQUENCY = new DPT("14.034", "Angular Frequency", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.RADIAN.divide(SI.SECOND).toString());
	public static final DPT DPT_HEAT_CAPACITY = new DPT("14.035", "Heat Capacity", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.JOULE.divide(SI.KELVIN).toString());
	public static final DPT DPT_HEAT_FLOWRATE = new DPT("14.036", "Heat Flow rate", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.WATT.toString());
	public static final DPT DPT_HEAT_QUANTITY = new DPT("14.037", "Heat Quantity", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.JOULE.toString());
	public static final DPT DPT_IMPEDANCE = new DPT("14.038", "Impedance", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.OHM.toString());
	public static final DPT DPT_LENGTH = new DPT("14.039", "Length", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.METER.toString());
	public static final DPT DPT_LIGHT_QUANTITY = new DPT("14.040", "Light quantity", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.LUMEN.times(SI.SECOND).toString());
	public static final DPT DPT_LUMINANCE = new DPT("14.041", "Luminance", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.CANDELA.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_LUMINOUS_FLUX = new DPT("14.042", "Luminous Flux", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.LUMEN.toString());
	public static final DPT DPT_LUMINOUS_INTENSITY = new DPT("14.043", "Amount of substance (moles)", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.CANDELA.toString());
	public static final DPT DPT_MAGNETIC_FIELDSTRENGHT = new DPT("14.044", "Magnetic Field Strenght", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.AMPERE.divide(SI.METER).toString());
	public static final DPT DPT_MAGNETIC_FLUX = new DPT("14.045", "Magnetic Flux", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.WEBER.toString());
	public static final DPT DPT_MAGNETIC_FLUX_DENSITY = new DPT("14.046", "Magnetic Flux Density", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.TESLA.toString());
	public static final DPT DPT_MAGNETIC_MOMENT = new DPT("14.047", "Magnetic Moment", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.AMPERE.times(SI.SQUARE_METRE).toString());
	public static final DPT DPT_MAGNETIC_POLARIZATION = new DPT("14.048", "Magnetic Polarization", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.TESLA.toString());
	public static final DPT DPT_MAGNETIZATION = new DPT("14.049", "Magnetization", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.AMPERE.divide(SI.METER).toString());
	public static final DPT DPT_MAGNETOMOTIVE_FORCE = new DPT("14.050", "Magnetomotive force", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.AMPERE.toString());
	public static final DPT DPT_MASS = new DPT("14.051", "Mass", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.KILOGRAM.toString());
	public static final DPT DPT_MASS_FLUX = new DPT("14.052", "Mass Flux", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.KILOGRAM.divide(SI.SECOND).toString());
	public static final DPT DPT_MOMENTUM = new DPT("14.053", "Momentum", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.NEWTON.divide(SI.SECOND).toString());
	public static final DPT DPT_PHASE_ANGLE_RAD = new DPT("14.054", "Phase angle in radians", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, SI.RADIAN.toString());
	public static final DPT DPT_PHASE_ANGLE_DEG = new DPT("14.055", "Phase angle in degrees", "" + Float.NEGATIVE_INFINITY,
			"" + Float.POSITIVE_INFINITY, NonSI.DEGREE_ANGLE.toString());
	public static final DPT DPT_POWER = new DPT("14.056", "Power", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.WATT.toString());
	public static final DPT DPT_REACTIVE_POWER = new DPT("14.056r", "Power", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, "var");
	public static final DPT DPT_APPARENT_POWER = new DPT("14.056a", "Power", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.VOLT.times(SI.AMPERE).toString());
	public static final DPT DPT_POWER_FACTOR = new DPT("14.057", "Power Factor", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, "");
	public static final DPT DPT_PRESSURE = new DPT("14.058", "Pressure", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.PASCAL.toString());
	public static final DPT DPT_REACTANCE = new DPT("14.059", "Reactance", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.OHM.toString());
	public static final DPT DPT_RESISTANCE = new DPT("14.060", "Resistance", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.OHM.toString());
	public static final DPT DPT_RESISTIVITY = new DPT("14.061", "Resistivity", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.OHM.times(SI.METER).toString());
	public static final DPT DPT_SELF_INDUCTANCE = new DPT("14.062", "Self-Inductance", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.HENRY.toString());
	public static final DPT DPT_SOLID_ANGLE = new DPT("14.063", "Solid Angle", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.STERADIAN.toString());
	public static final DPT DPT_SOUND_INTENSITY = new DPT("14.064", "Sound Intensity", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.WATT.divide(SI.SQUARE_METRE).toString());
	public static final DPT DPT_SPEED = new DPT("14.065", "Speed", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.METERS_PER_SECOND.toString());
	public static final DPT DPT_STRESS = new DPT("14.066", "Stress", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.PASCAL.toString());
	public static final DPT DPT_SURFACE_TENSION = new DPT("14.067", "Surface Tension", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.NEWTON.divide(SI.METER).toString());
	public static final DPT DPT_COMMON_TEMPERATURE = new DPT("14.068", "Common Temperature", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.CELSIUS.toString());
	public static final DPT DPT_ABSOLUTE_TEMPERATURE = new DPT("14.069", "Absolute Temperature", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.KELVIN.toString());
	public static final DPT DPT_TEMPERATURE_DIFFERENCE = new DPT("14.070", "Temperature Difference", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.KELVIN.toString());
	public static final DPT DPT_THERMAL_CAPACITY = new DPT("14.071", "Thermal Capacity", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.JOULE.divide(SI.KELVIN).toString());
	public static final DPT DPT_THERMAL_CONDUCTIVITY = new DPT("14.072", "Thermal Conductivity", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.WATT.divide((SI.METER.times(SI.KELVIN))).toString());
	public static final DPT DPT_THERMOELECTRIC_POWER = new DPT("14.073", "Thermoelectric Power", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.VOLT.divide(SI.KELVIN).toString());
	public static final DPT DPT_TIME = new DPT("14.074", "Time", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.SECOND.toString());
	public static final DPT DPT_TORQUE = new DPT("14.075", "Torque", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.NEWTON.times(SI.METER).toString());
	public static final DPT DPT_VOLUME = new DPT("14.076", "Volume", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.CUBIC_METRE.toString());
	public static final DPT DPT_VOLUME_FLUX = new DPT("14.077", "Volume Flux", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.CUBIC_METRE.divide(SI.SECOND).toString());
	public static final DPT DPT_WEIGHT = new DPT("14.078", "Weight", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.NEWTON.toString());
	public static final DPT DPT_WORK = new DPT("14.079", "Work", "" + Float.NEGATIVE_INFINITY, ""
			+ Float.POSITIVE_INFINITY, SI.JOULE.toString());

	
	private static final Map<String, DPT> types;
	
	static
	{
		types = new HashMap<String, DPT>();
		types.put(DPT_ACCELERATION.getID(), DPT_ACCELERATION);
		types.put(DPT_ACCELERATION_ANGULAR.getID(), DPT_ACCELERATION_ANGULAR);
		types.put(DPT_ACTIVATION_ENERGY.getID(), DPT_ACTIVATION_ENERGY);
		types.put(DPT_ACTIVITY.getID(), DPT_ACTIVITY);
		types.put(DPT_MOL.getID(), DPT_MOL);
		types.put(DPT_AMPLITUDE.getID(), DPT_AMPLITUDE);
		types.put(DPT_ANGLERAD.getID(), DPT_ANGLERAD);
		types.put(DPT_ANGLEDEG.getID(), DPT_ANGLEDEG);
		types.put(DPT_ANGULAR_MOMENTUM.getID(), DPT_ANGULAR_MOMENTUM);
		types.put(DPT_ANGULAR_VELOCITY.getID(), DPT_ANGULAR_VELOCITY);
		types.put(DPT_AREA.getID(), DPT_AREA);
		types.put(DPT_CAPACITANCE.getID(), DPT_CAPACITANCE);
		types.put(DPT_CHARGEDENSITY_SURFACE.getID(), DPT_CHARGEDENSITY_SURFACE);
		types.put(DPT_CHARGEDENSITY_VOLUME.getID(), DPT_CHARGEDENSITY_VOLUME);
		types.put(DPT_COMPRESSIBILITY.getID(), DPT_COMPRESSIBILITY);
		types.put(DPT_CONDUCTANCE.getID(), DPT_CONDUCTANCE);
		types.put(DPT_ELECTRICAL_CONDUCTIVITY.getID(), DPT_ELECTRICAL_CONDUCTIVITY);
		types.put(DPT_DENSITY.getID(), DPT_DENSITY);
		types.put(DPT_ELECTRIC_CHARGE.getID(), DPT_ELECTRIC_CHARGE);
		types.put(DPT_ELECTRIC_CURRENT.getID(), DPT_ELECTRIC_CURRENT);
		types.put(DPT_ELECTRIC_CURRENT_MILLIS.getID(), DPT_ELECTRIC_CURRENT_MILLIS);
		types.put(DPT_ELECTRIC_CURRENT_DENSITY.getID(), DPT_ELECTRIC_CURRENT_DENSITY);
		types.put(DPT_ELECTRIC_DIPOLE_MOMENT.getID(), DPT_ELECTRIC_DIPOLE_MOMENT);
		types.put(DPT_ELECTRIC_DISPLACEMENT.getID(), DPT_ELECTRIC_DISPLACEMENT);
		types.put(DPT_ELECTRIC_FIELDSTRENGHT.getID(), DPT_ELECTRIC_FIELDSTRENGHT);
		types.put(DPT_ELECTRIC_FLUX.getID(), DPT_ELECTRIC_FLUX);
		types.put(DPT_ELECTRIC_FLUX_DENSITY.getID(), DPT_ELECTRIC_FLUX_DENSITY);
		types.put(DPT_ELECTRIC_POLARIZATION.getID(), DPT_ELECTRIC_POLARIZATION);
		types.put(DPT_ELECTRIC_POTENTIAL.getID(), DPT_ELECTRIC_POTENTIAL);
		types.put(DPT_ELECTRIC_POTENTIAL_MILLIS.getID(), DPT_ELECTRIC_POTENTIAL_MILLIS);
		types.put(DPT_ELECTRIC_POTENTIAL_DIFFERENCE.getID(), DPT_ELECTRIC_POTENTIAL_DIFFERENCE);
		types.put(DPT_ELECTRIC_POTENTIAL_DIFFERENCE_MILLIS.getID(), DPT_ELECTRIC_POTENTIAL_DIFFERENCE_MILLIS);
		types.put(DPT_ELECTROMAGNETIC_MOMENT.getID(), DPT_ELECTROMAGNETIC_MOMENT);
		types.put(DPT_ELECTROMOTIVE_FORCE.getID(), DPT_ELECTROMOTIVE_FORCE);
		types.put(DPT_ENERGY.getID(), DPT_ENERGY);
		types.put(DPT_FORCE.getID(), DPT_FORCE);
		types.put(DPT_FREQUENCY.getID(),DPT_FREQUENCY); 
		types.put(DPT_ANGULAR_FREQUENCY.getID(), DPT_ANGULAR_FREQUENCY);
		types.put(DPT_HEAT_CAPACITY.getID(), DPT_HEAT_CAPACITY);
		types.put(DPT_HEAT_FLOWRATE.getID(), DPT_HEAT_FLOWRATE);
		types.put(DPT_HEAT_QUANTITY.getID(), DPT_HEAT_QUANTITY);
		types.put(DPT_IMPEDANCE.getID(), DPT_IMPEDANCE);
		types.put(DPT_LENGTH.getID(), DPT_LENGTH);
		types.put(DPT_LIGHT_QUANTITY.getID(), DPT_LIGHT_QUANTITY);
		types.put(DPT_LUMINANCE.getID(), DPT_LUMINANCE);
		types.put(DPT_LUMINOUS_FLUX.getID(), DPT_LUMINOUS_FLUX);
		types.put(DPT_LUMINOUS_INTENSITY.getID(), DPT_LUMINOUS_INTENSITY);
		types.put(DPT_MAGNETIC_FIELDSTRENGHT.getID(), DPT_MAGNETIC_FIELDSTRENGHT);
		types.put(DPT_MAGNETIC_FLUX.getID(), DPT_MAGNETIC_FLUX);
		types.put(DPT_MAGNETIC_FLUX_DENSITY.getID(), DPT_MAGNETIC_FLUX_DENSITY);
		types.put(DPT_MAGNETIC_MOMENT.getID(), DPT_MAGNETIC_MOMENT);
		types.put(DPT_MAGNETIC_POLARIZATION.getID(), DPT_MAGNETIC_POLARIZATION);
		types.put(DPT_MAGNETIZATION.getID(), DPT_MAGNETIZATION);
		types.put(DPT_MAGNETOMOTIVE_FORCE.getID(), DPT_MAGNETOMOTIVE_FORCE);
		types.put(DPT_MASS.getID(), DPT_MASS);
		types.put(DPT_MASS_FLUX.getID(), DPT_MASS_FLUX);
		types.put(DPT_MOMENTUM.getID(), DPT_MOMENTUM);
		types.put(DPT_PHASE_ANGLE_RAD.getID(), DPT_PHASE_ANGLE_RAD);
		types.put(DPT_PHASE_ANGLE_DEG.getID(), DPT_PHASE_ANGLE_DEG);
		types.put(DPT_POWER.getID(), DPT_POWER);
		types.put(DPT_APPARENT_POWER.getID(), DPT_APPARENT_POWER);
		types.put(DPT_REACTIVE_POWER.getID(), DPT_REACTIVE_POWER);
		types.put(DPT_POWER_FACTOR.getID(), DPT_POWER_FACTOR);
		types.put(DPT_PRESSURE.getID(), DPT_PRESSURE);
		types.put(DPT_REACTANCE.getID(), DPT_REACTANCE);
		types.put(DPT_RESISTANCE.getID(), DPT_RESISTANCE);
		types.put(DPT_RESISTIVITY.getID(), DPT_RESISTIVITY);
		types.put(DPT_SELF_INDUCTANCE.getID(), DPT_SELF_INDUCTANCE);
		types.put(DPT_SOLID_ANGLE.getID(), DPT_SOLID_ANGLE);
		types.put(DPT_SOUND_INTENSITY.getID(), DPT_SOUND_INTENSITY);
		types.put(DPT_SPEED.getID(), DPT_SPEED);
		types.put(DPT_STRESS.getID(), DPT_STRESS);
		types.put(DPT_SURFACE_TENSION.getID(), DPT_SURFACE_TENSION);
		types.put(DPT_COMMON_TEMPERATURE.getID(), DPT_COMMON_TEMPERATURE);
		types.put(DPT_ABSOLUTE_TEMPERATURE.getID(), DPT_ABSOLUTE_TEMPERATURE);
		types.put(DPT_TEMPERATURE_DIFFERENCE.getID(), DPT_TEMPERATURE_DIFFERENCE);
		types.put(DPT_THERMAL_CAPACITY.getID(), DPT_THERMAL_CAPACITY);
		types.put(DPT_THERMAL_CONDUCTIVITY.getID(), DPT_THERMAL_CONDUCTIVITY);
		types.put(DPT_THERMOELECTRIC_POWER.getID(), DPT_THERMOELECTRIC_POWER);
		types.put(DPT_TIME.getID(), DPT_TIME);
		types.put(DPT_TORQUE.getID(), DPT_TORQUE);
		types.put(DPT_VOLUME.getID(), DPT_VOLUME);
		types.put(DPT_VOLUME_FLUX.getID(), DPT_VOLUME_FLUX);
		types.put(DPT_WEIGHT.getID(), DPT_WEIGHT);
		types.put(DPT_WORK.getID(), DPT_WORK);
		
		
	}
	
	private final float min;
	private final float max;
	
	/**
	 * Creates a translator for the given datapoint type.
	 * <p>
	 * 
	 * @param dpt
	 *            the requested datapoint type
	 * @throws KNXFormatException
	 *             on not supported or not available DPT
	 */
	public DPTXlator4ByteFloat(DPT dpt) throws KNXFormatException
	{
		this(dpt.getID());
	}
	
	/**
	 * Creates a translator for <code>dptID</code>.
	 * <p>
	 * 
	 * @param dptID
	 *            available implemented datapoint type ID
	 * @throws KNXFormatException
	 *             on wrong formatted or not expected (available) DPT
	 */
	public DPTXlator4ByteFloat(String dptID) throws KNXFormatException
	{
		super(4);
		setTypeID(types, dptID);
		min = getLimit(dpt.getLowerValue());
		max = getLimit(dpt.getUpperValue());
		data = new short[4];
	}
	
	/**
	 * Sets the translation value from a float.
	 * <p>
	 * If succeeded, any other items in the translator are discarded.
	 * 
	 * @param value
	 *            the float value
	 * @throws KNXFormatException
	 *             if <code>value</code>doesn't fit into KNX data type
	 */
	public void setValue(float value) throws KNXFormatException
	{
		final short[] buf = new short[4];
		toDPT(value, buf, 0);
		data = buf;
	}
	
	/**
	 * Returns the first translation item formatted as float.
	 * <p>
	 * 
	 * @return value as float
	 */
	public final float getValueFloat()
	{
		return fromDPT(0);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getAllValues()
	 */
	public String[] getAllValues()
	{
		final String[] buf = new String[data.length / 2];
		for (int i = 0; i < buf.length; ++i)
			buf[i] = makeString(i);
		return buf;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#setData(byte[], int)
	 */
	public void setData(byte[] data, int offset)
	{
		if (offset < 0 || offset > data.length)
			throw new KNXIllegalArgumentException("illegal offset " + offset);
		final int size = (data.length - offset) & ~0x01;
		if (size == 0)
			throw new KNXIllegalArgumentException("data length " + size + " < KNX data type width "
					+ Math.max(1, getTypeSize()));
		this.data = new short[size];
		for (int i = 0; i < size; ++i)
			this.data[i] = ubyte(data[offset + i]);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getData(byte[], int)
	 */
	public byte[] getData(byte[] dst, int offset)
	{
		final int end = Math.min(data.length, dst.length - offset) & ~0x01;
		for (int i = 0; i < end; ++i)
			dst[offset + i] = (byte) data[i];
		return dst;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tuwien.auto.calimero.dptxlator.DPTXlator#getSubTypes()
	 */
	public Map<String, DPT> getSubTypes()
	{
		return types;
	}
	
	/**
	 * @return the subtypes of the 2-byte float translator type
	 * @see DPTXlator#getSubTypesStatic()
	 */
	protected static Map<String, DPT> getSubTypesStatic()
	{
		return types;
	}
	
	private String makeString(int index)
	{
		return appendUnit(String.valueOf(fromDPT(index)));
	}
	
	private float fromDPT(int index)
	{
		// convert data into a buffer array
		byte[] bData = new byte[data.length];
		for (int j = 0; j < data.length; j++)
			bData[j] = (byte) data[j];
		
		// wrap the byte array with a byte buffer object
		ByteBuffer bBuffer = ByteBuffer.wrap(bData);
		
		// extract an IEEE754 float
		return bBuffer.getFloat();
	}
	
	private void toDPT(float value, short[] dst, int index) throws KNXFormatException
	{
		if (value < min || value > max)
			throw logThrow(LogLevel.WARN, "translation error for " + value,
					"value out of range [" + dpt.getLowerValue() + ".." + dpt.getUpperValue() + "]",
					Float.toString(value));
		// encoding
		byte[] bData = new byte[4];
		
		// wrap an empty byte buffer
		ByteBuffer bBuffer = ByteBuffer.wrap(bData);
		
		// encode a float
		bBuffer.putFloat(value);
		
		// extract the byte buffer and convert to an array of short...
		for (int i = 0; i < bData.length; i++)
			dst[i] = ubyte(bData[i]);
	}
	
	protected void toDPT(String value, short[] dst, int index) throws KNXFormatException
	{
		try
		{
			toDPT(Float.parseFloat(removeUnit(value)), dst, index);
		}
		catch (final NumberFormatException e)
		{
			throw logThrow(LogLevel.WARN, "wrong value format " + value, null, value);
		}
	}
	
	private float getLimit(String limit) throws KNXFormatException
	{
		try
		{
			final float f = Float.parseFloat(limit);
			if (f >= Float.NEGATIVE_INFINITY && f <= Float.POSITIVE_INFINITY)
				return f;
		}
		catch (final NumberFormatException e)
		{
		}
		throw logThrow(LogLevel.ERROR, "limit " + limit, "invalid DPT range", limit);
	}
}
