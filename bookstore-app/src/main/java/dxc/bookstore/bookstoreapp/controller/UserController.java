package dxc.bookstore.bookstoreapp.controller;

import dxc.bookstore.bookstoreapp.model.request.ApiRequest;
import dxc.bookstore.bookstoreapp.model.response.ApiResponse;
import dxc.bookstore.bookstoreapp.model.entity.User;
import dxc.bookstore.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/setup/user")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public @ResponseBody
    ApiResponse<User> addUser(@RequestBody ApiRequest<User> userApiRequest) {
        System.out.println("addUser Post API called");
        return new ApiResponse<>(userService.addUser(userApiRequest.getData()), HttpStatus.CREATED) ;
    }

    @PostMapping("/addUsers")
    public @ResponseBody ApiResponse<List<User>> addUsers(@RequestBody ApiRequest<List<User>> userApiRequests) {
        System.out.println("addUsers Post API called");
        return new ApiResponse<>(userService.addUsers(userApiRequests.getData()), HttpStatus.CREATED) ;
    }

    @GetMapping("/getAllUsers")
    public @ResponseBody ApiResponse<List<User>> getUserList() {
        System.out.println("getUserList Get API called");
        return new ApiResponse<>(userService.getUserList(), HttpStatus.OK);
    }


}
