package com.example.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.services.EmailService;

@Controller
public class ForgotPasswordController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	Random random = new Random(100000);

	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgot_password_form";
	}

	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("email") String email, HttpSession session) {

		long otp= random.nextLong(9999999);
		String subject ="OTP from SCM";
		String message=""
				+ "<div style='border:1px solid #e2e2e2; padding:20px'>"
				+ "<h1>"
				+ "OTP is--> "
				+ "<b>"+otp
				+ "</b>"
				+ "</h1> "
				+ "</div>";

		String to=email;
		boolean flag=this.emailService.sendEmail(subject, message, to);

		if(flag) {
			session.setAttribute("session_otp", otp);
			session.setAttribute("email", email);
			return "verify_OTP";
		}
		session.setAttribute("message", "Check your Email-Id");
		return "forgot_password_form";
	}
	//verify otp
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") long otp, HttpSession session)
	{
		long session_Otp=(long)session.getAttribute("session_otp");

		System.out.println("User OTP "+otp);
		System.out.println("Our OTP "+session_Otp);

		String email=(String)session.getAttribute("email");
		if(session_Otp != otp) {
			session.setAttribute("message", "You have entered wrong otp !!");
			return "forgot_password_form";		}

		User user = this.userRepository.getUserByUserName(email);

		if(user==null)
		{
			//send error message
			session.setAttribute("message", "User does not exits with this email !!");
			return "forgot_password_form";
		}

		//password change form
		return "password_change_form";
	}

	//change password
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("newpassword") String newpassword,HttpSession session)
	{
		String email=(String)session.getAttribute("email");
		User user = userRepository.getUserByUserName(email);
		user.setPassword(this.bcrypt.encode(newpassword));
		this.userRepository.save(user);
		return "redirect:/login?change=password changed successfully..";

	}

}
