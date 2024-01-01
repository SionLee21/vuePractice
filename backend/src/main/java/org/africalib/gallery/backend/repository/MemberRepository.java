package org.africalib.gallery.backend.repository;

import org.africalib.gallery.backend.entity.Item;
import org.africalib.gallery.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
}
