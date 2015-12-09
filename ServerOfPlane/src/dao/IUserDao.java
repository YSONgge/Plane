package dao;

import entity.User;

public interface IUserDao {
	public boolean checkUserExist(User u);
	public boolean userRegist(User u);
	public boolean userLogin(User u);
	public boolean changePW(User u,String newPW);
}
