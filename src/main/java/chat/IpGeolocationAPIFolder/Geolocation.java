package chat.IpGeolocationAPIFolder;

import java.math.BigDecimal;

public class Geolocation
{
	  private  String ip;
	  private  String hostname;
	  private  String continentCode;
	  private  String continentName;
	  private  String countryCode2;
	  private  String countryCode3;
	  private  String countryName;
	  private  String countryCapital;
	  private  String stateProvince;
	  private  String stateCode;
	  private  String district;
	  private  String city;
	  private  String zipCode;
	  private  BigDecimal latitude;
	  private  BigDecimal longitude;
	  private  boolean eu;
	  private  String callingCode;
	  private  String countryTLD;
	  private  String countryFlag;
	  private  long geoNameId;
	  private  String isp;
	  private  String connectionType;
	  private  String organization;
	  private  String asn;
	  
	  private GeolocationCurrency currency;
	  private GeolocationTimezone timezone;
	  
	public Geolocation()
	{
		/*final String ip;
		final String hostname;
		final String contintent_code;
		final String continent_name;
	 	final String country_code2;
		final String country_code3;
		final String country_name;
		final String country_capital;
		final String state_prov;
		final String district;
		final String city;
		final int zipcode;
		final BigDecimal latitute;
		final BigDecimal longitude;
		final boolean eu;
		final String callingCode;
		final String countryTLD;
		final String languages;
		final String countryFlag;
		final String isp;
		final String connectionType;
		final String organization;
		final String asn;
		final long geoNameId;*/

	}

	public String getIp()
	{
		return ip;
	}

	public BigDecimal getLatitude()
	{
		return latitude;
	}

	public BigDecimal getLongitude()
	{
		return longitude;
	}
}
