package com.gitgudgang.fakeperson.mapper;

import com.gitgudgang.fakeperson.domain.Address;
import com.gitgudgang.fakeperson.domain.Person;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.util.List;

import static com.gitgudgang.fakeperson.dto.PersonDTO.*;

@Mapper(componentModel = "spring", imports = {LocalDate.class})
public interface PersonMapper {
    CprDTO personToCprDTO(Person person);

    NameCprDTO personToNameCprDTO(Person person);

    NameCprDobDTO personToNameCprDobDTO(Person person);

    CprNameGenderDTO personToCprNameGenderDTO(Person person);

    CprNameGenderDobDTO personToCprNameGenderDobDTO(Person person);

    AddressDTO addressToAddressDTO(Address address);

    PhoneDTO personToPhoneDTO(Person person);

    FullPersonDTO personToFullPersonDTO(Person person);

    List<FullPersonDTO> personToFullPersonDTOList(List<Person> persons);
}
