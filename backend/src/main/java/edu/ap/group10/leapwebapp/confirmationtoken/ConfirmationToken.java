package edu.ap.group10.leapwebapp.confirmationtoken;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.ap.group10.leapwebapp.company.Company;
import lombok.Data;

@Data
@Entity
@Table
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, name="token_id")
    private Long id;

    @Column(name="token_name")
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "company_id")
    private Company company;

    public ConfirmationToken() {}

    public ConfirmationToken(Company company) {
        this.company = company;
        createdDate = new Date();
        token = UUID.randomUUID().toString();
    }
}
