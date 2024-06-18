package com.example.researchlab.member.controller;

import com.example.researchlab.common.model.vo.ResponseData;
import com.example.researchlab.common.template.ResponseTemplate;
import com.example.researchlab.member.model.service.MemberService;
import com.example.researchlab.member.model.vo.Member;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final BCryptPasswordEncoder bc;
    private final MemberService memberService;
    private final ResponseTemplate responseTemplate;

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseData> login(@RequestBody @Valid Member member, HttpSession session, BindingResult br) {
        ResponseData rd = new ResponseData();
        // 추후에 JWT활용 로그인 방식으로 변경

        if(br.hasErrors()){
            rd = ResponseData.builder()
                             .data(null)
                             .resultMessage("잘못된 요청")
                             .responseCode("NN")
                             .build();

            return responseTemplate.fail(rd, HttpStatus.BAD_REQUEST);
        } else {

            Member loginMember = memberService.login(member);

            if(loginMember != null) {

                rd = ResponseData.builder()
                        .data(null)
                        .resultMessage("통신성공")
                        .responseCode("NN")
                        .build();

                session.setAttribute("loginMember", loginMember);

                return responseTemplate.success(rd, HttpStatus.OK);
            }

            return responseTemplate.fail(rd, HttpStatus.UNAUTHORIZED);
        }
    }

    //로그아웃
    //회원가입

    @PostMapping("/enroll")
    public ResponseEntity<ResponseData> enrollMember(@RequestBody Member member){
        return null;
    }
    //정보수정
    //탈퇴



}
