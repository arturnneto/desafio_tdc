package com.artur.callforpapers.service.papers.impl;

import com.artur.callforpapers.domain.entities.auth.RoleEntity;
import com.artur.callforpapers.repositories.auth.RoleRepository;
import com.artur.callforpapers.service.papers.RoleService;
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
