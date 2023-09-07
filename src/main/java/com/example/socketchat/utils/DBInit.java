package com.example.socketchat.utils;

import com.example.socketchat.member.Member;
import com.example.socketchat.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class DBInit {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDB(MemberRepository memberRepository){
        return args -> {
            Member member1 = Member.builder()
                    .username("member1")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member member2 = Member.builder()
                    .username("member2")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member member3 = Member.builder()
                    .username("member3")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member member4 = Member.builder()
                    .username("member4")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member member5 = Member.builder()
                    .username("member5")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member member6 = Member.builder()
                    .username("member6")
                    .password(passwordEncoder.encode("1234"))
                    .role("user")
                    .build();

            Member admin = Member.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .role("admin")
                    .build();

            memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4, member5, member6, admin));
        };
    }
}
