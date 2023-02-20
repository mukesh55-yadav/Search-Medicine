package com.Medicine.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Medicine.Bean.BaseBean;
import com.Medicine.Bean.MedicineBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Exception.ApplicationException;
import com.Medicine.Exception.DuplicateRecordException;
import com.Medicine.Model.MedicineModel;
import com.Medicine.Model.UserModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.DataValidator;
import com.Medicine.Utility.PropertyReader;
import com.Medicine.Utility.ServletUtility;

@WebServlet(name = "MedicineOrderCtl", urlPatterns = "/ordermedicine")
public class MedicineOrderCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";            
   
    public MedicineOrderCtl() {
        super();
    }

    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("mName"))) {
   			request.setAttribute("mName", PropertyReader.getvalue("error.require", "Medicine Name"));
   			pass = false;

   		} else if (!DataValidator.isName(request.getParameter("mName"))) {
   			request.setAttribute("mName", PropertyReader.getvalue("error.name", "Medicine Name"));
   			pass = false;
   		}
   		if (DataValidator.isNull(request.getParameter("mId"))) {
			request.setAttribute("mId", PropertyReader.getvalue("error.require", "M ID"));
			pass = false;

		}

   		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getvalue("error.require", "Quantity"));
			pass = false;

		}
   		if (DataValidator.isNull(request.getParameter("userId"))) {
			request.setAttribute("userId", PropertyReader.getvalue("error.require", "UserId"));
			pass = false;

		}
   		return pass;
   	}
    
    protected BaseBean populateBean(HttpServletRequest request) {
   		MedicineBean bean = new MedicineBean();
   		bean.setId(DataUtility.getLong(request.getParameter("id")));
   		bean.setMedicineName(DataUtility.getString(request.getParameter("mName")));
   		bean.setQuantity(DataUtility.getLong(request.getParameter("quantity")));
   		bean.setMedicineid(DataUtility.getLong(request.getParameter("mId")));
   		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long user = existBean.getId();
		bean.setUserid(user);
		//bean.setUserid(DataUtility.getLong(request.getParameter("userId")));
   		populateDTO(bean, request);
   		return bean;
   	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicineModel model = new MedicineModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MedicineBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do post");
		MedicineModel model = new MedicineModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		MedicineBean bean = new MedicineBean();
		bean = (MedicineBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (MedicineBean) populateBean(request);
			try {
				long pk = model.useradd(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Booking Successfully....", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setbean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);

		}
	
	}

	@Override
	protected String getView() {
		return SMView.ORDER_MEDICINE_VIEW;
	}

}
