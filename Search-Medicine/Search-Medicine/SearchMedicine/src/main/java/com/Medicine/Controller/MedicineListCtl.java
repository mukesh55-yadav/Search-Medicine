package com.Medicine.Controller;

import java.io.IOException;
import java.util.List;

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
import com.Medicine.Utility.ServletUtility;

/**
 * Servlet implementation class MedicineListCtl
 */
@WebServlet(name = "MedicineListCtl", urlPatterns = "/medicinelist")
public class MedicineListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineListCtl() {
        super();
        // TODO Auto-generated constructor stub
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
		MedicineBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}

		List list = null;
		try {
			System.out.println("in do get");
			list = model.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null && list.size() == 0) {
			ServletUtility.setErrorMessage("No record found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in Post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		MedicineModel model = new MedicineModel();
		MedicineBean bean = new MedicineBean();
		bean = (MedicineBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SMView.MEDICINE_LIST_CTL, request, response);
			return;

		}
		if (OP_SEARCH.equalsIgnoreCase(op)) {
			try {
				list = model.search(bean);
				ServletUtility.setList(list, request);
				ServletUtility.setbean(bean, request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		
		return SMView.MEDICINE_LIST_VIEW;
	}

}
