package mim.auth.service.entity.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mim.auth.service.entity.models.UserDetails;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {


	public List<UserDetails> findByUserIdOrEmailId(String userId, String emailId);

}
