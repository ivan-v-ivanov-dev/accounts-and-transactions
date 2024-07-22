package com.capgemini.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
