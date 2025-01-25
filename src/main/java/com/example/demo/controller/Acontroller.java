package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Aentity;
import com.example.demo.entity.Centity;
import com.example.demo.entity.Rentity;
import com.example.demo.entity.Wentity;
import com.example.demo.repository.Arepository;
import com.example.demo.repository.Crepository;
import com.example.demo.repository.Rrepository;
import com.example.demo.repository.Wrepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.OtpService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.234:3000","https://trendix-seven.vercel.app/"})
@RequestMapping("/user")

public class Acontroller {

    @Autowired
    private Arepository arepo;
    @Autowired
    private Crepository crepo;

    @Autowired
    private Wrepository wrepo;

    @Autowired
    private Rrepository rrepo;

    

    @Autowired
    private EmailService emailservice;

    @Autowired
    private OtpService otpservice;

    @PostMapping("/register")
    public String register(@RequestBody Aentity user) {
        String otp = otpservice.otpGenerator();
        System.out.println("Generated OTP: " + otp);
        try {
            String phoneRegex = "^\\d{10}$";
            String nameRegex = "^[A-Za-z\\s]+$";
            String phoneString = String.valueOf(user.getUsernumber());
            Optional<Aentity> existingUser = arepo.findByUseremailOrUsernumber(user.getUseremail(), user.getUsernumber());

            if (existingUser.isPresent()) {
                return "Account already exists";
            }
            if (!phoneString.matches(phoneRegex)) {
                return "Invalid phone number. It must be 10 digits.";
            }
            if (!user.getUsername().matches(nameRegex)) {
                return "Invalid name. It must contain only letters and spaces.";
            }

            user.setUserotp(otp);
            arepo.save(user);

            otpservice.otpSender(user.getUseremail(), otp);

            return "Registered successfully";
        } catch (Exception e) {
            return "An error occurred during registration.";
        }
    }

    @PostMapping("/verify-otp/{mail}/{otp}")
    public String verifyOtp(@PathVariable String mail, @PathVariable String otp) {
        Optional<Aentity> user = arepo.findByUseremail(mail);
        if (user.isPresent()) {
            if (user.get().getUserotp().matches(otp)) {
                return "User verified";
            } else {
                return "User not verified";
            }
        } else {
            return "Invalid OTP.";
        }
    }

    @PostMapping("/setpassword/{mail}/{password}")
    public String setPassword(@PathVariable String mail, @PathVariable String password) {
        Optional<Aentity> user = arepo.findByUseremail(mail);
        if (!user.isPresent()) {
            return "Enter registerd email";
        }
        Aentity USER = user.get();
        USER.setUserpassword(password);
        arepo.save(USER);
        return "Password has been set successfully.";
    }

    @GetMapping("/login/{mail}/{pass}")
    @SuppressWarnings("CallToPrintStackTrace")
    public String login(@PathVariable String mail, @PathVariable String pass) {
        try {
            Optional<Aentity> user = arepo.findByUseremail(mail);
            if (user.isPresent()) {
                Aentity USER = user.get();
                if (USER.getUserpassword().matches(pass)) {
                    return "Welcome " + USER.getUsername();
                } else {
                    return "Invalid password";
                }
            } else {
                return "email not found?..";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/forgotpass/{mail}")
    public String forgotpass(@PathVariable String mail) {
        try {
            Optional<Aentity> user = arepo.findByUseremail(mail);
            if (user.isPresent()) {
                Aentity USER = user.get();
                String subject = "Recovery of your password";
                String body = "The password for your email " + mail + " is retrived, and your password " + USER.getUserpassword() + ". Please continue with our appliction senior " + USER.getUsername();
                emailservice.sendMail(mail, body, subject);
                return "Your password is sent to your mail " + mail;
            } else {
                return "email not found?..";
            }
        } catch (Exception e) {
        }
        return null;
    }

    @GetMapping("/profile/{mail}")
    public Aentity profile(@PathVariable String mail) {
        Optional<Aentity> user = arepo.findByUseremail(mail);
        if (user.isPresent()) {
            Aentity USER = user.get();
            return USER;
        }
        return null;
    }

    @GetMapping("/getuserid/{mail}")
public long getuserid(@PathVariable String mail) {
    Optional<Aentity> user = arepo.findByUseremail(mail);
    if (user.isPresent()) {
        return user.get().getUserid();
    }
    System.out.println("No user found for email: " + mail);
    return 0;
}
@DeleteMapping("/deleteuser/{mail}")
	public String deleteUser(@PathVariable String mail) {
		try {
			Optional<Aentity> user=arepo.findByUseremail(mail);
			if(user.isPresent()) {
				Aentity USER=user.get();
				List<Centity> cartItems=crepo.findByAentityUserid(USER.getUserid());
				if(!cartItems.isEmpty()) {
					crepo.deleteAll(cartItems);
				}
				List<Wentity> wishItems=wrepo.findByAentityUserid(USER.getUserid());
				if(!wishItems.isEmpty()) {
					wrepo.deleteAll(wishItems);
				}
				List<Rentity> commentItems=rrepo.findByAentityUserid(USER.getUserid());
				if(!commentItems.isEmpty()) {
					rrepo.deleteAll(commentItems);
				}
				arepo.delete(USER);
				return "User deleted ";
			}else {
				return "User not found";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
