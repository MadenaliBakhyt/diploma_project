package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String iin;
    private String phone_number;

    public UserDto(UserEntity userEntity){
        this.id=userEntity.getId();
        this.username=userEntity.getUsername();
        this.iin=userEntity.getIin();
        this.phone_number=userEntity.getPhone_number();

    }


}
