package com.cg.model;

import com.cg.model.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
@Accessors(chain = true)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

//    @Column(nullable = false, unique = true)
//    private String email;

    private String phone;


    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;


    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;


    public CustomerDTO toCustomerDTO() {

        return new CustomerDTO()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegionDTO())
                ;
    }
}
