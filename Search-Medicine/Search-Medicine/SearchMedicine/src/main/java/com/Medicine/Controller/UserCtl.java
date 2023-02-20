package com.Medicine.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Medicine.Bean.BaseBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Model.UserModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.DataValidator;
import com.Medicine.Utility.PropertyReader;
import com.Medicine.Utility.ServletUtility;

/**
 * Servlet implementation class UserCtl
 */
@WebServlet(name = "UserCtl", urlPatterns = "/user")
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_UPDATE = "Update";
	public static final String OP_SAVE = "Save";     
   
    public UserCtl() {
        super();
    }

    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("username"))) {
   			request.setAttribute("username", PropertyReader.getvalue("error.require", "User Name"));
   			pass = false;

   		} else if (!DataValidator.isName(request.getParameter("username"))) {
   			request.setAttribute("username", PropertyReader.getvalue("error.name", "User Name"));
   			pass = false;
   		}

   		if (DataValidator.isNull(request.getParameter("email"))) {
   			request.setAttribute("email", PropertyReader.getvalue("error.require", "Email Id"));
   			pass = false;

   		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
   			request.setAttribute("email", PropertyReader.getvalue("error.login", "Email Id"));
   			pass = false;
   		}

   		if (DataValidator.isNull(request.getParameter("password"))) {
   			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
   			pass = false;

   		}
   		else if (!DataValidator.isPassword(request.getParameter("password"))) {
   			request.setAttribute("password", PropertyReader.getvalue("error.password", "Password"));
   			return false;
   		}

   		return pass;
   	}
       
       protected BaseBean populateBean(HttpServletRequest request) {

   		UserBean bean = new UserBean();

   		bean.setId(DataUtility.getLong(request.getParameter("id")));
   		bean.setUserName(DataUtility.getString(request.getParameter("username")));
   		bean.setEmail(DataUtility.getString(request.getParameter("email")));
   		bean.setPassword(DataUtility.getString(request.getParameter("password")));
   		bean.setRoleid(2);
   		bean.setRoleName("User");
   		bean.setEnable(true);
   		populateDTO(bean, request);
   		return bean;

   	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserModel model = new UserModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);

		if (bean.getId() > 0) {
			System.out.println("in do post2");
			long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
		} else {
			try {
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Data Add Successfully", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		
		return SMView.USER_VIEW;
	}

}
