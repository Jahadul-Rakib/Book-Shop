package com.book.security.repo;

import java.util.Set;
import org.springframework.stereotype.Repository;
import com.book.entity.User;
import com.book.security.entity.PasswordResetToken;
import com.book.security.entity.UserRole;

@Repository
public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
}
