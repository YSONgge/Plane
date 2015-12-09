package service;

import entity.User;

public interface IUserService {
	public boolean checkUserExist(User u);
	public boolean userRegist(User u);
	public boolean userLogin(User u);
	public boolean changePW(User u,String newPW);
}
