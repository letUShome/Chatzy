package web.slack.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.slack.config.jwt.JwtTokenProvider;
import web.slack.service.CustomOauth2UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOauth2UserService oauth2UserService;

    @PostMapping("/postman")
    public String postmanTest(@RequestBody Map<String, Object> attribute, HttpServletResponse response){
        String memberId = oauth2UserService.loadUserPostman(attribute);
        response.setHeader("Authorization", jwtTokenProvider.createToken(memberId));
        return "Response with header using HttpServletResponse";
    }


}
