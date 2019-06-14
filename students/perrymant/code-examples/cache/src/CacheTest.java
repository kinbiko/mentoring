import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/*
can we create tests to ensure we're not breaking anything please?
This should give you some practice in dependency injection too,
as you'll have to give the class a DB implementation when testing.
*/
@RunWith(MockitoJUnitRunner.class)
public class CacheTest {

    @Mock
    DB db;

    @InjectMocks
    Cache cache;

    @Test
    public void canReturnCorrectSizeOf_getAllUsers() {

        Account account = new Account();
        account.setUserIds(new HashSet<>(Arrays.asList(11, 22, 33)));
        Assert.assertEquals(3, cache.getAllUsers(account).size());
    }

    @Test
    public void canReturnCorrectSizeOf_getAllAccounts() {
        User user = new User();
        user.setAccountIds(new HashSet<>(Arrays.asList(1234, 2345, 3456, 4567)));
        Assert.assertEquals(4, cache.getAllAccounts(user).size());
    }


}