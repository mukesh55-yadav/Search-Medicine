package com.Medicine.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Medicine.Bean.BaseBean;
import com.Medicine.Bean.PaymentBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Model.PaymentModel;
import com.Medicine.Utility.DataUtility;
import com.Medicine.Utility.ServletUtility;

/**
 * Servlet implementation class PaymentListCtl
 */
@WebServlet(name = "PaymentListCtl", urlPatterns = "/paymentlist")
public class PaymentListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentListCtl() {
        super();
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
		PaymentModel model = new PaymentModel();
		PaymentBean bean = new PaymentBean();
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
			     list =	model.list();
			     ServletUtility.setList(list, request);
			     
			} catch (Exception e) {
			
		}
		}
        ServletUtility.forward(getView(), request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return SMView.PAYMENT_LIST_VIEW;
	}

}
