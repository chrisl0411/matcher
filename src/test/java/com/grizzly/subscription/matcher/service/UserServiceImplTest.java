package com.grizzly.subscription.matcher.service;

import com.grizzly.subscription.matcher.assembler.UserModelAssembler;
import com.grizzly.subscription.matcher.domain.User;
import com.grizzly.subscription.matcher.repository.UserRepository;
import com.grizzly.subscription.matcher.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserModelAssembler userModelAssembler;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void addVerifyToUser() {
        User user = new User("firstName", "lastName", "male", 22, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Engineering", "Software Development", 5L, "hello my name is firstName");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        userService.verifyUser(1L);
        assertEquals(1, user.getVerifiedCount());
    }

//    @Test
//    void getAllUsers() throws Exception {
//        CollectionModel<EntityModel<User>> users = createUsers();
//        Mockito.when(userService.getUsers()).thenReturn(users);
//        this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/users")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$", hasSize(5)))
////                .andExpect(jsonPath("$[2].id", is(3L)));
//    }
//
//    private CollectionModel<EntityModel<User>> createUsers() {
//        EntityModel<User> user1 = userModelAssembler.toModel(new User("Test", "User1", "Male", 20, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Engineering", "Software Development"));
//        EntityModel<User> user2 = userModelAssembler.toModel(new User("Test", "User2", "Female", 20, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Engineering", "DevOps"));
//        EntityModel<User> user3 = userModelAssembler.toModel(new User("Test", "User3", "Female", 20, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Design", "UI"));
//        EntityModel<User> user4 = userModelAssembler.toModel(new User("Test", "User4", "Male", 20, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Design", "UX"));
//        EntityModel<User> user5 = userModelAssembler.toModel(new User("Test", "User5", "Female", 20, "Dallas", "TX", "Student", "test@email.com", "1234567890", 0, "Accounting", "CPA Exams"));
//        List<EntityModel<User>> users = new ArrayList<>(Arrays.asList(user1, user2, user3, user4, user5));
//
//        return CollectionModel.of(users,
//                linkTo(methodOn(UserServiceImpl.class).getUsers()).withSelfRel());
//    }


}