package com.artur.estoqueapi.service.stock;

import com.artur.estoqueapi.domain.entities.auth.RoleEntity;

public interface RoleService {

    RoleEntity getBasicRole();

    RoleEntity getAdminRole();
}
