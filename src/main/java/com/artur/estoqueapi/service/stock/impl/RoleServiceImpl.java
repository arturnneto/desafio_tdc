package com.artur.estoqueapi.service.stock.impl;

import com.artur.estoqueapi.domain.entities.auth.RoleEntity;
import com.artur.estoqueapi.repositories.RoleRepository;
import com.artur.estoqueapi.service.stock.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public RoleEntity getBasicRole() {
        return roleRepository.findByRoleName(RoleEntity.Values.BASIC.name().toLowerCase());
    }

    @Override
    public RoleEntity getAdminRole() {
        return roleRepository.findByRoleName(RoleEntity.Values.ADMIN.name().toLowerCase());
    }
}
