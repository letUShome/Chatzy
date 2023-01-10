package web.slack.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import web.slack.controller.dto.OauthAttributes;
import web.slack.domain.entity.Member;
import web.slack.domain.repository.GoogleCodeRepository;
import web.slack.domain.repository.MemberRepository;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info(userRequest.toString());
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.info(oAuth2User.getAttributes().toString());

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OauthAttributes attributes = OauthAttributes.ofGoogle(userNameAttributeName, oAuth2User.getAttributes());

        // TODO: 구글 로그인 2차 코드 생성하는 서비스 실행
        Member member = saveOrUpdate(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRole().getKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    public Member saveOrUpdate(OauthAttributes attributes) {
        if(memberRepository.findByEmail(attributes.getEmail()).isEmpty()){
            Member member = attributes.toEntity();
            return memberRepository.save(member);
        } else{
            return memberRepository.findByEmail(attributes.getEmail()).get();
        }
    }

    public String  loadUserPostman(Map<String, Object> attribute) {
        OauthAttributes attributes = OauthAttributes.ofGoogle((String) attribute.get("sub"), attribute);
        Member member = saveOrUpdate(attributes);
        return member.getId();
    }
}
