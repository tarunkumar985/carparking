package com.example.carparking.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carparking.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}