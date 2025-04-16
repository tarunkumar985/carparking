package com.example.carparking.auth.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.carparking.auth.dto.UserRequestDTO;
import com.example.carparking.auth.dto.UserResponseDTO;
import com.example.carparking.auth.entity.Users;

@Mapper(componentModel  = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Users toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(Users user);
}
