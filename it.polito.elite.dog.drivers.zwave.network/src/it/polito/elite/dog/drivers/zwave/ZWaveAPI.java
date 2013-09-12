package it.polito.elite.dog.drivers.zwave;

/**
 * http://wiki.micasaverde.com/index.php/ZWave_Command_Classes
 * http://www.agocontrol.com/trac/browser/agocontrol/agocontrol/devices/zwave/ZWApi.h
 */
public class ZWaveAPI
{
	public static final int ROOT_ELEMENT = 			0;
	
	public static final int BASIC_TYPE_CONTROLLER = 			0x01;
	public static final int BASIC_TYPE_STATIC_CONTROLLER = 		0x02;
	public static final int BASIC_TYPE_SLAVE = 					0x03;
	public static final int BASIC_TYPE_ROUTING_SLAVE = 			0x04;

	public static final int GENERIC_TYPE_GENERIC_CONTROLLER = 	0x01;
	public static final int GENERIC_TYPE_STATIC_CONTROLLER = 	0x02;
	public static final int GENERIC_TYPE_AV_CONTROL_POINT = 	0x03;
	public static final int GENERIC_TYPE_DISPLAY = 				0x06;
	public static final int GENERIC_TYPE_GARAGE_DOOR = 			0x07;
	public static final int GENERIC_TYPE_THERMOSTAT = 			0x08;
	public static final int GENERIC_TYPE_WINDOW_COVERING = 		0x09;
	public static final int GENERIC_TYPE_REPEATER_SLAVE = 		0x0F;
	public static final int GENERIC_TYPE_SWITCH_BINARY = 		0x10;
	public static final int GENERIC_TYPE_SWITCH_MULTILEVEL = 	0x11;
	public static final int GENERIC_TYPE_SWITCH_REMOTE = 		0x12;
	public static final int GENERIC_TYPE_SWITCH_TOGGLE = 		0x13;
	public static final int GENERIC_TYPE_SENSOR_BINARY = 		0x20;
	public static final int GENERIC_TYPE_SENSOR_MULTILEVEL = 	0x21;
	public static final int GENERIC_TYPE_SENSOR_ALARM =			0xa1;
	public static final int GENERIC_TYPE_WATER_CONTROL =		0x22;
	public static final int GENERIC_TYPE_METER_PULSE = 			0x30;
	public static final int GENERIC_TYPE_ENTRY_CONTROL = 		0x40;
	public static final int GENERIC_TYPE_SEMI_INTEROPERABLE = 	0x50;
	public static final int GENERIC_TYPE_NON_INTEROPERABLE = 	0xFF;
	
	public static final int SPECIFIC_TYPE_NOT_USED =				0x00;
	public static final int SPECIFIC_TYPE_POWER_SWITCH_MULTILEVEL=	0x01;
	public static final int SPECIFIC_TYPE_MOTOR_MULTIPOSITION =		0x03;
	public static final int SPECIFIC_TYPE_SCENE_SWITCH_MULTILEVEL =	0x04;
	public static final int SPECIFIC_TYPE_CLASS_A_MOTOR_CONTROL	=	0x05;
	public static final int SPECIFIC_TYPE_CLASS_B_MOTOR_CONTROL	=	0x06;
	public static final int SPECIFIC_TYPE_CLASS_C_MOTOR_CONTROL	=	0x07;
	public static final int SPECIFIC_TYPE_ADV_ZENSOR_NET_SMOKE_SENSOR =		0x0a;
	public static final int SPECIFIC_TYPE_BASIC_ROUTING_SMOKE_SENSOR =		0x06;
	public static final int SPECIFIC_TYPE_BASIC_ZENSOR_NET_SMOKE_SENSOR =	0x08;
	public static final int SPECIFIC_TYPE_ROUTING_SMOKE_SENSOR =			0x07;
	public static final int SPECIFIC_TYPE_ZENSOR_NET_SMOKE_SENSOR =			0x09;

