package study.hellojpa.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.hellojpa.domain.Member;
import study.hellojpa.repository.MemberRepository;


@Transactional
@SpringBootTest
class MemberServiceTest{


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName( "kim" );

        //when
        Long saveId = memberService.join( member );

        //given
        Assertions.assertThat( member ).isEqualTo( memberRepository.findOne( saveId ) );

    }


}