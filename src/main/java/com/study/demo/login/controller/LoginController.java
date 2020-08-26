package com.study.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.demo.security.service.SecurityService;
import com.study.demo.vo.Member;
/********************************************
 * @Class Name : LoginController
 * @Description : 로그인 Controller
 * @Modification : Information
 * @
 * @	 수정일				수정자					수정내용
 * @ -------------------------------------------------------------------
 * @ 2020-08-26		sharic					최초생성
 * @
 * @author : sharic
 * @since : 2020-08-26
 * @version : 1.0
 * @see
 ********************************************/
@Controller
public class LoginController {
	
	@Autowired
	private SecurityService securityService;
	
	/********************************************** 
	 * 1. MethodName : goUserJoinFramelo
	 * 2. RequestMapping : /join/join.do
	 * 3. ReturnVIew : view/login/join.jsp
	 * 4. Comment : 회원가입 페이지 이동
	 * @param : 
	 * @return : ModelAndView
	 * @throws : Exception
	 **********************************************/
    @GetMapping("/join/join")
    public String goUserJoinFrame() {
        return  "login/join";
    }
    
    //로그인 페이지 이동
    @RequestMapping("/login/login")
    public ModelAndView lgoin(@RequestParam(value="msg", required=false) String msg) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("/login/login");
        return mv;
    }
    
    //로그인 에러페이지 이동
    @GetMapping("/login/login-error")
    public String error() {
        return  "login/error";
    }
    
    //아이디 중복체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam String inputId) throws Exception{
        Member member = securityService.getSelectMeberInfo(inputId);
        String canUse = member != null ? "" : "Y";
        return canUse;
    }
    
    //회원가입 Insert 
    @PostMapping("/join/insert")
    public String setInsertMember(Member member) throws Exception{
        if(securityService.setInsertMember(member) > 0){
            return  "login/login";    
        }else {
            return  "join/join";
        }
    }
    
    //로그인 성공후 이동페이지
    @RequestMapping("/home")
    public String home() {
        return  "home/home";
    }
    
}