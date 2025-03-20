package com.artur.callforpapers.service.papers;

import com.artur.callforpapers.domain.entities.auth.RoleEntity;

public interface RoleService {

    RoleEntity getBasicRole();

    RoleEntity getAdminRole();
}
