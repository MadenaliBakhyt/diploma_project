package com.example.diplomaproject.dto;

import com.example.diplomaproject.entities.RoleEntity;
import com.example.diplomaproject.entities.UserEntity;
import com.example.diplomaproject.entities.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String userSecondname;
    private String userThirdname;
    private String iin;
    private String phone_number;
    private String imageUrl;
    private Collection<RoleEntity> roles;
    public UserDto(UserEntity userEntity){
        this.id=userEntity.getId();
        this.username=userEntity.getUsername();
        this.userSecondname= userEntity.getUserSecondname();
        this.userThirdname=userEntity.getUserThirdname();
        this.iin=userEntity.getIin();
        this.phone_number=userEntity.getPhone_number();
        this.roles=userEntity.getRoles();
        this.imageUrl=userEntity.getImageUrl();
    }
}
