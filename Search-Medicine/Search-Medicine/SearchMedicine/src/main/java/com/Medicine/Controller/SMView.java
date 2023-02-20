package com.Medicine.Controller;

public interface SMView {

	public String APP_CONTEXT = "/SearchMedicine";
	public String PAGE_FOLDER = "/jsp";
	
	//Controller------------------
	
	public String WECOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String REGISTRATION_CTL = APP_CONTEXT + "/registration";
	public String USER_CTL = APP_CONTEXT + "/user";
	public String USER_LIST_CTL = APP_CONTEXT + "/userlist";
	public String MEDICINE_CTL = APP_CONTEXT + "/medicine";
	public String MEDICINE_LIST_CTL = APP_CONTEXT + "/medicinelist";
	public String PAYMENT_CTL = APP_CONTEXT + "/payment";
	public String PAYMENT_LIST_CTL = APP_CONTEXT + "/paymentlist";
	public String ORDER_MEDICINE_CTL = APP_CONTEXT + "/ordermedicine";
	public String ORDER_MEDICINE_LIST_CTL = APP_CONTEXT + "/ordermedicinelist";
	//View------------
	
	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/Login.jsp";
    public String REGISTRATION_VIEW = PAGE_FOLDER + "/Registration.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/User.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserList.jsp";
	public String MEDICINE_VIEW = PAGE_FOLDER + "/AddMedicine.jsp";
	public String MEDICINE_LIST_VIEW = PAGE_FOLDER + "/MedicineList.jsp";
	public String PAYMENT_VIEW = PAGE_FOLDER + "/Payment.jsp";
	public String ORDER_MEDICINE_VIEW = PAGE_FOLDER + "/MedicineOrder.jsp";
	public String ORDER_MEDICINE_LIST_VIEW = PAGE_FOLDER + "/OrderMedicineList.jsp";
	public String PAYMENT_LIST_VIEW = PAGE_FOLDER + "/PaymentList.jsp";
}
