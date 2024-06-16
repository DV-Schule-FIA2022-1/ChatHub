package chat.users;

import lombok.Getter;

public class Address
{
    @Getter
    private String street;
    @Getter
    private String city;
    @Getter
    private String zipCode;
    @Getter
    private String country;

    public Address(String street, String city, String zipCode, String country)
    {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public String toString()
    {
        return street + city + zipCode + country;
    }
}
