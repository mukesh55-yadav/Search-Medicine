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
import com.Medicine.Bean.PaymentBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Exception.ApplicationException;
import com.Medicine.Exception.DuplicateRecordException;
import com.Medicine.Model.PaymentModel;
import com.Medicine.Model.UserModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.DataValidator;
import com.Medicine.Utility.PropertyReader;
import com.Medicine.Utility.ServletUtility;


@WebServlet(name = "PaymentCtl", urlPatterns = "/payment")
public class PaymentCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_PAY = "Pay";
    
    public PaymentCtl() {
        super();
    }
    @Override
   	protected boolean validate(HttpServletRequest request) {
   		System.out.println("in validation");
   		boolean pass = true;

   		if (DataValidator.isNull(request.getParameter("amount"))) {
			request.setAttribute("amount", PropertyReader.getvalue("error.require", "Amount"));
			pass = false;
		}
   		if (DataValidator.isNull(request.getParameter("cardNumber"))) {
   			request.setAttribute("cardNumber", PropertyReader.getvalue("error.require", "Card Number"));
   			pass = false;
   		} 
   		if (DataValidator.isNull(request.getParameter("orderId"))) {
			request.setAttribute("orderId", PropertyReader.getvalue("error.require", "Order Id"));
			pass = false;
		}
   		return pass;
   	}
       
       protected BaseBean populateBean(HttpServletRequest request) {

   		PaymentBean bean = new PaymentBean();
   		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long userId = existBean.getId();
		bean.setUserid(userId);
   		bean.setId(DataUtility.getLong(request.getParameter("id")));
   		bean.setAmount(DataUtility.getLong(request.getParameter("amount")));
   		bean.setCardNumber(DataUtility.getString(request.getParameter("cardNumber")));
   		bean.setOrderid(DataUtility.getLong(request.getParameter("orderId")));
   		populateDTO(bean, request);
   		return bean;

   	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("in do post");
		PaymentModel model = new PaymentModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		PaymentBean bean = new PaymentBean();
		bean = (PaymentBean) populateBean(request);

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(SMView.PAYMENT_CTL, request, response);
			return;
		}

		if (OP_PAY.equalsIgnoreCase(op)) {
			bean = (PaymentBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Payment Successfully", request);
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
		return SMView.PAYMENT_VIEW;
	}

}
