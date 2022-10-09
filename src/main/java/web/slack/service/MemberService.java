package web.slack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.MemberRequestDto;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String signUp(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .build();
        memberRepository.save(member);
        return "회원가입에 성공하였습니다.";
    }
}
