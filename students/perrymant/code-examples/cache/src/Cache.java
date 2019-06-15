import java.util.*;

/**
 * 2 methods:
 * - Gives all users associated with 1 account
 * - Find all the accounts associated with 1 user.
 * create maps of user IDs to lists of accounts,
 * create maps of account IDs to lists of users,
 */
public class Cache {
    // The List of users and accounts that are stored in the cache:
    private List<User> users = new ArrayList<>();        // Replace
    private List<Account> accounts = new ArrayList<>();     // Replace

    // map of <accountID, List<User>> stored in the cache:
    private Map<Integer, List<User>> accountIdToUserListHashMap = new HashMap<>();
    // map of <userID, List<Account>> stored in the cache:
    private Map<Integer, List<Account>> userIdToAccountListHashMap = new HashMap<>();

    // The DB that can also be accessed:
    private final DB db;

    // The Cache constructor has an instance of the DB passed to it:
    public Cache(DB db) {
        this.db = db;
    }

    // Gives all users associated with 1 account:
    List<User> getAllUsers(Account account) {
        // userId values found in cache or DB are added to the following Lists:
        List<Integer> fromCache = new ArrayList<>();
        List<Integer> fromDB = new ArrayList<>();

        // Loop through the accounts, check if the userId is in the cache,
        // if it is: add the userId to the fromCache list,
        // if not: add the userId to the fromDB list.
        for (Integer userId : account.getUserIds()) {
            if (containsUser(users, userId)) {
                fromCache.add(userId);
            } else {
                fromDB.add(userId);
            }
        }

        // Loop through the cache, get userIds and add them to the allUsersForAccount List.
        List<User> allUsersForAccount = new ArrayList<>();
        for (Integer userId : fromCache) {
            for (User user : users) {
                if (userId == user.getId()) {
                    allUsersForAccount.add(user);
                }
            }
        }

        // Loop through the DB, get userIds and add them to the allUsersForAccount List.
        // Also add the userID to the cache.
        for (Integer userID : fromDB) {
            User user = db.getUser(userID);
            allUsersForAccount.add(user);
            users.add(user);
        }
        return allUsersForAccount;
    }

    // Find all the accounts associated with 1 user:
    List<Account> getAllAccounts(User user) {
        // accountId values found in cache or DB are added to the following Lists:
        List<Integer> fromCache = new ArrayList<>();
        List<Integer> fromDB = new ArrayList<>();

        // Loop through the users, check if the accountId is in the cache,
        // if it is: add the accountId to the fromCache list,
        // if not: add the accountId to the fromDB list.
        for (Integer accountId : user.getAccountIds()) {
            if (containsAccount(accounts, accountId)) {
                fromCache.add(accountId);
            } else {
                fromDB.add(accountId);
            }
        }

        // Loop through the cache, get accountIds and add them to the allAccountsForUser List.
        List<Account> allAccountsForUser = new ArrayList<>();
        for (Integer accountId : fromCache) {
            for (Account account : accounts) {
                if (accountId == account.getId()) {
                    allAccountsForUser.add(account);
                }
            }
        }

        // Loop through the DB, get accountIds and add them to the allAccountsForUser List.
        // Also add the accountIds to the cache.
        for (Integer accountID : fromDB) {
            Account account = db.getAccount(accountID);
            allAccountsForUser.add(account);
            accounts.add(account);
        }
        return allAccountsForUser;
    }

    // Checks that the userId is in the users List
    private boolean containsUser(List<User> users, Integer userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return true;
            }
        }
        return false;
    }

    // Checks that the accountId is in the accounts List
    private boolean containsAccount(List<Account> accounts, Integer accountId) {
        for (Account account : accounts) {
            if (account.getId() == accountId) {
                return true;
            }
        }
        return false;
    }
}

interface DB {
    User getUser(int id);

    Account getAccount(int id);
}


// The User POJO:
class User {
    private int id;
    private Set<Integer> accountIds;

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Set<Integer> getAccountIds() {
        return accountIds;
    }

    void setAccountIds(Set<Integer> accountIds) {
        this.accountIds = accountIds;
    }
}

// The Account POJO:
class Account {
    private int id;
    private Set<Integer> userIds;

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    Set<Integer> getUserIds() {
        return userIds;
    }

    void setUserIds(Set<Integer> userIds) {
        this.userIds = userIds;
    }
}