package com.Medicine.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Medicine.Bean.MedicineBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Model.MedicineModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.ServletUtility;

@WebServlet(name = "OrderMedicineListCtl", urlPatterns = "/ordermedicinelist")
public class OrderMedicineListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
  
    public OrderMedicineListCtl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MedicineModel model = new MedicineModel();
		MedicineBean bean = new MedicineBean();
        List list = null;
        HttpSession session = request.getSession(false);
    	UserBean bean2 = (UserBean) session.getAttribute("user");
    	long roleid = bean2.getRoleid();
    	if (roleid==2) {
    		   try {
    			     list =	model.Showlist(bean2.getId());
    			     ServletUtility.setList(list, request);
    			     
    			} catch (Exception e) {
    			}
		}else{
			 try {
			     list =	model.orderlist();
			     ServletUtility.setList(list, request);
			     
			} catch (Exception e) {
			
		}
			 long id = DataUtility.getLong(request.getParameter("id"));
				
			  if (id > 0) {
				  model.delete(id);
			  ServletUtility.setSuccessMessage("FIR Deleted Successfully", request); }
			 
		}
        ServletUtility.forward(getView(), request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	@Override
	protected String getView() {
		return SMView.ORDER_MEDICINE_LIST_VIEW;
	}

}
