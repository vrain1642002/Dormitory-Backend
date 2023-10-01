package com.Dormitory.payment;

import java.time.LocalDate;

import com.Dormitory.contract.Contract;
import com.Dormitory.paymentmethod.PaymentMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id // Đánh dấu đây là ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // trường tăng tự động
    private Integer id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "paymeny_method_id")
    private PaymentMethod paymentMethod;
    @NotNull(message = "Hợp đồng không thể null")
    @OneToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    private Float totalPrice;
    private Boolean isPay = false;
    private LocalDate paymentDate = null;

}
