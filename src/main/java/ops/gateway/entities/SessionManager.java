package ops.gateway.entities;
// Generated Feb 1, 2020 10:38:55 AM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SessionManager generated by hbm2java
 */
@Entity
@Table(name = "session_manager", catalog = "ops_pay_service")
public class SessionManager implements java.io.Serializable {

	private String sessionId;
	private Date createdDate;

	public SessionManager() {
	}

	public SessionManager(String sessionId) {
		this.sessionId = sessionId;
	}

	public SessionManager(String sessionId, Date createdDate) {
		this.sessionId = sessionId;
		this.createdDate = createdDate;
	}

	@Id

	@Column(name = "SESSION_ID", unique = true, nullable = false, length = 100)
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

}
