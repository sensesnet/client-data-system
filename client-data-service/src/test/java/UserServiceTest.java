import com.sensesnet.model.User;
import com.sensesnet.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:beans-service-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest
{
    private final static Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    private String userLogin;

    @Before
    public void setUp() {
        User user = new User();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setUserLogin(String.format("test_%s@test.org",timestamp.getTime()));
        user.setUserPassword("19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd");
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
        User user = userService.getUserByLogin("admin@admin.com");
        assertNotNull(user);
    }
}
