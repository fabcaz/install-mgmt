package com.example.installmgmt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.installmgmt.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
    public List<Address> findAllByStreetAddressAndZipcode(String streetAddress, Integer zipcode);
    public List<Address> findAllByStreetAddress(String streetAddress);
    public List<Address> findAllByZipcode(Integer zipcode);
    public List<Address> findAllByCity(String city);

    public List<Address> findAllByState(String State) ;
    public List<Address> findAllByStreetAddressIn(List<String> streetName);
    public List<Address> findAllByZipcodeIn(List<Integer> zipcodes);
    public List<Address> findAllByCityIn(List<String> city);

    public List<Address> findAllByStateIn(List<String> State);
}
