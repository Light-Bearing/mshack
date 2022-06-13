package lb.hack.mshack.controller;

import io.swagger.v3.oas.annotations.Operation;
import lb.hack.mshack.entity.Role;
import lb.hack.mshack.entity.User;
import lb.hack.mshack.message.request.*;
import lb.hack.mshack.message.response.JwtResponse;
import lb.hack.mshack.message.response.ResponseMessage;
import lb.hack.mshack.repository.RoleRepository;
import lb.hack.mshack.repository.UserRepository;
import lb.hack.mshack.security.jwt.JwtProvider;
import lb.hack.mshack.security.services.UserPrinciple;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"}, maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userPrinciple.getUsername(), userPrinciple.getSurname(), userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = signUpRequest.toUser(roleRepository.findAll());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User " + signUpRequest.getName() + " is registered successfully!"), HttpStatus.OK);

    }

    @PostMapping("/update_password")
    @PreAuthorize("hasRole('ADMIN') OR principal.user.id == #request.id")
    @Operation(description = "Обновление пароля пользователя, доступно для пользователей с ролью ADMIN и для самих пользователей")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordRequest request) {
        userRepository.findById(request.getId()).ifPresent(user -> {
            user.setPassword(encoder.encode(request.getPassword()));
            userRepository.save(user);
        });
        return new ResponseEntity<>(new ResponseMessage("Password updated"), HttpStatus.OK);
    }

    @PostMapping("/update_roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "Обновление ролей, доступно для пользователей с ролью ADMIN")
    public ResponseEntity<?> updateRoles(@Valid @RequestBody UpdateRolesRequest request) {
        userRepository.findById(request.getUserId()).ifPresent(user -> {
            val roles = roleRepository.findAllById(request.getRoleIds());

            user.setRoles(new HashSet<>(roles));

            userRepository.save(user);
        });
        return new ResponseEntity<>(new ResponseMessage("Roles updated"), HttpStatus.OK);
    }

    @PutMapping("/update_user")
    @PreAuthorize("hasRole('ADMIN') OR principal.user.id == #request.id")
    @Operation(description = "Обновление главных данных пользователя, доступно для пользователей с ролью ADMIN")
    public ResponseEntity<?> updateUser(@Valid @RequestBody SignUpdateForm request) {
        User findUser = userRepository.findByUsernameOrEmail(request.getUsername(), request.getEmail()).orElse(null);
        if (findUser != null && !findUser.getId().equals(request.getId())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (findUser != null) {
            List<Role> roles = roleRepository.findAll();
            User updatedUser = request.updateUser(findUser, roles);
            updatedUser.setId(request.getId());
            userRepository.save(updatedUser);

            return new ResponseEntity<>(new ResponseMessage("User updated"), HttpStatus.OK);
        } else return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/roles")
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    @GetMapping
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
