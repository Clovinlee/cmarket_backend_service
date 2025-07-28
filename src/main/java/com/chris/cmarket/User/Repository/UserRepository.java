package com.chris.cmarket.User.Repository;

import com.chris.cmarket.User.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByUuid(String uuid);

    boolean existsByEmailAndProviderIsNull(String email);

    boolean existsByProviderAndProviderId(String provider, String providerId);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUuid(String Uuid);

}
