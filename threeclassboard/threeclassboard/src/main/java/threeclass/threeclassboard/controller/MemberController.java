package threeclass.threeclassboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import threeclass.threeclassboard.Service.MemberService;
import threeclass.threeclassboard.domain.Member;
import threeclass.threeclassboard.dto.MemberDto;
import threeclass.threeclassboard.repository.MemberJpaRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberJpaRepository memberJpaRepository;


    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping("/user/signup")
    public String dispSignup(Model model) {
        model.addAttribute("member", new MemberDto());
        return "user/signup";
    }

    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    //로그인
    @GetMapping("/user/login")
    public String dispLogin(@RequestParam(value = "error",required = false) String error,@RequestParam(value = "exception",required = false)String exception,Model model)
    {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }

    //로그인 성공시
    @GetMapping("/user/login/result")
    public String dispLoginResult(Model model, Authentication authentication) {

        String name = authentication.getName();
        model.addAttribute("name", name);

        return "/user/loginSuccess";
    }

    @GetMapping("/user/logout/result")
    public String disLogout() {
        return "/user/logout";
    }

    @GetMapping("/user/denined")
    public String dispDenied() {
        return "/user/denied";
    }

    @GetMapping("user/info")
    public String dispMyInfo() {
        return "/user/myinfo";
    }

    @GetMapping("/admin")
    public String dispAdmin() {
        return "/user/admin";
    }


}
