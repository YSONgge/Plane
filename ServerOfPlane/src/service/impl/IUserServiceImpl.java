package service.impl;

import dao.IUserDao;
import dao.impl.IUserDaoImpl;
import entity.User;
import service.IUserService;
import servlet.UserRegister;

public class IUserServiceImpl implements IUserService{
	
	IUserDao dao = null;
	
	public IUserServiceImpl(){
		dao = new IUserDaoImpl();
	}

	public boolean changePW(User u, String newPW) {
		return dao.changePW(u, newPW);
	}

	public boolean checkUserExist(User u) {
		return dao.checkUserExist(u);
	}

	public boolean userLogin(User u) {
		return dao.userLogin(u);
	}

	public boolean userRegister(User u) {
		return dao.userRegister(u);
	}

	/**
	 * Test Mod
	 * @param args
	 */
	public static void main(String[] args) {
		IUserServiceImpl i = new IUserServiceImpl();
		User u = new User("eric\t","eric");
		System.out.println("CheckExist\t"+i.checkUserExist(u));
		System.out.println("Regist\t"+i.userRegister(u));
		System.out.println("Login\t"+i.userLogin(u));
		System.out.println("ChangePW\t"+i.changePW(u, "passwd"));
		System.out.println("Login\t"+i.userLogin(u));
		u.setPassword("passwd");
		System.out.println("newLogin\t"+i.userLogin(u));
	}
}
