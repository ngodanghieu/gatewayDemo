package ops.gateway.entities;
// Generated Feb 1, 2020 10:38:55 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TransactionTemp generated by hbm2java
 */
@Entity
@Table(name = "transaction_temp", catalog = "ops_pay_service")
public class TransactionTemp implements java.io.Serializable {

	private Integer transactionTempId;
	private Integer userId;
	private String data;
	private String sessionId;
	private Date createdDate;
	private String transactionType;
	private int isError;
	private String errorDesc;
	private String originalTransId;

	public TransactionTemp() {
	}

	public TransactionTemp(int isError) {
		this.isError = isError;
	}

	public TransactionTemp(Integer userId, String data, String sessionId, Date createdDate, String transactionType,
			int isError, String errorDesc, String originalTransId) {
		this.userId = userId;
		this.data = data;
		this.sessionId = sessionId;
		this.createdDate = createdDate;
		this.transactionType = transactionType;
		this.isError = isError;
		this.errorDesc = errorDesc;
		this.originalTransId = originalTransId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "TRANSACTION_TEMP_ID", unique = true, nullable = false)
	public Integer getTransactionTempId() {
		return this.transactionTempId;
	}

	public void setTransactionTempId(Integer transactionTempId) {
		this.transactionTempId = transactionTempId;
	}

	@Column(name = "USER_ID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "DATA", length = 65535)
	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Column(name = "SESSION_ID", length = 100)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "TRANSACTION_TYPE", length = 50)
	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "IS_ERROR", nullable = false)
	public int getIsError() {
		return this.isError;
	}

	public void setIsError(int isError) {
		this.isError = isError;
	}

	@Column(name = "ERROR_DESC", length = 65535)
	public String getErrorDesc() {
		return this.errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Column(name = "ORIGINAL_TRANS_ID", length = 100)
	public String getOriginalTransId() {
		return this.originalTransId;
	}

	public void setOriginalTransId(String originalTransId) {
		this.originalTransId = originalTransId;
	}

}
