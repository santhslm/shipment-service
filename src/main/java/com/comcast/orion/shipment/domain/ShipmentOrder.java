package com.comcast.orion.shipment.domain;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The shipmentOrder Schema
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({ "id", "purchaseOrderNumber", "compsOrderNumber", "vendorOrderNumber", "orderStatus",
		"vendorOrderStatus", "externalTransactionId", "createdBy", "createdTimeStamp", "modifiedTimeStamp",
		"shipmentOrderDetail", "shippingAddress", "additionalAttributes", "lineItems", "shipmentTrackingDetails" })
public class ShipmentOrder {

	/**
	 * Internal Order ID
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("id")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "int", position = 0, value = "", example = "")
	private BigInteger id = null;
	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("purchaseOrderNumber")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	@NotNull
	private String purchaseOrderNumber = "";
	/**
	 * The compsOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("compsOrderNumber")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String compsOrderNumber = "";
	/**
	 * The vendorOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("vendorOrderNumber")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String vendorOrderNumber = "";
	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("orderStatus")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String orderStatus = "";
	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("vendorOrderStatus")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String vendorOrderStatus = "";

	@JsonProperty("externalTransactionId")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String externalTransactionId = "";

	@JsonProperty("createdBy")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "string", position = 0, value = "", example = "")
	@Pattern(regexp = "^(.*)$")
	private String createdBy = "";

	@JsonProperty("createdTimeStamp")
	@JsonPropertyDescription("")
	private Timestamp createdTimeStamp;

	@JsonProperty("modifiedTimeStamp")
	@JsonPropertyDescription("")
	private Timestamp modifiedTimeStamp;

	public Timestamp getModifiedTimeStamp() {
		return modifiedTimeStamp;
	}

	public void setModifiedTimeStamp(Timestamp modifiedTimeStamp) {
		this.modifiedTimeStamp = modifiedTimeStamp;
	}

	/**
	 * The shipmentOrder Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentOrderDetail")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
	@Valid
	private ShipmentOrderDetail shipmentOrderDetail;
	/**
	 * The Shippingaddress Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shippingAddress")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "object", position = 0, value = "", example = "")
	@Valid
	private ShippingAddress shippingAddress;
	/**
	 * The Additionalattributes Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("additionalAttributes")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
	@Valid
	@NotNull
	private List<AdditionalAttribute> additionalAttributes = new ArrayList<AdditionalAttribute>();
	/**
	 * The Lineitems Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lineItems")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
	@Valid
	@NotNull
	private List<LineItem> lineItems = new ArrayList<LineItem>();
	/**
	 * The shipmentTrackingDetails Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentTrackingDetails")
	@JsonPropertyDescription("")
	@ApiModelProperty(required = false, dataType = "array", position = 0, value = "", example = "")
	@Valid
	private List<ShipmentTrackingDetail> shipmentTrackingDetails = new ArrayList<ShipmentTrackingDetail>();

	/**
	 * Internal Order ID
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("id")
	public BigInteger getId() {
		return id;
	}

	/**
	 * Internal Order ID
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("id")
	public void setId(BigInteger id) {
		this.id = id;
	}

	public ShipmentOrder withId(BigInteger id) {
		this.id = id;
		return this;
	}

	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("purchaseOrderNumber")
	public String getPurchaseOrderNumber() {
		return purchaseOrderNumber;
	}

	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("purchaseOrderNumber")
	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
	}

	public ShipmentOrder withPurchaseOrderNumber(String purchaseOrderNumber) {
		this.purchaseOrderNumber = purchaseOrderNumber;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * The compsOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("compsOrderNumber")
	public String getCompsOrderNumber() {
		return compsOrderNumber;
	}

	/**
	 * The compsOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("compsOrderNumber")
	public void setCompsOrderNumber(String compsOrderNumber) {
		this.compsOrderNumber = compsOrderNumber;
	}

	public ShipmentOrder withCompsOrderNumber(String compsOrderNumber) {
		this.compsOrderNumber = compsOrderNumber;
		return this;
	}

	/**
	 * The vendorOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("vendorOrderNumber")
	public String getVendorOrderNumber() {
		return vendorOrderNumber;
	}

	/**
	 * The vendorOrderNumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("vendorOrderNumber")
	public void setVendorOrderNumber(String vendorOrderNumber) {
		this.vendorOrderNumber = vendorOrderNumber;
	}

	public ShipmentOrder withVendorOrderNumber(String vendorOrderNumber) {
		this.vendorOrderNumber = vendorOrderNumber;
		return this;
	}

	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("orderStatus")
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * The Purchaseordernumber Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("orderStatus")
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ShipmentOrder withOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	/**
	 * The shipmentOrder Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentOrderDetail")
	public ShipmentOrderDetail getShipmentOrderDetail() {
		return shipmentOrderDetail;
	}

	/**
	 * The shipmentOrder Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentOrderDetail")
	public void setShipmentOrderDetail(ShipmentOrderDetail shipmentOrderDetail) {
		this.shipmentOrderDetail = shipmentOrderDetail;
	}

	public ShipmentOrder withShipmentOrderDetail(ShipmentOrderDetail shipmentOrderDetail) {
		this.shipmentOrderDetail = shipmentOrderDetail;
		return this;
	}

	/**
	 * The Shippingaddress Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shippingAddress")
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * The Shippingaddress Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shippingAddress")
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public ShipmentOrder withShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
		return this;
	}

	/**
	 * The Additionalattributes Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("additionalAttributes")
	public List<AdditionalAttribute> getAdditionalAttributes() {
		return additionalAttributes;
	}

	/**
	 * The Additionalattributes Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("additionalAttributes")
	public void setAdditionalAttributes(List<AdditionalAttribute> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
	}

	public ShipmentOrder withAdditionalAttributes(List<AdditionalAttribute> additionalAttributes) {
		this.additionalAttributes = additionalAttributes;
		return this;
	}

	/**
	 * The Lineitems Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lineItems")
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * The Lineitems Schema
	 * <p>
	 * 
	 * (Required)
	 * 
	 */
	@JsonProperty("lineItems")
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public ShipmentOrder withLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
		return this;
	}

	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	/**
	 * The shipmentTrackingDetails Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentTrackingDetails")
	public List<ShipmentTrackingDetail> getShipmentTrackingDetails() {
		return shipmentTrackingDetails;
	}

	public String getExternalTransactionId() {
		return externalTransactionId;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}

	/**
	 * The shipmentTrackingDetails Schema
	 * <p>
	 * 
	 * 
	 */
	@JsonProperty("shipmentTrackingDetails")
	public void setShipmentTrackingDetails(List<ShipmentTrackingDetail> shipmentTrackingDetails) {
		this.shipmentTrackingDetails = shipmentTrackingDetails;
	}

	public ShipmentOrder withShipmentTrackingDetails(List<ShipmentTrackingDetail> shipmentTrackingDetails) {
		this.shipmentTrackingDetails = shipmentTrackingDetails;
		return this;
	}

	public String getVendorOrderStatus() {
		return vendorOrderStatus;
	}

	public void setVendorOrderStatus(String vendorOrderStatus) {
		this.vendorOrderStatus = vendorOrderStatus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(purchaseOrderNumber).append(compsOrderNumber)
				.append(vendorOrderNumber).append(orderStatus).append(shipmentOrderDetail).append(shippingAddress)
				.append(additionalAttributes).append(lineItems).append(shipmentTrackingDetails).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof ShipmentOrder) == false) {
			return false;
		}
		ShipmentOrder rhs = ((ShipmentOrder) other);
		return new EqualsBuilder().append(id, rhs.id).append(purchaseOrderNumber, rhs.purchaseOrderNumber)
				.append(compsOrderNumber, rhs.compsOrderNumber).append(vendorOrderNumber, rhs.vendorOrderNumber)
				.append(orderStatus, rhs.orderStatus).append(shipmentOrderDetail, rhs.shipmentOrderDetail)
				.append(shippingAddress, rhs.shippingAddress).append(additionalAttributes, rhs.additionalAttributes)
				.append(lineItems, rhs.lineItems).append(shipmentTrackingDetails, rhs.shipmentTrackingDetails)
				.isEquals();
	}

}
