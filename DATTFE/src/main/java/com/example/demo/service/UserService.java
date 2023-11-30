package com.example.demo.service;

import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.KhachHangDao;
import com.example.demo.repository.NhanVienDAO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    KhachHangDao khachHangDao;
    @Autowired
    NhanVienDAO nhanVienDAO;

    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            boolean kq = true;
            String password = "";
            String roles = "";
            if (kq){
                KhachHang accounts = khachHangDao.getKhByEmail(email.trim());
                if (accounts==null){
                    kq=false;
                }else {
                    password = accounts.getMatkhau();
                    roles="USER";
                    System.out.println("MK:Mã Hóa là :"+ pe.encode(password));
                }
            }
            if (kq==false){
                NhanVien accounts = nhanVienDAO.getNVByEmail(email.trim());
//              Tao UserDetail tu Account
                password = accounts.getMatKhau();
                roles = accounts.getChucVu().getTen();
            }
            return User.withUsername(email).password(password).roles(roles).build();
        }catch (Exception exception){
            exception.printStackTrace();
            throw new UsernameNotFoundException(email+"not found");
        }
    }

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2){
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());
        UserDetails user= User.withUsername(email)
                .password(pe.encode(password)).roles("ADMIN").build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
