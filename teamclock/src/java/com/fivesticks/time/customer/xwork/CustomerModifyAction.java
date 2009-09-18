/*
 * Created on Aug 25, 2004 by shuji
 */
/*
 * Nick 2005-10-9 Added Country.
 */
package com.fivesticks.time.customer.xwork;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerDeleteCommand;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;

/**
 * @author shuji
 */
public class CustomerModifyAction extends SessionContextAwareSupport implements
		CustomerModifyContextAware {

	// private FstxTimeSettings fs;

	private CustomerModifyContext customerModifyContext;

	private String customerSubmit;

	private String customerSubmitDelete;

	private String customerCancel;

	private Long target;

	private Customer targetCust;

	// private String customerName;
	//
	// private String street1;
	//
	// private String street2;
	//
	// private String city;
	//
	// private String state;
	//
	// private String zip;
	//
	// private String country;
	//
	// private Boolean hidden = Boolean.FALSE;
	// private Long status;

	public String execute() throws Exception {

		if (this.getCustomerCancel() != null)
			return SUCCESS;

		if (this.getCustomerSubmitDelete() != null)
			return this.getSessionContext().getCommonDelete(
					new CustomerDeleteCommand(this.getCustomerModifyContext()
							.getTargetCustomer()));

		CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory
				.build(this.getSessionContext());

		if (this.getCustomerSubmit() == null) {
			this.getCustomerModifyContext().setTargetCustomer(null);
			if (this.getTarget() != null) {

				Customer customerTarget = null;

				try {
					customerTarget = customerBD.getFstxCustomer(this
							.getTarget());
				} catch (CustomerServiceDelegateException e) {
					return ERROR;
				}

				if (customerTarget == null) {
					customerTarget = this.getCustomerModifyContext()
							.getTargetCustomer();

				}

				if (customerTarget != null) {
					this.setTargetCust(customerTarget);

					this.getCustomerModifyContext().setTargetCustomer(
							customerTarget);
				} else {
					this.setTargetCust(new Customer());
				}
			}
			return INPUT;
		}

		if (this.getTargetCust().getName() == null
				|| this.getTargetCust().getName().trim().length() == 0) {
			this.addFieldError("targetCust.name", "name is required.");
		}
		if (!StringUtils.hasText(this.getTargetCust().getCountry())
				|| this.getTargetCust().getCountry().equals("--")) {
			this.addFieldError("targetCust.country", "Country is required.");
		}
		if (this.hasErrors()) {
			return INPUT;
		}

		if (this.getCustomerModifyContext().getTargetCustomer() != null) {

			Customer customer = this.getCustomerModifyContext()
					.getTargetCustomer();
			// customer.setName(this.getCustomerName());

			this.getTargetCust().setName(customer.getName());
			this.getTargetCust().setId(customer.getId());
			this.getTargetCust().setOwnerKey(customer.getOwnerKey());
			
			saveCustomer(this.getTargetCust());

		} else {

			saveCustomer(this.getTargetCust());

			this.getCustomerModifyContext().setTargetCustomer(
					this.getTargetCust());

		}
		if (this.hasErrors()) {
			return INPUT;
		}
		return SUCCESS;
	}

	private void saveCustomer(Customer newCustomer) {
		CustomerServiceDelegate customerBD = CustomerServiceDelegateFactory.factory
				.build(this.getSystemOwner());
		// asume customerName is already there

		if (this.getCustomerModifyContext().getTargetCustomer() != null) {
			newCustomer.setId(this.getCustomerModifyContext().getTargetCustomer().getId());
		}
		try {
			customerBD.save(newCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			this.addFieldError("customerName", "same name is not allowed.");
		}

	}

	public Collection getCountries() {
		return new GeographicCollection().getCountries();

	}

	public String getCustomerSubmit() {
		return customerSubmit;
	}

	public void setCustomerSubmit(String submit) {
		this.customerSubmit = submit;
	}

	public Long getTarget() {
		return target;
	}

	public void setTarget(Long target) {
		this.target = target;
	}

	/**
	 * @return Returns the customerCancel.
	 */
	public String getCustomerCancel() {
		return customerCancel;
	}

	/**
	 * @param customerCancel
	 *            The customerCancel to set.
	 */
	public void setCustomerCancel(String customerCancel) {
		this.customerCancel = customerCancel;
	}

	/**
	 * @return Returns the customerModifyContext.
	 */
	public CustomerModifyContext getCustomerModifyContext() {
		return customerModifyContext;
	}

	/**
	 * @param customerModifyContext
	 *            The customerModifyContext to set.
	 */
	public void setCustomerModifyContext(
			CustomerModifyContext customerModifyContext) {
		this.customerModifyContext = customerModifyContext;
	}

	public Collection getStatusLookups() throws Exception {
		return LookupServiceDelegateFactory.factory
				.build(this.getSystemOwner())
				.getAll(LookupType.CUSTOMER_STATUS);
	}

	public String getCustomerSubmitDelete() {
		return customerSubmitDelete;
	}

	public void setCustomerSubmitDelete(String customerSubmitDelete) {
		this.customerSubmitDelete = customerSubmitDelete;
	}

	public Customer getTargetCust() {
		return targetCust;
	}

	public void setTargetCust(Customer targetCust) {
		this.targetCust = targetCust;
	}

	public Collection getCustomerContacts() throws Exception {
		Collection ret = new ArrayList();

		if (this.getCustomerModifyContext().getTargetCustomer() != null) {
			ret = ContactV2ServiceDelegateFactory.factory.build(
					this.getSessionContext()).getByCustomer(
					this.getCustomerModifyContext().getTargetCustomer());
		}
		
		return ret;
	}
}