	public static final int COMMAND_CLASS_MARK			=	0xef;
	public static final int COMMAND_CLASS_BASIC			=	0x20;
	public static final int COMMAND_CLASS_VERSION			=	0x86;
	public static final int COMMAND_CLASS_BATTERY			=	0x80;
	public static final int COMMAND_CLASS_WAKE_UP = 	0x84;
	public static final int COMMAND_CLASS_CONTROLLER_REPLICATION = 	0x21;
	public static final int COMMAND_CLASS_SWITCH_MULTILEVEL = 	0x26;
	public static final int COMMAND_CLASS_SWITCH_ALL		=	0x27;
	public static final int COMMAND_CLASS_SENSOR_BINARY		=	0x30;
	public static final int COMMAND_CLASS_SENSOR_MULTILEVEL	=		0x31;
	public static final int COMMAND_CLASS_SENSOR_ALARM			=0x9c;
	public static final int COMMAND_CLASS_ALARM			=	0x71;
	public static final int COMMAND_CLASS_MULTI_CMD = 0x8F;
	public static final int COMMAND_CLASS_CLIMATE_CONTROL_SCHEDULE	=	0x46;
	public static final int COMMAND_CLASS_CLOCK			=	0x81;
	public static final int COMMAND_CLASS_ASSOCIATION		=	0x85;
	public static final int COMMAND_CLASS_CONFIGURATION		=	0x70;
	public static final int COMMAND_CLASS_MANUFACTURER_SPECIFIC=		0x72;
	public static final int COMMAND_CLASS_APPLICATION_STATUS 	=	0x22;
	public static final int COMMAND_CLASS_ASSOCIATION_COMMAND_CONFIGURATION =0x9B;
	public static final int COMMAND_CLASS_AV_CONTENT_DIRECTORY_MD	=	0x95;
	public static final int COMMAND_CLASS_AV_CONTENT_SEARCH_MD	=	0x97;
	public static final int COMMAND_CLASS_AV_RENDERER_STATUS	=	0x96;
	public static final int COMMAND_CLASS_AV_TAGGING_MD	=		0x99;
	public static final int COMMAND_CLASS_BASIC_WINDOW_COVERING=		0x50;
	public static final int COMMAND_CLASS_CHIMNEY_FAN=			0x2A;
	public static final int COMMAND_CLASS_COMPOSITE	=			0x8D;
	public static final int COMMAND_CLASS_DOOR_LOCK	=			0x62;
	public static final int COMMAND_CLASS_ENERGY_PRODUCTION	=		0x90;
	public static final int COMMAND_CLASS_FIRMWARE_UPDATE_MD	=	0x7a;
	public static final int COMMAND_CLASS_GEOGRAPHIC_LOCATION	=	0x8C;
	public static final int COMMAND_CLASS_GROUPING_NAME	=		0x7B;
	public static final int COMMAND_CLASS_HAIL			=	0x82;
	public static final int COMMAND_CLASS_INDICATOR		=		0x87;
	public static final int COMMAND_CLASS_IP_CONFIGURATION	=		0x9A;
	public static final int COMMAND_CLASS_LANGUAGE	=			0x89;
	public static final int COMMAND_CLASS_LOCK			=	0x76;
	public static final int COMMAND_CLASS_MANUFACTURER_PROPRIETARY	=	0x91;
	public static final int COMMAND_CLASS_METER_PULSE		=	0x35;
	public static final int COMMAND_CLASS_METER			=	0x32;
	public static final int COMMAND_CLASS_MTP_WINDOW_COVERING	=	0x51;
	public static final int COMMAND_CLASS_MULTI_INSTANCE_ASSOCIATION=	0x8E;
	public static final int COMMAND_CLASS_MULTI_INSTANCE		=	0x60;
	public static final int COMMAND_CLASS_NO_OPERATION			=0x00;
	public static final int COMMAND_CLASS_NODE_NAMING		=	0x77;
	public static final int COMMAND_CLASS_NON_INTEROPERABLE	=		0xf0;
	public static final int COMMAND_CLASS_POWERLEVEL		=	0x73;
	public static final int COMMAND_CLASS_PROPRIETARY		=	0x88;
	public static final int COMMAND_CLASS_PROTECTION		=	0x75;
	public static final int COMMAND_CLASS_REMOTE_ASSOCIATION_ACTIVATE	=0x7c;
	public static final int COMMAND_CLASS_REMOTE_ASSOCIATION	=	0x7d;
	public static final int COMMAND_CLASS_SCENE_ACTIVATION		=	0x2b;
	public static final int COMMAND_CLASS_SCENE_ACTUATOR_CONF	=	0x2C;
	public static final int COMMAND_CLASS_SCENE_CONTROLLER_CONF	=	0x2D;
	public static final int COMMAND_CLASS_SCREEN_ATTRIBUTES	=		0x93;
	public static final int COMMAND_CLASS_SCREEN_MD		=		0x92;
	public static final int COMMAND_CLASS_SECURITY			=	0x98;
	public static final int COMMAND_CLASS_SENSOR_CONFIGURATION	=	0x9E;
	public static final int COMMAND_CLASS_SILENCE_ALARM		=	0x9d;
	public static final int COMMAND_CLASS_SIMPLE_AV_CONTROL		=	0x94;
	public static final int COMMAND_CLASS_SWITCH_BINARY			=0x25;
	public static final int COMMAND_CLASS_SWITCH_TOGGLE_BINARY		=0x28;
	public static final int COMMAND_CLASS_SWITCH_TOGGLE_MULTILEVEL	=	0x29;
	public static final int COMMAND_CLASS_THERMOSTAT_FAN_MODE	=	0x44;
	public static final int COMMAND_CLASS_THERMOSTAT_FAN_STATE	=	0x45;
	public static final int COMMAND_CLASS_THERMOSTAT_HEATING	=	0x38;
	public static final int COMMAND_CLASS_THERMOSTAT_MODE		=	0x40;
	public static final int COMMAND_CLASS_THERMOSTAT_OPERATING_STATE=	0x42;
	public static final int COMMAND_CLASS_THERMOSTAT_SETBACK=		0x47;
	public static final int COMMAND_CLASS_THERMOSTAT_SETPOINT	=	0x43;
	public static final int COMMAND_CLASS_TIME_PARAMETERS	=		0x8B;
	public static final int COMMAND_CLASS_TIME			=	0x8a;
	public static final int COMMAND_CLASS_USER_CODE		=		0x63;
	public static final int COMMAND_CLASS_ZIP_ADV_CLIENT	=		0x34;
	public static final int COMMAND_CLASS_ZIP_ADV_SERVER		=	0x33;
	public static final int COMMAND_CLASS_ZIP_ADV_SERVICES	=		0x2F;
	public static final int COMMAND_CLASS_ZIP_CLIENT		=	0x2e;
	public static final int COMMAND_CLASS_ZIP_SERVER		=	0x24;
	public static final int COMMAND_CLASS_ZIP_SERVICES		=	0x23;
}
