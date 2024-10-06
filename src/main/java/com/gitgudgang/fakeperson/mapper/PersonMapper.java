package com.gitgudgang.fakeperson.mapper;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.domain.Person;
import org.mapstruct.Mapper;

import static com.gitgudgang.fakeperson.dto.PersonDTO.*;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    CprDTO personToCprDTO(Person person);

    NameCprDTO personToNameCprDTO(Person person);

    NameCprDobDTO personToNameCprDobDTO(Person person);

    CprNameGenderDTO personToCprNameGenderDTO(Person person);

    CprNameGenderDobDTO personToCprNameGenderDobDTO(Person person);

    AddressDTO addressToAddressDTO(Address address);

    PhoneDTO personToPhoneDTO(Person person);

    FullPersonDTO personToFullPersonDTO(Person person);
}
