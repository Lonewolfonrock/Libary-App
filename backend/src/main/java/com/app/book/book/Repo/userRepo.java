package com.app.book.book.Repo;

import com.app.book.book.Entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<user,String> {

    Optional<user> findByEmail(String email);

}
