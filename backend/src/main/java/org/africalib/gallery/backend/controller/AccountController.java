package org.africalib.gallery.backend.controller;

import jakarta.servlet.http.Cookie;
import org.africalib.gallery.backend.entity.Member;
import org.africalib.gallery.backend.repository.MemberRepository;
import org.africalib.gallery.backend.repository.MemberRepository;
import org.africalib.gallery.backend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public int login(
            @RequestBody Map<String, String> params
    ) {
        Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));
        if (member != null) {
            JwtService jwtService = new JwtServiceImpl();
            int id = member.getId();
            String token = jwtService.getToken("id". id);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);
            return ResponseEntity.ok().build();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
