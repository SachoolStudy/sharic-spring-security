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
 * @Description : �α��� Controller
 * @Modification : Information
 * @
 * @	 ������				������					��������
 * @ -------------------------------------------------------------------
 * @ 2020-08-26		sharic					���ʻ���
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
	 * 4. Comment : ȸ������ ������ �̵�
	 * @param : 
	 * @return : ModelAndView
	 * @throws : Exception
	 **********************************************/
    @GetMapping("/join/join")
    public String goUserJoinFrame() {
        return  "login/join";
    }
    
    //�α��� ������ �̵�
    @RequestMapping("/login/login")
    public ModelAndView lgoin(@RequestParam(value="msg", required=false) String msg) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",msg);
        mv.setViewName("/login/login");
        return mv;
    }
    
    //�α��� ���������� �̵�
    @GetMapping("/login/login-error")
    public String error() {
        return  "login/error";
    }
    
    //���̵� �ߺ�üũ
    @PostMapping("/join/idCheck")
    @ResponseBody
    public String idCheck(@RequestParam String inputId) throws Exception{
        Member member = securityService.getSelectMeberInfo(inputId);
        String canUse = member != null ? "" : "Y";
        return canUse;
    }
    
    //ȸ������ Insert 
    @PostMapping("/join/insert")
    public String setInsertMember(Member member) throws Exception{
        if(securityService.setInsertMember(member) > 0){
            return  "login/login";    
        }else {
            return  "join/join";
        }
    }
    
    //�α��� ������ �̵�������
    @RequestMapping("/home")
    public String home() {
        return  "home/home";
    }
    
}