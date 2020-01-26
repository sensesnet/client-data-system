import com.sensesnet.model.User;
import com.sensesnet.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:beans-service-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest
{

    @Autowired
    private UserService userService;

    private String userLogin;

    @Before
    public void setUp() {
        User user = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setUserLogin(String.format("test_%s@test.org",timestamp.getTime()));
        user.setUserPassword("Password1");
        user.setUserName("Test");
        user.setUserSurname("Test");
        user.setUserAddress("Armando Bennett 2554 Pede");
        user.setUserBirthday("2019-06-27");
        user.setUserRole(1);
        user.setUserPhone("8 033 661 77 71");
        userService.add(user);
        this.userLogin = user.getUserName();
    }

    @Test
    public void getAll() {
        List<User> users = userService.getAll();
        assertFalse(users.isEmpty());
    }

    @Test
    public void getUserByName() {
        User user = userService.getUserByLogin(userLogin);
        assertNotNull(user);
    }
}
