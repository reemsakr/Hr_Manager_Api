package gov.iti.jets.web.service;

import gov.iti.jets.web.dto.AddressDto;
import gov.iti.jets.web.mapper.AddressMapper;
import gov.iti.jets.web.persistence.connection.DB;
import gov.iti.jets.web.persistence.entities.Address;
import gov.iti.jets.web.persistence.repository.AddressRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressServices {

    public  List<AddressDto> getAllAddresses(){
        return DB.doInTransaction(em->{
            AddressRepo addressRepo = new AddressRepo(em);
            List<AddressDto> result= new ArrayList<>();
            for(Address address:addressRepo.findAll().get()) {
                result.add(AddressMapper.INSTANCE.toDto(address));
            }
            return result;
        });
    }

    public  Optional<AddressDto> getAddressById(Integer addressId){
        return DB.doInTransaction(em->{
            AddressRepo addressRepo = new AddressRepo(em);
            Optional<Address> address = addressRepo.findById(addressId);
            if(address.isPresent()){
                return  Optional.of(AddressMapper.INSTANCE.toDto(address.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  Optional<AddressDto> updateAddress(AddressDto addressDto){
        return DB.doInTransaction(em->{
            AddressRepo addressRepo = new AddressRepo(em);
            Optional<Address> address = addressRepo.update(AddressMapper.INSTANCE.toEntity(addressDto));
            if(address.isPresent()){
                return  Optional.of(AddressMapper.INSTANCE.toDto(address.get()));
            }
            else{
                return Optional.empty();
            }
        });
    }

    public  int deleteAddressById(Integer addressId){
        Optional<AddressDto> addressFound = getAddressById(addressId);
        if(addressFound.isPresent()){
            DB.doInTransactionWithoutResult(em->{
                AddressRepo addressRepo = new AddressRepo(em);
                addressRepo.deleteById(addressId);
            });
            return 1;
        }
        else{
            return 0;
        }
    }


    public  void addAddress(AddressDto addressDto){
             DB.doInTransactionWithoutResult(em->{
                AddressRepo addressRepo = new AddressRepo(em);
                addressRepo.create(AddressMapper.INSTANCE.toEntity(addressDto));
            });
    }

}
