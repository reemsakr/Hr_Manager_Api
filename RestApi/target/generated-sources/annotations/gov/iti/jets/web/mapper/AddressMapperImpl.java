package gov.iti.jets.web.mapper;

import gov.iti.jets.web.dto.AddressDto;
import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.entities.Employee;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-06T01:45:08+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setId( addressDto.getId() );
        address.setEmployee( addressDto.getEmployee() );
        address.setStreet( addressDto.getStreet() );
        address.setCity( addressDto.getCity() );
        address.setPostalCode( addressDto.getPostalCode() );

        return address;
    }

    @Override
    public AddressDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        Integer id = null;
        String street = null;
        String city = null;
        String postalCode = null;
        Employee employee = null;

        id = address.getId();
        street = address.getStreet();
        city = address.getCity();
        postalCode = address.getPostalCode();
        employee = address.getEmployee();

        AddressDto addressDto = new AddressDto( id, street, city, postalCode, employee );

        return addressDto;
    }

    @Override
    public Address partialUpdate(AddressDto addressDto, Address address) {
        if ( addressDto == null ) {
            return address;
        }

        if ( addressDto.getId() != null ) {
            address.setId( addressDto.getId() );
        }
        if ( addressDto.getEmployee() != null ) {
            address.setEmployee( addressDto.getEmployee() );
        }
        if ( addressDto.getStreet() != null ) {
            address.setStreet( addressDto.getStreet() );
        }
        if ( addressDto.getCity() != null ) {
            address.setCity( addressDto.getCity() );
        }
        if ( addressDto.getPostalCode() != null ) {
            address.setPostalCode( addressDto.getPostalCode() );
        }

        return address;
    }
}
