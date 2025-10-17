package org.Irepository;

import org.dao.User;

public interface IUserRepository {
    public User checkAccount(String username, String password);
}
