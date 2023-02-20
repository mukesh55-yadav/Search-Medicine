package com.Medicine.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Medicine.Bean.BaseBean;
import com.Medicine.Bean.MedicineBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Model.MedicineModel;
import com.Medicine.Model.UserModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.DataValidator;
import com.Medicine.Utility.PropertyReader;
import com.Medicine.Utility.ServletUtility;

/**
 * Servlet implementation class AddMedicineCtl
 */
@WebServlet(name = "AddMedicineCtl", urlPatterns = "/medicine")
public class AddMedicineCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_UPDATE = "Update";
	public static final String OP_SAVE = "Save";         
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMedicineCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("cName"))) {
   			request.setAttribute("cName", PropertyReader.getvalue("error.require", "Company Name"));
   			pass = false;

   		} else if (!DataValidator.isName(request.getParameter("cName"))) {
   			request.setAttribute("cName", PropertyReader.getvalue("error.name", "Company Name"));
   			pass = false;
   		}

   		if (DataValidator.isNull(request.getParameter("mName"))) {
   			request.setAttribute("mName", PropertyReader.getvalue("error.require", "Medicine Name"));
   			pass = false;

   		} else if (!DataValidator.isName(request.getParameter("mName"))) {
   			request.setAttribute("mName", PropertyReader.getvalue("error.name", "Medicine Name"));
   			pass = false;
   		}

   		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getvalue("error.require", "Quantity"));
			pass = false;

		}
   		return pass;
   	}
       
       protected BaseBean populateBean(HttpServletRequest request) {

   		MedicineBean bean = new MedicineBean();

   		bean.setId(DataUtility.getLong(request.getParameter("id")));
   		bean.setCompanyName(DataUtility.getString(request.getParameter("cName")));
   		bean.setMedicineName(DataUtility.getString(request.getParameter("mName")));
   		bean.setQuantity(DataUtility.getLong(request.getParameter("quantity")));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicineModel model = new MedicineModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		MedicineBean bean = new MedicineBean();
		bean = (MedicineBean) populateBean(request);

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
		
		return SMView.MEDICINE_VIEW;
	}

}
